package lapr.project.controller;

import lapr.project.model.Analysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.utils.FileParser.ExportHTML;
import lapr.project.utils.Measurable;
import lapr.project.utils.pathAlgorithm.PathAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller of BestPathComparisonAllAnalysisUI
 */
public class BestPathComparisonAllAnalysisController {

    private Project project;
    private DataBaseCommunicator dbCom;
    private File outputFile;
    private List<Analysis> analysisList;

    /**
     * Constructor
     * @param project instance of {@link Project}
     * @param dbCom instance of {@link DataBaseCommunicator}
     */
    public BestPathComparisonAllAnalysisController(Project project, DataBaseCommunicator dbCom) {
        this.project = project;
        this.dbCom = dbCom;
    }

    /**
     * Stores generated network analysis to current project
     * @return true if storing was successful
     */
    public boolean storeGeneratedNetworkAnalysis(Analysis generatedAnalysis) {
        return dbCom.storeNetworkAnalysis(generatedAnalysis);
    }

    /**
     *
     * @param outputFile {@link File} to which is desired to export an {@link Analysis} data
     * @param analysis {@link Analysis} whose data is going to be exported
     * @throws IOException
     */
    public void exportToHtml(File outputFile, Analysis analysis, Vehicle vehicle) throws IOException {
        this.outputFile = outputFile;
        ExportHTML exportHTML = new ExportHTML(analysis);
        exportHTML.exportDataFromAnalysis(outputFile, vehicle);
    }

    /**
     * Method called by the UI that returns the Analysis of N10 algorithm
     *
     * @param startNode
     * @param endNode
     * @param selectedVehicle
     * @param load
     * @return Analysis provided by the N10 algorithm
     */
    public Analysis analyzeFastestPath(Node startNode, Node endNode, Vehicle selectedVehicle, Measurable load){
        return PathAlgorithm.fastestPath(project, startNode, endNode, selectedVehicle, load);
    }

    /**
     *
     * @param start
     * @param end
     * @param selectedVehicle
     * @param maxAcceleration
     * @param maxBraking
     * @param load
     * @return
     */
    public Analysis analyzeTheoreticalEfficientPath(Node start, Node end, Vehicle selectedVehicle, Measurable maxAcceleration,
                                                    Measurable maxBraking, Measurable load){
        return PathAlgorithm.theoreticalEfficientPath(project, start, end, selectedVehicle, maxAcceleration, maxBraking, load);
    }

    /**
     *
     * @param start
     * @param end
     * @param vehicle
     * @param maxAcceleration
     * @param maxBraking
     * @param load
     * @return
     */
    public Analysis analyzeEfficientPathEnergySavingMode(Node start, Node end, Vehicle vehicle, Measurable maxAcceleration,
                                                         Measurable maxBraking, Measurable load){
        return PathAlgorithm.efficientPathEnergySavingMode(project, start, end, vehicle, maxAcceleration, maxBraking, load);
    }

}
