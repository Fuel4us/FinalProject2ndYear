package lapr.project.utils.FileParser;

import lapr.project.model.Vehicle;
import org.antlr.stringtemplate.StringTemplate;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the exportation of multiple objects's data to an HTML file
 */
public interface ExportableHTML {

    void exportDataHTML(StringTemplate stringTemplate1, StringTemplate stringTemplate2, FileWriter file, Vehicle vehicle) throws IOException;
}

