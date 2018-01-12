package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.RoadNetwork;

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
    RoadNetwork retrieveRoadNetwork(String projectName) throws SQLException;

    /**
     * Stores a {@link RoadNetwork} in database
     * @param roadNetwork instance of {@link RoadNetwork}
     * @param projectName identifier of {@link lapr.project.model.Project}
     * @throws SQLException
     */
    void storeRoadNetwork(RoadNetwork roadNetwork, String projectName) throws SQLException;

}
