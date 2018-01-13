package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.FileParser.FileParser;
import lapr.project.utils.FileParser.XMLImporter;
import lapr.project.utils.Graph.Edge;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;


public class ChangeDataController {

    private Project project;
    private DataBaseCommunicator dbCom;
    private File roadsFile;
    private File vehiclesFile;

    public ChangeDataController(Project project, DataBaseCommunicator dbCom) {
        this.project = project;
        this.dbCom = dbCom;
    }

    /**
     * Setter for RoadNetwork File
     *
     * @param roads
     */
    public void setRoadNetworkFile(File roads) {
        this.roadsFile = roads;
    }

    /**
     * Setter for the Vehicles File
     *
     * @param vehicles
     */
    public void setVehiclesFile(File vehicles) {
        this.vehiclesFile = vehicles;
    }

    /**
     * Getter for project name
     * @return Name of Project
     */
    public String getName() {
        return project.getName();
    }

    /**
     * Getter for project description
     * @return Description of project
     */
    public String getDescription() {
        return project.getDescription();
    }

    /**
     * Getter for project
     * @return project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Setter of name
     * @param name project name
     */
    public void setName(String name) {
        String name1 = name;
    }

    /**
     * Setter of description
     * @param description project description
     */
    public void setDescription(String description) {
        String description1 = description;
    }

    /**
     * Adds more elements to the already created {@link lapr.project.model.RoadNetwork}
     * @throws Exception
     */
    public void addNewRoads() throws Exception {
        FileParser importer = new XMLImporter(roadsFile, vehiclesFile);
        RoadNetwork temporaryRoadNetwork = importer.importNetwork(true);
        List<Node> nodes = temporaryRoadNetwork.getVertices();
        List<Section> sections = temporaryRoadNetwork.getEdges().stream().map(Edge::getElement).collect(Collectors.toList());
        dbCom.addRoadNetworkElementsToProject(project, nodes, sections);
    }

    /**
     * Adds more vehicles to the Vehicle List
     * @throws Exception
     */
    public void addNewVehicles() throws Exception {
        FileParser importer = new XMLImporter(roadsFile, vehiclesFile);
        List<Vehicle> temporaryVehicles = importer.importVehicles();
        List<Vehicle> projectVehicles = project.getVehicles();
        projectVehicles.addAll(temporaryVehicles);
        dbCom.addVehiclesToProject(project, temporaryVehicles);
    }

    /**
     *
     * @param project
     * @param updatedName
     * @param updatedDescription
     */
    public void changeProjectData(Project project, String updatedName, String updatedDescription){
        dbCom.changeProjectData(project, updatedName, updatedDescription);
        project.setName(updatedName);
        project.setDescription(updatedDescription);
    }
}