package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.utils.FileParser.FileParser;
import lapr.project.utils.FileParser.XMLImporter;

import java.io.File;

/**
 * Class that represents the Controller for the UC2 - Create Project
 */
public class CreateProjectController {

    private final DataBaseCommunicator dbCom;
    private File roadsFile;
    private File vehiclesFile;

    /**
     * Full constructor for the class CreateProjectController
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
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
     * Getter for the RoadNetwork File
     * 
     * @return The XML File relative to the RoadNetwork
     */
    public File getRoadsFile() {
        return roadsFile;
    }

    /**
     * Getter for the Vehicles File
     * 
     * @return The XML File relative to the vehicles
     */
    public File getVehiclesFile() {
        return vehiclesFile;
    }

    /**
     * Method called by the UI that creates a new instance of project with the
     * user submitted data
     * 
     * @param name Project name
     * @param description Project description
     */
    public Project createProject(String name, String description) throws Exception {
        FileParser importer = new XMLImporter(roadsFile, vehiclesFile);
        RoadNetwork roadNetwork = importer.importNetwork(true);
        Project project = new Project(name, description, roadNetwork, null);
        importer.importVehicles();
        dbCom.addProject(project);
        return project;
    }

}
