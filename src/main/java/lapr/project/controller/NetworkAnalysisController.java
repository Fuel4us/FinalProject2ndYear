package lapr.project.controller;

import lapr.project.model.Analysis;
import lapr.project.model.Vehicle;
import lapr.project.ui.Main;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.utils.FileParser.ExportHTML;
import lapr.project.utils.FileParser.Exporter;

import java.io.File;
import java.io.IOException;

/**
 * Connects UI events to Model classes
 */
public class NetworkAnalysisController {

    private DataBaseCommunicator DBCom;
    private Analysis generatedAnalysis;
    private Exporter exporter;
    private Vehicle vehicle;

    /**
     * Creates a {@link NetworkAnalysisController} with a {@link DataBaseCommunicator} and a generated {@link Analysis}
     * Defaults exporting mode to HTML but can be changed
     */
    public NetworkAnalysisController(DataBaseCommunicator dbCom, Analysis generatedAnalysis, Vehicle vehicle) {
        this.DBCom = dbCom;
        this.generatedAnalysis = generatedAnalysis;
        this.exporter = new ExportHTML(generatedAnalysis);
        this.vehicle = vehicle;
    }

    /**
     * Stores generated network analysis to current project
     * @return true if storing was successful
     */
    public boolean storeGeneratedNetworkAnalysis() {
        return DBCom.storeNetworkAnalysis(generatedAnalysis);
    }

    public void exportData(File output) throws IOException {
        exporter.exportDataFromAnalysis(output, vehicle);
    }

    /**
     * Sets the output mode, so that a specific type of file format can be exported
     * @param fileFormat the file format to which to export
     */
    public void setOutputFormat(Main.SupportedOutputFileTypes fileFormat) {
        switch (fileFormat) {
            case HTML:
                exporter = new ExportHTML(this.generatedAnalysis);
                break;
        }
    }

}
