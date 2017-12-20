package lapr.project.utils.FileParser;

import org.antlr.stringtemplate.StringTemplate;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the exportation of analysis data to files of multiple formats
 */
public interface Exportable {

    void printDataFromAnalysis(StringTemplate stringTemplate1, StringTemplate stringTemplate2, FileWriter file) throws IOException;
}

