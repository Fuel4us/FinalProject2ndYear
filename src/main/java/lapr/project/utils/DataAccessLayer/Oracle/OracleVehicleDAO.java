/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import lapr.project.model.Vehicle.*;
import lapr.project.utils.DataAccessLayer.Abstraction.*;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

/**
 * Retrieves instances of Vehicle for a given project
 */
public class OracleVehicleDAO extends OracleDAO implements VehicleDAO {

    private PreparedStatement statement;

    public OracleVehicleDAO() {
    }

    /**
     * Creates a list of instances of {@link Vehicle} from a given project name
     *
     * @param projectName name of the project
     * @return list of {@link Vehicle}
     * @throws SQLException
     */
    @Override
    public List<Vehicle> retrieveVehicle(String projectName) throws SQLException {
        ResultSet vehicleSet = statement.executeQuery(
                "SELECT * FROM VEHICLE WHERE VEHICLE.PROJECTNAME = PROJECT.NAME AND PROJECT.NAME = projectName;"
        );
        //fetchVehiclesFromProject(projectName)

        List<Vehicle> vehicles = new LinkedList<>();
        while (vehicleSet.next()) {
            Vehicle vehicle = retrieveVehicle(vehicleSet);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    /**
     * Creates an instance of {@link Vehicle} from a given ResultSet
     *
     * @param resultSet name of the project
     * @return instance of {@link Vehicle}
     * @throws SQLException
     */
    private Vehicle retrieveVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle;
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int vehicleClass = resultSet.getInt("vehicleTollClass");
        float dragCoefficient = resultSet.getFloat("dragCoefficient");
        float rollingReleaseCoefficient = resultSet.getFloat("rollingReleaseCoefficient");

        VehicleType vehicleType = determineVehicleType(resultSet);
        Vehicle.MotorType motorization = determineMotorType(resultSet);
        Fuel fuel = determineFuel(resultSet);

        Unit[] unitEnum = Unit.values();
        Measurable mass = createMass(name, unitEnum);
        Measurable load = createLoad(name, unitEnum);
        Measurable frontalArea = createFrontalArea(name, unitEnum);
        Measurable wheelSize = createWheelSize(name, unitEnum);

        List<VelocityLimit> velocityLimitList = fillVelocityLimitList(name, unitEnum);

        Energy energy = createEnergy(name);

        //creation of vehicle
        vehicle = new Vehicle(name, description, vehicleType, vehicleClass, motorization, fuel, mass, load, dragCoefficient, frontalArea, rollingReleaseCoefficient, wheelSize, velocityLimitList, energy);
        return vehicle;
    }

    private List<Regime> fillRegimeList(int throttleID) throws SQLException {
        ResultSet regimeSet = statement.executeQuery(
                "SELECT * FROM REGIME WHERE REGIME.THROTTLE_ID = THROTTLE.ID AND THROTTLE.ID = throttleID;"
        );
        //getRegimeSet(throttleID)
        List<Regime> regimeList = new LinkedList<>();
        while (regimeSet.next()) {
            Regime regime = new Regime(regimeSet.getInt("torqueLow"), regimeSet.getInt("torqueHigh"), regimeSet.getInt("rpmLow"), regimeSet.getInt("rpmHigh"), regimeSet.getInt("SFC"));
            regimeList.add(regime);
        }
        return regimeList;
    }

    private List<Throttle> fillThrottleList(int energyID) throws SQLException {
        List<Throttle> throttleList = new LinkedList<>();
        ResultSet throttleSet = statement.executeQuery(
                "SELECT * FROM THROTTLE WHERE THROTTLE.ENERGY_ID = ENERGY.ID AND ENERGY.ID = energyID;"
        );
        //getThrottleSet(energyID)
        while (throttleSet.next()) {
            int throttleID = throttleSet.getInt("id");
            List<Regime> regimeList = fillRegimeList(throttleID);
            Throttle throttle = new Throttle(throttleID, regimeList);
            throttleList.add(throttle);
        }
        return throttleList;
    }

    private List<Gears> fillGearList(int energyID) throws SQLException {
        List<Gears> gearList = new LinkedList<>(); //creation of gears needed by energy

        ResultSet gearsSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getGearSet(?)")) {
            callableStatement.setInt(1, energyID);
            gearsSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (gearsSet.next()) {
            Gears gear = new Gears(gearsSet.getInt("id"), gearsSet.getFloat("ratio"));
            gearList.add(gear);
        }
        return gearList;
    }

    private Energy createEnergy(String name) throws SQLException {
        ResultSet energySet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getLimitSet(?)")) {
            callableStatement.setString(1, name);
            energySet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int energyID = energySet.getInt("id");
        List<Gears> gearList = fillGearList(energyID);
        List<Throttle> throttleList = fillThrottleList(energyID);
        return new Energy(energySet.getInt("rpmLow"), energySet.getInt("rpmHigh"), energySet.getFloat("finalDriveRatio"), gearList, throttleList);

    }

    private Measurable createVelocityLimit(String name, Unit[] unitEnum) throws SQLException {
        //ToDo esta qualquer coisa mal
        ResultSet limitSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getLimitSet(?)")) {
            callableStatement.setString(1, name);
            limitSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createMeasurable(limitSet, unitEnum);
    }

    private Measurable createMeasurable(ResultSet resultSet, Unit[] unitEnum) throws SQLException {
        Unit unit = null;
        for (Unit unitType : unitEnum) {
            String unitStr = resultSet.getString("unit");
            if (unitStr.equals(unitType.toString())) {
                unit = unitType;
            }
        }
        double quantity = resultSet.getDouble("class");
        return new Measurable(quantity, unit);
    }

    private List<VelocityLimit> fillVelocityLimitList(String name, Unit[] unitEnum) throws SQLException {
        List<VelocityLimit> velocityLimitList = new LinkedList<>();

        ResultSet velocitySet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getVelocitySet(?)")) {
            callableStatement.setString(1, name);
            velocitySet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (velocitySet.next()) {
            String segmentType = velocitySet.getString("segmentType");
            Measurable limit = createVelocityLimit(name, unitEnum);
            VelocityLimit velocityLimit = new VelocityLimit(segmentType, limit);
            velocityLimitList.add(velocityLimit);
        }
        return velocityLimitList;
    }

    private Measurable createWheelSize(String name, Unit[] unitEnum) throws SQLException {
        ResultSet wheelSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getWheelSize(?)")) {
            callableStatement.setString(1, name);
            wheelSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createMeasurable(wheelSet, unitEnum);
    }

    private Measurable createFrontalArea(String name, Unit[] unitEnum) throws SQLException {
        ResultSet areaSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getFrontalAreaSet(?)")) {
            callableStatement.setString(1, name);
            areaSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createMeasurable(areaSet, unitEnum);
    }

    private Measurable createLoad(String name, Unit[] unitEnum) throws SQLException {
        ResultSet loadSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getLoadSet(?)")) {
            callableStatement.setString(1, name);
            loadSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createMeasurable(loadSet, unitEnum);
    }

    private Measurable createMass(String name, Unit[] unitEnum) throws SQLException {
        ResultSet massSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getMassSet(?)")) {
            callableStatement.setString(1, name);
            massSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createMeasurable(massSet, unitEnum);
    }

    private Fuel determineFuel(ResultSet resultSet) throws SQLException {
        Fuel fuel = null;
        Fuel[] fuelEnum = Fuel.values();
        for (Fuel fuelType : fuelEnum) {
            String fuelStr = resultSet.getString("fuelType");
            if (fuelStr.equals(fuelType.toString())) {
                fuel = fuelType;
            }
        }
        return fuel;
    }

    private Vehicle.MotorType determineMotorType(ResultSet resultSet) throws SQLException {
        Vehicle.MotorType motorization = null;
        Vehicle.MotorType[] motorizationEnum = Vehicle.MotorType.values();
        for (Vehicle.MotorType motorizationType : motorizationEnum) {
            String motorizationStr = resultSet.getString("motorType");
            if (motorizationStr.equals(motorizationType.toString())) {
                motorization = motorizationType;
            }
        }
        return motorization;
    }

    private VehicleType determineVehicleType(ResultSet resultSet) throws SQLException {
        VehicleType vehicleType = null;
        VehicleType[] typesEnum = VehicleType.values();
        for (VehicleType type : typesEnum) {
            String typeStr = resultSet.getString("vehicleType");
            if (typeStr.equals(type.toString())) {
                vehicleType = type;
            }
        }
        return vehicleType;
    }

}
