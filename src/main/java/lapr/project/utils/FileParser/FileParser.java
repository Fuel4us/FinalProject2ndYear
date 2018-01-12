package lapr.project.utils.FileParser;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Vehicle;

import java.io.IOException;
import java.util.List;

/**
 * Allows a careful design of the methods used for data input/output, namely using the strategy design pattern.
 * Multiple file formats can be supported, without requiring changes in classes coupled to this interface.
 */
public interface FileParser {

    /**
     * Imports vehicles from a file on to a {@link Project}
     * @return true if adding was successful
     * @throws Exception i.e {@link IOException} and other reading
     * related exceptions that vary with the implementation
     */
    List<Vehicle> importVehicles() throws Exception;

    /**
     * Reads a {@link RoadNetwork} from a given file
     * @param newNetwork Defines whether a new network is to be created,
     * or if the information in the file is to be added to an existing road network.
     * @return the read {@link RoadNetwork}
     * @throws Exception i.e {@link IOException} and other reading
     * related exceptions that vary with the implementation
     */
    RoadNetwork importNetwork(boolean newNetwork) throws Exception;

}
