/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.Vehicle.*;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface VehicleDAO {

    /**
     * Creates an instance of Vehicle from a ResultSet
     * @param resultSet list of entities Vehicle
     * @return Vehicle instance
     */
    Vehicle createVehicle(ResultSet resultSet) throws SQLException;

}
