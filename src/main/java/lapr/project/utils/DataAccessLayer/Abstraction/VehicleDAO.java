package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.Vehicle.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Allows for different implementation of methods that fetch instances of Vehicle for a given project from the database, for different databases
 */
public interface VehicleDAO extends DataAccessObject {

    /**
     * Creates a list of instances of {@link Vehicle} from a given project name
     * @param projectName name of the project
     * @return list of {@link Vehicle}
     * @throws SQLException
     */
    List<Vehicle> retrieveVehicles(String projectName) throws SQLException;

}
