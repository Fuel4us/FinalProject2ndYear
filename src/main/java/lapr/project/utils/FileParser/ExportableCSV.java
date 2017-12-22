package lapr.project.utils.FileParser;

import org.antlr.stringtemplate.StringTemplate;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the exportation of multiple objects's data to an CSV file
 */
public interface ExportableCSV {

    void exportDataCSV(StringTemplate stringTemplate1, StringTemplate stringTemplate2, FileWriter file) throws IOException;
}