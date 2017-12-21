package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import lapr.project.model.Analysis;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.model.Project;
import lapr.project.utils.FileParser.ExportHTML;

/**
 * Connects UI events to Model classes
 */
public class NetworkAnalysisController {

    private Project project;
    private DataBaseCommunicator DBCom;
    private Analysis generatedAnalysis;
    private ExportHTML exp;

    public NetworkAnalysisController(Project project, DataBaseCommunicator DBCom, Analysis generatedAnalysis) {
        this.project = project;
        this.DBCom = DBCom;
        this.generatedAnalysis = generatedAnalysis;
        this.exp = new ExportHTML(generatedAnalysis);
    }

    /**
     * Stores generated network analysis to current project
     * @return true if storing was successful
     */
    public boolean storeGeneratedNetworkAnalysis() {
        return DBCom.storeNetworkAnalysis(generatedAnalysis);
    }
    
    public void exportData(File output) throws IOException{
        exp.printDataFromAnalysis(output);
    }
    
}
