/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Vehicle.*;
import lapr.project.utils.DataAccessLayer.Abstraction.VehicleDAO;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

/**
 * Retrieves instances of Vehicle for a given project
 */
public class OracleVehicleDAO extends OracleDAO implements VehicleDAO {



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

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL fetchVehiclesFromProject(?)")) {
            callableStatement.setString(1, projectName);
            ResultSet vehicleSet = callableStatement.executeQuery();
            while (vehicleSet.next()) {
                Vehicle vehicle = retrieveVehicle(vehicleSet);
                vehicles.add(vehicle);
            }
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

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getRegimeSet(?)")) {
            callableStatement.setInt(1, throttleID);
            ResultSet regimeSet = callableStatement.executeQuery();
            while (regimeSet.next()) {
                Regime regime = new Regime(regimeSet.getInt("torqueLow"), regimeSet.getInt("torqueHigh"), regimeSet.getInt("rpmLow"), regimeSet.getInt("rpmHigh"), regimeSet.getInt("SFC"));
                regimeList.add(regime);
            }
        }

        return regimeList;
    }

    private List<Throttle> fillThrottleList(int energyID) throws SQLException {
        List<Throttle> throttleList = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getThrottleSet(?)")) {
            callableStatement.setInt(1, energyID);
            ResultSet throttleSet = callableStatement.executeQuery();
            while (throttleSet.next()) {
                int throttleID = throttleSet.getInt("id");
                List<Regime> regimeList = fillRegimeList(throttleID);
                Throttle throttle = new Throttle(throttleID, regimeList);
                throttleList.add(throttle);
            }
        }

        return throttleList;
    }

    private List<Gears> fillGearList(int energyID) throws SQLException {
        List<Gears> gearList = new LinkedList<>(); //creation of gears needed by energy

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getGearSet(?)")) {
            callableStatement.setInt(1, energyID);
            ResultSet gearsSet = callableStatement.executeQuery();
            while (gearsSet.next()) {
                Gears gear = new Gears(gearsSet.getInt("id"), gearsSet.getFloat("ratio"));
                gearList.add(gear);
            }
        }

        return gearList;
    }

    private Energy createEnergy(String name) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getEnergySet(?)")) {
            callableStatement.setString(1, name);
            ResultSet energySet = callableStatement.executeQuery();
            int energyID = energySet.getInt("id");
            List<Gears> gearList = fillGearList(energyID);
            List<Throttle> throttleList = fillThrottleList(energyID);
            return new Energy(energySet.getInt("rpmLow"), energySet.getInt("rpmHigh"), energySet.getFloat("finalDriveRatio"), gearList, throttleList);
        }
    }

    private Measurable createMeasurable(ResultSet resultSet, Unit[] unitEnum) throws SQLException {
        for (Unit unitType : unitEnum) {
            String unitStr = resultSet.getString("unit");
            if (unitStr.equals(unitType.toString())) {
                Unit unit = unitType;
                double quantity = resultSet.getDouble("class");
                return new Measurable(quantity, unit);
            }
        }
        return null;
    }

    private List<VelocityLimit> fillVelocityLimitList(String name, Unit[] unitEnum) throws SQLException {
        List<VelocityLimit> velocityLimitList = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getVelocitySet(?)")) {
            callableStatement.setString(1, name);
            ResultSet velocitySet = callableStatement.executeQuery();
            while (velocitySet.next()) {
                int velocityLimitID = velocitySet.getInt("id");
                String segmentType = velocitySet.getString("segmentType");
                Measurable limit = createVelocityLimit(velocityLimitID, unitEnum);
                VelocityLimit velocityLimit = new VelocityLimit(segmentType, limit);
                velocityLimitList.add(velocityLimit);
            }
        }

        return velocityLimitList;
    }

    private Measurable createVelocityLimit(int velocityLimitID, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getLimitSet(?)")) {
            callableStatement.setInt(1, velocityLimitID);
            ResultSet limitSet = callableStatement.executeQuery();
            return createMeasurable(limitSet, unitEnum);
        }
    }


    private Measurable createWheelSize(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getWheelSize(?)")) {
            callableStatement.setString(1, name);
            ResultSet wheelSet = callableStatement.executeQuery();
            return createMeasurable(wheelSet, unitEnum);
        }
    }

    private Measurable createFrontalArea(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getFrontalAreaSet(?)")) {
            callableStatement.setString(1, name);
            ResultSet areaSet = callableStatement.executeQuery();
            return createMeasurable(areaSet, unitEnum);
        }

    }

    private Measurable createLoad(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getLoadSet(?)")) {
            callableStatement.setString(1, name);
            ResultSet loadSet = callableStatement.executeQuery();
            return createMeasurable(loadSet, unitEnum);
        }

    }

    private Measurable createMass(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getMassSet(?)")) {
            callableStatement.setString(1, name);
            ResultSet massSet = callableStatement.executeQuery();
            return createMeasurable(massSet, unitEnum);
        }

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

    /**
     * Stores information of RoadNetwork
     * @param vehicle the {@link Vehicle} to store
     * @param projectName identifier of {@link lapr.project.model.Project}
     * @throws SQLException
     */
    void storeVehicleInfo(Vehicle vehicle, String projectName) throws SQLException {

        try (CallableStatement storeVehicleInfoProcedure = oracleConnection.prepareCall("CALL storeVehicleInfoProcedure(?,?,?,?,?,?,?,?,?,?,?,?,?)")) {

            storeVehicleInfoProcedure.setString("name", vehicle.getName());
            storeVehicleInfoProcedure.setString("projectName", projectName);

            vehicle.storeVehicleInformation(storeVehicleInfoProcedure);

            storeVehicleInfoProcedure.setInt("massID", storeStatisticalInfo(vehicle.getMass()));
            storeVehicleInfoProcedure.setInt("maxLoadID", storeStatisticalInfo(vehicle.getMaxLoad()));
            storeVehicleInfoProcedure.setInt("frontalAreaID", storeStatisticalInfo(vehicle.getFrontalArea()));
            storeVehicleInfoProcedure.setInt("wheelSizeID", storeStatisticalInfo(vehicle.getWheelSize()));

            storeVehicleInfoProcedure.setInt("energyID", storeEnergyInfo(vehicle.getEnergy()));

            List<VelocityLimit> velocityLimitList = vehicle.getVelocityLimitList();
            for (VelocityLimit velocityLimit : velocityLimitList) {
                storeVelocityLimit(vehicle.getName(), velocityLimit);
            }

            storeVehicleInfoProcedure.executeUpdate();
        }
    }

    /**
     * Stores information of {@link VelocityLimit}
     * @param name vehicle name
     */
    private void storeVelocityLimit(String name, VelocityLimit velocityLimit) throws SQLException {
        try (CallableStatement storeVelocityLimitProcedure = oracleConnection.prepareCall("CALL storeVelocityLimitProcedure(?,?,?)")) {
            storeVelocityLimitProcedure.setString("segmentType", velocityLimit.getSegmentType());
            storeVelocityLimitProcedure.setInt("limitID", storeStatisticalInfo(velocityLimit.getLimit()));
            storeVelocityLimitProcedure.setString("vehicleName", name);

            storeVelocityLimitProcedure.executeUpdate();
        }
    }

    /**
     * Stores information of {@link Energy} and belonging objects information
     * @param energy instance of {@link Energy}
     * @return {@link int} identifier of entity
     */
    private int storeEnergyInfo(Energy energy) throws SQLException {

        try (CallableStatement storeEnergyFunction = oracleConnection
                .prepareCall("{? = call STOREENERGYFUNCTION(?,?,?,?)}")) {

            storeEnergyFunction.registerOutParameter(1, Types.INTEGER);

            storeEnergyFunction.setInt("rpmLow", energy.getMinRpm());
            storeEnergyFunction.setInt("rpmHigh", energy.getMaxRpm());
            storeEnergyFunction.setFloat("finalDriveRatio", energy.getFinalDriveRatio());
            storeEnergyFunction.setFloat("energyFinalRatio", energy.getEnergyFinalRatio());

            List<Gears> gears = energy.getGears();
            for (Gears gear : gears) {
                storeGear(gear, storeEnergyFunction.getInt(1));
            }
            List<Throttle> throttles = energy.getThrottles();
            for (Throttle throttle : throttles) {
                storeThrottle(throttle, storeEnergyFunction.getInt(1));
            }

            storeEnergyFunction.executeUpdate();

            return storeEnergyFunction.getInt(1);
        }
    }

    /**
     * Stores energy gears
     * @param gear instance of {@link Gears}
     * @param energyID identifier of Energy entity
     */
    private void storeGear(Gears gear, int energyID) {
    }

    /**
     * Stores energy throttle
     * @param throttle instance of {@link Throttle}
     * @param energyID identifier of Energy entity
     */
    private void storeThrottle(Throttle throttle, int energyID) {
    }

}
