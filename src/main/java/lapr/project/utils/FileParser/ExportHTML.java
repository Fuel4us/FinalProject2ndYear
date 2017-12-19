package lapr.project.utils.FileParser;
import lapr.project.model.Analysis;
import org.antlr.stringtemplate.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles exportation of an analysis result to a HTML file
 */
public class ExportHTML implements Exporter {

    private Analysis analysis;
    private static String HTML_STRUCTURE_MAIN = "html_structure_main";

    public ExportHTML(Analysis analysis) {
        this.analysis = analysis;
    }

    /**
     * Prints data from analysis filling the information missing in a given file template
     */
    @Override
    public void printDataFromAnalysis(File outputFile) throws IOException {
        FileWriter fillFile = new FileWriter(outputFile, true);

        StringTemplateGroup groupSegment =  new StringTemplateGroup("src\\main\\resources");
        StringTemplate stringTemplate = groupSegment.getInstanceOf(HTML_STRUCTURE_MAIN);
        analysis.printDataFromAnalysis(stringTemplate, fillFile);
    }

}
