package lapr.project.utils.FileParser;

import lapr.project.model.Project;
/**
 * Allows a careful design of the methods used for data input/output, namely using the strategy design pattern
 */
public interface FileParser {
    public boolean importVehicles(Project object, String filename);
}
