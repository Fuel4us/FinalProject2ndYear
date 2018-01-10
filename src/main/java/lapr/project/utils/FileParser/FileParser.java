package lapr.project.utils.FileParser;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;

/**
 * Allows a careful design of the methods used for data input/output, namely using the strategy design pattern.
 * Multiple file formats can be supported, without requiring changes in classes coupled to this interface.
 */
public interface FileParser {

    /**
     * Imports vehicles from a file on to a {@link Project}
     * @param project The project to which read vehicles are to be added
     * @param filename The name of the file containing serialized vehicles
     * @return true if adding was successful
     */
    boolean importVehicles(Project project, String filename);

    /**
     * Reads a {@link RoadNetwork} from a given file
     * @param newNetwork Defines whether a new network is to be created,
     * or if the information in the file is to be added to an existing road network.
     * @return the read {@link RoadNetwork}
     * @throws Exception i.e {@link java.io.FileNotFoundException}
     * and other reading related exceptions that vary with the implementation
     */
    RoadNetwork importNetwork(boolean newNetwork) throws Exception;

}
