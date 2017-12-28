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
    public List<Vehicle> retrieveVehicles(String projectName) throws SQLException {

        List<Vehicle> vehicles = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call fetchVehiclesFromProject(?)")) {
            callableStatement.setString(1, projectName);
            ResultSet vehicleSet = callableStatement.executeQuery();
            while (vehicleSet.next()) {
                Vehicle vehicle = retrieveVehicle(vehicleSet);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        vehicle = new Vehicle(name, description, vehicleType, vehicleClass, motorization, fuel, mass, load, dragCoefficient, frontalArea, rollingReleaseCoefficient, wheelSize, velocityLimitList, energy);
        return vehicle;
    }

    private List<Regime> fillRegimeList(int throttleID) throws SQLException {
        List<Regime> regimeList = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getRegimeSet(?)")) {
            callableStatement.setInt(1, throttleID);
            ResultSet regimeSet = callableStatement.executeQuery();
            while (regimeSet.next()) {
                Regime regime = new Regime(regimeSet.getInt("torqueLow"), regimeSet.getInt("torqueHigh"), regimeSet.getInt("rpmLow"), regimeSet.getInt("rpmHigh"), regimeSet.getInt("SFC"));
                regimeList.add(regime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return regimeList;
    }

    private List<Throttle> fillThrottleList(int energyID) throws SQLException {
        List<Throttle> throttleList = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getThrottleSet(?)")) {
            callableStatement.setInt(1, energyID);
            ResultSet throttleSet = callableStatement.executeQuery();
            while (throttleSet.next()) {
                int throttleID = throttleSet.getInt("id");
                List<Regime> regimeList = fillRegimeList(throttleID);
                Throttle throttle = new Throttle(throttleID, regimeList);
                throttleList.add(throttle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return throttleList;
    }

    private List<Gears> fillGearList(int energyID) throws SQLException {
        List<Gears> gearList = new LinkedList<>(); //creation of gears needed by energy

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getGearSet(?)")) {
            callableStatement.setInt(1, energyID);
            ResultSet gearsSet = callableStatement.executeQuery();
            while (gearsSet.next()) {
                Gears gear = new Gears(gearsSet.getInt("id"), gearsSet.getFloat("ratio"));
                gearList.add(gear);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gearList;
    }

    private Energy createEnergy(String name) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getEnergySet(?)")) {
            callableStatement.setString(1, name);
            ResultSet energySet = callableStatement.executeQuery();
            int energyID = energySet.getInt("id");
            List<Gears> gearList = fillGearList(energyID);
            List<Throttle> throttleList = fillThrottleList(energyID);
            return new Energy(energySet.getInt("rpmLow"), energySet.getInt("rpmHigh"), energySet.getFloat("finalDriveRatio"), gearList, throttleList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getVelocitySet(?)")) {
            callableStatement.setString(1, name);
            ResultSet velocitySet = callableStatement.executeQuery();
            while (velocitySet.next()) {
                int velocityLimitID = velocitySet.getInt("id");
                String segmentType = velocitySet.getString("segmentType");
                Measurable limit = createVelocityLimit(velocityLimitID, unitEnum);
                VelocityLimit velocityLimit = new VelocityLimit(segmentType, limit);
                velocityLimitList.add(velocityLimit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return velocityLimitList;
    }

    private Measurable createVelocityLimit(int velocityLimitID, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getLimitSet(?)")) {
            callableStatement.setInt(1, velocityLimitID);
            ResultSet limitSet = callableStatement.executeQuery();
            return createMeasurable(limitSet, unitEnum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
