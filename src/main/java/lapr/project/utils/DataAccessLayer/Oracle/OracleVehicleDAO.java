/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.Connection;
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

    public OracleVehicleDAO() {}

    /**
     * Creates a list of instances of {@link Vehicle} from a given project name
     * @param projectName name of the project
     * @return list of {@link Vehicle}
     * @throws SQLException
     */
    @Override
    public List<Vehicle> retrieveVehicle(String projectName) throws SQLException {
        ResultSet vehicleSet = statement.executeQuery(
                "SELECT * FROM VEHICLE WHERE VEHICLE.PROJECTNAME = PROJECT.NAME AND PROJECT.NAME = projectName;"
        );

        List<Vehicle> vehicles = new LinkedList<>();
        while(vehicleSet.next()) {
            Vehicle vehicle = retrieveVehicle(vehicleSet);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    /**
     * Creates an instance of {@link Vehicle} from a given ResultSet
     * @param resultSet name of the project
     * @return instance of {@link Vehicle}
     * @throws SQLException
     */
    private Vehicle retrieveVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle;
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        VehicleType vehicleType = null;
        Vehicle.MotorType motorization = null;
        Fuel fuel = null;
        int vehicleClass = resultSet.getInt("vehicleTollClass");
        float dragCoefficient = resultSet.getFloat("dragCoefficient");
        float rollingReleaseCoefficient = resultSet.getFloat("rollingReleaseCoefficient");
        List<VelocityLimit> velocityLimitList = new LinkedList<>();
        Energy energy = null;

        //creation of vehicleType
        VehicleType[] typesEnum = VehicleType.values();
        for (VehicleType type : typesEnum) {
            String typeStr = resultSet.getString("vehicleType");
            if (typeStr.equals(type.toString())) {
                vehicleType = type;
            }
        }

        //creation of motorization
        Vehicle.MotorType[] motorizationEnum = Vehicle.MotorType.values();
        for (Vehicle.MotorType motorizationType : motorizationEnum) {
            String motorizationStr = resultSet.getString("motorType");
            if (motorizationStr.equals(motorizationType.toString())) {
                motorization = motorizationType;
            }
        }

        //creation of fuel
        Fuel[] fuelEnum = Fuel.values();
        for (Fuel fuelType : fuelEnum) {
            String fuelStr = resultSet.getString("fuelType");
            if (fuelStr.equals(fuelType.toString())) {
                fuel = fuelType;
            }
        }

        //creation of mass
        ResultSet massSet = statement.executeQuery(
                "SELECT * FROM MEASURABLE WHERE MEASURABLE.ID = VEHICLE.MASS_ID AND VEHICLE.NAME = name;"
        );
        Unit[] unitEnum = Unit.values();
        Unit unit = null;
        for (Unit unitType : unitEnum) {
            String unitStr = massSet.getString("unit");
            if (unitStr.equals(unitType.toString())) {
                unit = unitType;
            }
        }
        double quantity = massSet.getDouble("class");
        Measurable mass = new Measurable(quantity, unit);

        //creation of load
        ResultSet loadSet = statement.executeQuery(
                "SELECT * FROM MEASURABLE WHERE MEASURABLE.ID = VEHICLE.LOAD_ID AND VEHICLE.NAME = name;"
        );
        for (Unit unitType : unitEnum) {
            String unitStr = loadSet.getString("unit");
            if (unitStr.equals(unitType.toString())) {
                unit = unitType;
            }
        }
        quantity = loadSet.getDouble("class");
        Measurable load = new Measurable(quantity, unit);

        //creation of frontal area
        ResultSet areaSet = statement.executeQuery(
                "SELECT * FROM MEASURABLE WHERE MEASURABLE.ID = VEHICLE.FRONTALAREAID AND VEHICLE.NAME = name;"
        );
        for (Unit unitType : unitEnum) {
            String unitStr = areaSet.getString("unit");
            if (unitStr.equals(unitType.toString())) {
                unit = unitType;
            }
        }
        quantity = massSet.getDouble("class");
        Measurable frontalArea = new Measurable(quantity, unit);

        //creation of wheel size
        ResultSet wheelSet = statement.executeQuery(
                "SELECT * FROM MEASURABLE WHERE MEASURABLE.ID = VEHICLE.FRONTALAREA_ID AND VEHICLE.NAME = name;"
        );
        for (Unit unitType : unitEnum) {
            String unitStr = wheelSet.getString("unit");
            if (unitStr.equals(unitType.toString())) {
                unit = unitType;
            }
        }
        quantity = massSet.getDouble("class");
        Measurable wheelSize = new Measurable(quantity, unit);

        //creation of list of velocity limits
        ResultSet velocitySet = statement.executeQuery(
                "SELECT * FROM VELOCITYLIMIT WHERE VELOCITYLIMIT.ID = VEHICLE.VELOCITYLIMITSLISTS_ID AND VEHICLE.NAME = name;"
        );
        while(velocitySet.next()) {
            String segmentType = velocitySet.getString("segment_type");
            ResultSet limitSet = statement.executeQuery(
                    "SELECT * FROM MEASURABLE WHERE MEASURABLE.ID = VEHICLE.LOAD_ID AND VEHICLE.NAME = name;"
            );
            for (Unit unitType : unitEnum) {
                String unitStr = limitSet.getString("unit");
                if (unitStr.equals(unitType.toString())) {
                    unit = unitType;
                }
            }
            quantity = limitSet.getDouble("class");
            Measurable limit = new Measurable(quantity, unit);
            VelocityLimit velocityLimit = new VelocityLimit(segmentType, limit);
            velocityLimitList.add(velocityLimit);
        }

        //creation of energy attribute
        ResultSet energySet = statement.executeQuery(
                "SELECT * FROM ENERGY WHERE ENERGY.ID = VEHICLE.ENERGY_ID AND VEHICLE.NAME = name;"
        );
        int energyID = energySet.getInt("id");
        List<Gears> gearList = new LinkedList<>(); //creation of gears needed by energy
        ResultSet gearsSet = statement.executeQuery(
                "SELECT * FROM GEARS WHERE GEARS.ENERGY_ID = ENERGY.ID AND ENERGY.ID = energyID;"
        );
        while (gearsSet.next()){
            Gears gear = new Gears(gearsSet.getInt("id"), gearsSet.getFloat("ratio"));
            gearList.add(gear);
        }
        List<Throttle> throttleList = new LinkedList<>(); //creation of throttles needed by energy
        ResultSet throttleSet = statement.executeQuery(
                "SELECT * FROM THROTTLE WHERE THROTTLE.ENERGY_ID = ENERGY.ID AND ENERGY.ID = energyID;"
        );
        while (throttleSet.next()){
            int throttleID = throttleSet.getInt("id");
            ResultSet regimeSet = statement.executeQuery(
                    "SELECT * FROM REGIME WHERE REGIME.THROTTLE_ID = THROTTLE.ID AND THROTTLE.ID = throttleID;"
            );
            List<Regime> regimeList = new LinkedList<>();
            while (regimeSet.next()){
                Regime regime = new Regime(regimeSet.getInt("torqueLow"), regimeSet.getInt("torqueHigh"), regimeSet.getInt("rpm_low"), regimeSet.getInt("rpm_high"), regimeSet.getInt("SFC"));
                regimeList.add(regime);
            }
            Throttle throttle = new Throttle(throttleID, regimeList);
            throttleList.add(throttle);
        }
        energy = new Energy(energySet.getInt("rpm_low"), energySet.getInt("rpm_high"), energySet.getFloat("final_drive_ratio"), gearList, throttleList);

        //creation of vehicle
        vehicle = new Vehicle(name, description, vehicleType, vehicleClass, motorization, fuel, mass, load, dragCoefficient, frontalArea, rollingReleaseCoefficient, wheelSize, velocityLimitList, energy);
        return vehicle;
    }

}
