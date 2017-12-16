/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.*;

/**
 *
 * @author goncalo
 */
public class OracleVehicleDAO extends OracleDBAccessor implements VehicleDAO {
    
    private PreparedStatement statement;

    public OracleVehicleDAO() {
        super();
        try {
            statement = oracleConnection.prepareStatement("");
        } catch (SQLException e) {
            super.logSQLException(e);
        }
    }
    
    

    @Override
    public void storeVehicle(Vehicle vehicle) {
        
    }
    
}
