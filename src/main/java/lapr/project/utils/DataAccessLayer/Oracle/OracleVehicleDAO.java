///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr.project.utils.DataAccessLayer.Oracle;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import lapr.project.model.Vehicle.*;
//import lapr.project.utils.DataAccessLayer.Abstraction.*;
//import lapr.project.utils.Measurable;
//
///**
// *
// * @author goncalo
// */
//public class OracleVehicleDAO extends OracleDBAccessor implements VehicleDAO {
//
//    private PreparedStatement statement;
//
//    public OracleVehicleDAO() {
//        super();
//        try {
//            statement = oracleConnection.prepareStatement("");
//        } catch (SQLException e) {
//            super.logSQLException(e);
//        }
//    }
//
//
//    @Override
//    public Vehicle createVehicle(ResultSet resultSet) throws SQLException {
//        Vehicle vehicle;
//        String name = resultSet.getString("name");
//        String description = resultSet.getString("description");
//        VehicleType vehicleType;
//        TollClass vehicleClass;
//        Motorization motorization;
//        Fuel fuel;
//        Measurable mass;
//        Measurable load;
//        double dragCoefficient = resultSet.getDouble("dragCoefficient");
//        double frontalRear;
//        double rollingReleaseCoefficient;
//        double wheelSize;
//        List<VelocityLimitList> velocityLimitList;
//        Energy energy;
//
//
//
//        VehicleType[] typesEnum = VehicleType.values();
//        for (VehicleType type : typesEnum) {
//            String stringSet = resultSet.getString("vehicleType");
//            if (stringSet.equals(type.toString())) {
//                vehicleType = type;
//            }
//        }
//
//        vehicle = new Vehicle(name, description, vehicleType, vehicleClass, motorization, fuel, mass, load, dragCoefficient, frontalRear, rollingReleaseCoefficient, wheelSize, velocityLimitList, energy);
//            return vehicle;
//    }
//
//}
