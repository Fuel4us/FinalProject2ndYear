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

        verifyConnection();

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

    /**
     * Retrieves {@link Regime} from database and adds them to a given {@link Throttle}
     * @param throttleID identifier of {@link Throttle}
     * @return {@link List} of {@link Regime}
     * @throws SQLException
     */
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

    /**
     * Retrieves {@link Throttle} from database and adds them to a given {@link Energy}
     * @param energyID identifier of {@link Energy}
     * @return {@link List} of {@link Throttle}
     * @throws SQLException
     */
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

    /**
     * Retrieves {@link Gears} from database and adds them to a given {@link Energy}
     * @param energyID identifier of {@link Energy}
     * @return {@link List} of {@link Gears}
     * @throws SQLException
     */
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

    /**
     * Retrieves and creates {@link Energy} from database
     * @param name identifier of {@link Vehicle}
     * @return {@link Energy}
     * @throws SQLException
     */
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

    /**
     * Retrieves any kind of {@link Measurable} from the database
     * @param resultSet a {@link ResultSet}
     * @param unitEnum {@link Unit} enum
     * @return created {@link Measurable}
     * @throws SQLException
     */
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

    /**
     * Retrieves from database and creates a {@link List} of {@link VelocityLimit}
     * @param name identifier of {@link Vehicle}
     * @param unitEnum {@link Unit} enum
     * @return {@link List} of {@link VelocityLimit}
     * @throws SQLException
     */
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

    /**
     * Retrieves limit attribute from {@link VelocityLimit}
     * @param velocityLimitID {@link VelocityLimit} identifier
     * @param unitEnum {@link Unit} enum
     * @return {@link Measurable}, limit of {@link VelocityLimit}
     * @throws SQLException
     */
    private Measurable createVelocityLimit(int velocityLimitID, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getLimitSet(?)")) {
            callableStatement.setInt(1, velocityLimitID);
            ResultSet limitSet = callableStatement.executeQuery();
            return createMeasurable(limitSet, unitEnum);
        }
    }

    /**
     * Retrieves wheelSize attribute from {@link Vehicle}
     * @param name {@link Vehicle} identifier
     * @param unitEnum {@link Unit} enum
     * @return {@link Measurable}, wheelSize of {@link Vehicle}
     * @throws SQLException
     */
    private Measurable createWheelSize(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getWheelSize(?)")) {
            callableStatement.setString(1, name);
            ResultSet wheelSet = callableStatement.executeQuery();
            return createMeasurable(wheelSet, unitEnum);
        }
    }

    /**
     * Retrieves frontalArea attribute from {@link Vehicle}
     * @param name {@link Vehicle} identifier
     * @param unitEnum {@link Unit} enum
     * @return {@link Measurable}, frontalArea of {@link Vehicle}
     * @throws SQLException
     */
    private Measurable createFrontalArea(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getFrontalAreaSet(?)")) {
            callableStatement.setString(1, name);
            ResultSet areaSet = callableStatement.executeQuery();
            return createMeasurable(areaSet, unitEnum);
        }

    }

    /**
     * Retrieves load attribute from {@link Vehicle}
     * @param name {@link Vehicle} identifier
     * @param unitEnum {@link Unit} enum
     * @return {@link Measurable}, load of {@link Vehicle}
     * @throws SQLException
     */
    private Measurable createLoad(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getLoadSet(?)")) {
            callableStatement.setString(1, name);
            ResultSet loadSet = callableStatement.executeQuery();
            return createMeasurable(loadSet, unitEnum);
        }

    }

    /**
     * Retrieves mass attribute from {@link Vehicle}
     * @param name {@link Vehicle} identifier
     * @param unitEnum {@link Unit} enum
     * @return {@link Measurable}, mass of {@link Vehicle}
     * @throws SQLException
     */
    private Measurable createMass(String name, Unit[] unitEnum) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getMassSet(?)")) {
            callableStatement.setString(1, name);
            ResultSet massSet = callableStatement.executeQuery();
            return createMeasurable(massSet, unitEnum);
        }

    }

    /**
     * Creates {@link Fuel} from {@link Vehicle} column "fuelType"
     * @param resultSet {@link ResultSet}
     * @return {@link Fuel}
     * @throws SQLException
     */
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

    /**
     * Creates {@link lapr.project.model.Vehicle.Vehicle.MotorType} from {@link Vehicle} column "motorType"
     * @param resultSet {@link ResultSet}
     * @return {@link lapr.project.model.Vehicle.Vehicle.MotorType}
     * @throws SQLException
     */
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

    /**
     * Creates {@link VehicleType} from {@link Vehicle} column "vehicleType"
     * @param resultSet {@link ResultSet}
     * @return {@link VehicleType}
     * @throws SQLException
     */
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
     * Stores information of current {@link Vehicle} and of associated objects
     * @param vehicle the {@link Vehicle} to store
     * @param projectName identifier of {@link lapr.project.model.Project}
     * @throws SQLException
     */
    @Override
    public void storeVehicleInfo(Vehicle vehicle, String projectName) throws SQLException {

        storeVehicle(vehicle, projectName);

        List<VelocityLimit> velocityLimitList = vehicle.getVelocityLimitList();
        for (VelocityLimit velocityLimit : velocityLimitList) {
            storeVelocityLimit(vehicle.getName(), velocityLimit);
        }

    }

    /**
     * Stores information of current {@link Vehicle}
     * @param vehicle the {@link Vehicle} to store
     * @param projectName identifier of {@link lapr.project.model.Project}
     * @throws SQLException
     */
    private void storeVehicle(Vehicle vehicle, String projectName) throws SQLException {

        try (CallableStatement storeVehicleInfoProcedure = oracleConnection.prepareCall("CALL storeVehicleInfoProcedure(?,?,?,?,?,?,?,?,?,?,?,?,?)")) {

            storeVehicleInfoProcedure.setString("name", vehicle.getName());
            storeVehicleInfoProcedure.setString("projectName", projectName);

            vehicle.storeVehicleInformation(storeVehicleInfoProcedure);

            storeVehicleInfoProcedure.setInt("massID", storeStatisticalInfo(vehicle.getMass()));
            storeVehicleInfoProcedure.setInt("maxLoadID", storeStatisticalInfo(vehicle.getMaxLoad()));
            storeVehicleInfoProcedure.setInt("frontalAreaID", storeStatisticalInfo(vehicle.getFrontalArea()));
            storeVehicleInfoProcedure.setInt("wheelSizeID", storeStatisticalInfo(vehicle.getWheelSize()));

            storeVehicleInfoProcedure.setInt("energyID", storeEnergyInfo(vehicle.getEnergy()));

            storeVehicleInfoProcedure.executeUpdate();
        }
    }

    /**
     * Stores information of {@link VelocityLimit}
     * @param name {@link Vehicle} identifier
     * @param velocityLimit {@link VelocityLimit}
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
        int energyID = storeEnergy(energy);

        List<Gears> gears = energy.getGears();
        for (Gears gear : gears) {
            storeGear(gear, energyID);
        }
        List<Throttle> throttles = energy.getThrottles();
        for (Throttle throttle : throttles) {
            storeThrottleInfo(throttle, energyID);
        }
        return energyID;
    }

    /**
     * Stores {@link Energy}
     * @param energy instance of {@link Energy}
     * @return identifier of entity
     */
    private int storeEnergy(Energy energy) throws SQLException {

        try (CallableStatement storeEnergyFunction = oracleConnection
                .prepareCall("{? = call STOREENERGYFUNCTION(?,?,?)}")) {

            storeEnergyFunction.registerOutParameter(1, Types.INTEGER);

            storeEnergyFunction.setInt(2, energy.getMinRpm());
            storeEnergyFunction.setInt(3, energy.getMaxRpm());
            storeEnergyFunction.setFloat(4, energy.getFinalDriveRatio());

            storeEnergyFunction.executeUpdate();

            return storeEnergyFunction.getInt(1);
        }
    }

    /**
     * Stores energy gears
     * @param gear instance of {@link Gears}
     * @param energyID identifier of Energy entity
     */
    private void storeGear(Gears gear, int energyID) throws SQLException {
        try (CallableStatement storeGearsProcedure = oracleConnection.prepareCall("CALL storeGearsProcedure(?,?,?)")) {
            storeGearsProcedure.setInt("id", gear.getId());
            storeGearsProcedure.setFloat("ratio", gear.getRatio());
            storeGearsProcedure.setInt("energyID", energyID);

            storeGearsProcedure.executeUpdate();
        }

    }

    /**
     * Stores energy {@link Throttle} information and information of associated objects
     * @param throttle instance of {@link Throttle}
     * @param energyID identifier of Energy entity
     */
    private void storeThrottleInfo(Throttle throttle, int energyID) throws SQLException {
        storeThrottle(throttle, energyID);

        List<Regime> regimes = throttle.getRegimes();
        for (Regime regime : regimes) {
            storeRegime(regime, throttle.getId(), energyID);
        }
    }

    /**
     * Stores energy {@link Throttle}
     * @param throttle instance of {@link Throttle}
     * @param energyID identifier of Energy entity
     */
    private void storeThrottle(Throttle throttle, int energyID) throws SQLException {
        try (CallableStatement storeThrottleProcedure = oracleConnection.prepareCall("CALL storeThrottleProcedure(?,?)")) {

            storeThrottleProcedure.setInt("id", throttle.getId());
            storeThrottleProcedure.setInt("energyID", energyID);

            storeThrottleProcedure.executeUpdate();
        }
    }

    /**
     * Stores throttle regime
     * @param regime instance of {@link Regime}
     * @param throttleID throttle identifier
     */
    private void storeRegime(Regime regime, int throttleID, int energyID) throws SQLException {
        try (CallableStatement storeRegimeProcedure = oracleConnection.prepareCall("CALL storeRegimeProcedure(?,?,?,?,?,?,?)")) {

            storeRegimeProcedure.setInt("torqueLow", regime.getTorqueLow());
            storeRegimeProcedure.setInt("torqueHigh", regime.getTorqueHigh());
            storeRegimeProcedure.setInt("rpmLow", regime.getRpmLow());
            storeRegimeProcedure.setInt("rpmHigh", regime.getRpmHigh());
            storeRegimeProcedure.setDouble("SFC", regime.getSFC());
            storeRegimeProcedure.setInt("throttleID", throttleID);
            storeRegimeProcedure.setInt("energyID", energyID);

            storeRegimeProcedure.executeUpdate();
        }
    }

}
