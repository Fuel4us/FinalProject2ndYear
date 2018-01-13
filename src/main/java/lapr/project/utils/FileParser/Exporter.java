package lapr.project.utils.FileParser;

import lapr.project.model.Vehicle;

import java.io.File;
import java.io.IOException;

/**
 * Handles exportation of an analysis result to a file
 */
public interface Exporter {

    void exportDataFromAnalysis(File file, Vehicle vehicle) throws IOException;

}
