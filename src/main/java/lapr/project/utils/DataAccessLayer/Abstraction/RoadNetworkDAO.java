package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.RoadNetwork.RoadNetwork;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Allows for different implementation of methods that fetch a RoadNetwork for a given project from the database, for different databases
 */
public interface RoadNetworkDAO extends DataAccessObject {

    /**
     * Creates an instance of {@link RoadNetwork} from a given project name
     * @param projectName name of the project
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    RoadNetwork createRoadNetwork(String projectName) throws SQLException;

    /**
     * Creates an instance of {@link RoadNetwork} from a given ResultSet of project entities
     * @param resultSet ResultSet object
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    RoadNetwork createRoadNetwork(ResultSet resultSet) throws SQLException;

}
