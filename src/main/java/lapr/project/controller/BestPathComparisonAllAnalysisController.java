package lapr.project.controller;

import lapr.project.model.Analysis;
import lapr.project.model.Vehicle;
import lapr.project.ui.Main;
import lapr.project.utils.FileParser.ExportHTML;
import lapr.project.utils.FileParser.Exporter;

import java.io.File;
import java.io.IOException;

/**
 * Controller of BestPathComparisonAllAnalysisUI
 */
public class BestPathComparisonAllAnalysisController {

    /**
     * @param outputFile {@link File} to which is desired to export an {@link Analysis} data
     * @param analysis {@link Analysis} whose data is going to be exported
     * @throws IOException
     */
    public void export(Main.SupportedOutputFileTypes outputFormat, File outputFile, Analysis analysis, Vehicle vehicle) throws IOException {
        Exporter exporter;
        switch (outputFormat) {
            case HTML:
                exporter = new ExportHTML(analysis);
                exporter.exportDataFromAnalysis(outputFile, vehicle);
                break;
        }
    }

}
