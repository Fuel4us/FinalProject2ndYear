package lapr.project.utils.FileParser;

import java.io.File;
import java.io.IOException;

/**
 * Handles exportation of an analysis result to a file
 */
public interface Exporter {

    void printDataFromAnalysis(File file) throws IOException;

}
