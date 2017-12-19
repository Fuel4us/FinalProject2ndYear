package lapr.project.utils.FileParser;
import lapr.project.model.Analysis;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles exportation of an analysis result to a HTML file
 */
public class ExportHTML implements Exporter {

    private Analysis analysis;
    private File outputFile = new File("C:\\Users\\anily\\Desktop\\Escolar\\RITA isep\\2º ano\\lapr3\\outputTest.html");
    private static String HTML_STRUCTURE_MAIN = "html_structure_main";


    /**
     * Prints data from analysis filling the information missing in a given file template
     */
    @Override
    public void printDataFromAnalysis() throws IOException {
        FileWriter fillFile = new FileWriter(outputFile, true);



        StringTemplateGroup groupSegment =  new StringTemplateGroup("src\\main\\resources");
        StringTemplate stringTemplate = groupSegment.getInstanceOf(HTML_STRUCTURE_MAIN);
        analysis.printDataFromAnalysis(stringTemplate, fillFile);
    }

}
