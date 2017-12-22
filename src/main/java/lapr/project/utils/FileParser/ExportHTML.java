package lapr.project.utils.FileParser;
import lapr.project.model.Analysis;
import org.antlr.stringtemplate.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles exportation of an object to a HTML file
 */
public class ExportHTML implements Exporter {

    private Analysis analysis;
    private static String HTML_STRUCTURE_FIRST = "html_structure_first";
    private static String HTML_STRUCTURE_SECOND = "html_structure_second";

    public ExportHTML(Analysis analysis) {
        this.analysis = analysis;
    }

    /**
     * Exports data from analysis filling the information missing in a given file template
     */
    @Override
    public void exportDataFromAnalysis(File outputFile) throws IOException {
        FileWriter fillFile = new FileWriter(outputFile, true);

        StringTemplateGroup groupHTML =  new StringTemplateGroup("src\\main\\resources");
        StringTemplate stringTemplateFirst = groupHTML.getInstanceOf(HTML_STRUCTURE_FIRST);
        StringTemplate stringTemplateSecond = groupHTML.getInstanceOf(HTML_STRUCTURE_SECOND);
        analysis.exportDataHTML(stringTemplateFirst, stringTemplateSecond, fillFile);

    }

}
