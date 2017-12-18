package lapr.project.controller;

import lapr.project.model.Analysis;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.model.Project;

/**
 * Connects UI events to Model classes
 */
public class NetworkAnalysisController {

    private Project project;
    private DataBaseCommunicator DBCom;
    private Analysis generatedAnalysis;

    public NetworkAnalysisController(Project project, DataBaseCommunicator DBCom, Analysis generatedAnalysis) {
        this.project = project;
        this.DBCom = DBCom;
        this.generatedAnalysis = generatedAnalysis;
    }

    /**
     * Stores generated network analysis to current project
     * @return true if storing was successful
     */
    public void storeGeneratedNetworkAnalysis() {
        DBCom.storeNetworkAnalysis(generatedAnalysis);
    }
    
}
