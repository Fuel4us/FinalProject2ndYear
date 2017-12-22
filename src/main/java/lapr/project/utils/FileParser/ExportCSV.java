package lapr.project.utils.FileParser;
import lapr.project.model.Analysis;
import org.antlr.stringtemplate.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles exportation of an object to a HTML file
 */
public class ExportCSV implements Exporter {

    private Analysis analysis;
    private static String CSV_STRUCTURE_FIRST = "csv_structure_first";
    private static String CSV_STRUCTURE_SECOND = "csv_structure_second";

    public ExportCSV(Analysis analysis) {
        this.analysis = analysis;
    }

    /**
     * Exports data from analysis filling the information missing in a given file template
     */
    @Override
    public void exportDataFromAnalysis(File outputFile) throws IOException {
        FileWriter fillFile = new FileWriter(outputFile, true);

        StringTemplateGroup groupHTML =  new StringTemplateGroup("src\\main\\resources");
        StringTemplate stringTemplateFirst = groupHTML.getInstanceOf(CSV_STRUCTURE_FIRST);
        StringTemplate stringTemplateSecond = groupHTML.getInstanceOf(CSV_STRUCTURE_SECOND);
        analysis.exportDataCSV(stringTemplateFirst, stringTemplateSecond, fillFile);

    }

}
