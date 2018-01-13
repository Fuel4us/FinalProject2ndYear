package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Analysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.utils.FileParser.ExportHTML;
import lapr.project.utils.Measurable;
import lapr.project.utils.pathAlgorithm.PathAlgorithm;



public class BestPathController {

    private Project project;
    private File outputFile;

    /**
     * Constructor of the controller class RoadNetworkPathFormController instanciated by
     * the UI
     * 
     * @param project The current instance of project
     */
    public BestPathController(Project project) {
        this.project = project;
    }

    /**
     * Method called by the UI that returns a list with all 
     * the vehicles in the project
     * 
     * @return list of all vehicles in the project
     */
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> list;
        list = project.getVehicles();
        return list;
    }
        
        
   /**
    * Method called by the UI that returns the list with all the nodes
    * 
    * @return List that contains all nodes 
    */
    public List<Node> getAllNodes(){
        List<Node> nodeList = new ArrayList<>();
        for(Node node : project.getRoadNetwork().vertices()){
            nodeList.add(node);
        }
        return nodeList;
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
     * Method called by the UI that returns the Analysis of N11 algorithm
     *
     * @param start
     * @param end
     * @param selectedVehicle
     * @param load
     * @return Analysis provided by the N11 algorithm
     */
    public Analysis analyzeTheoreticalEfficientPath(Node start, Node end, Vehicle selectedVehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load){
        return PathAlgorithm.theoreticalEfficientPath(project, start, end, selectedVehicle, maxAcceleration, maxBraking, load);
    }

    /**
     * Method called by the UI that returns the Analysis of N12 algorithm
     *
     * @param start
     * @param end
     * @param vehicle
     * @param load
     * @return Analysis provided by the N12 algorithm
     */
    public Analysis analyzeEfficientPathEnergySavingMode(Node start, Node end, Vehicle vehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load){
        return PathAlgorithm.efficientPathEnergySavingMode(project, start, end, vehicle, maxAcceleration, maxBraking, load);
    }

    /**
     * Method called by the UI that returns the Analysis of N13 algorithm
     *
     * @param start
     * @param end
     * @param selectedVehicle
     * @param load
     * @return Analysis provided by the N13 algorithm
     */
    public Analysis efficientPathPolynomialInterpolationN11Button(Node start, Node end, Vehicle selectedVehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load){
        return PathAlgorithm.efficientPathPolynomialInterpolation(project, start, end, selectedVehicle, maxAcceleration, maxBraking, load, false);
    }

    /**
     * Method called by the UI that returns the Analysis of N13 algorithm in energy saving mode
     *
     * @param start
     * @param end
     * @param selectedVehicle
     * @param load
     * @return Analysis provided by the N13 algorithm
     */
    public Analysis efficientPathPolynomialInterpolationN12Button(Node start, Node end, Vehicle selectedVehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load){
        return PathAlgorithm.efficientPathPolynomialInterpolation(project, start, end, selectedVehicle, maxAcceleration, maxBraking, load, true);
    }

}