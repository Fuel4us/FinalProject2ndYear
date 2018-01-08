package lapr.project.controller;

import java.io.File;
import lapr.project.model.*;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.utils.FileParser.XMLImporterRoads;
import lapr.project.utils.FileParser.XMLImporterVehicles;

/**
 * Class that represents the Controller for the UC2 - Create Project
 */
public class CreateProjectController {

    private final DataBaseCommunicator dbCom;
    private static Project project;
    private File roadsFile;
    private File vehiclesFile;

    /**
     * Full constructor for the class CreateProjectController
     * @param dbCom
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
        project = new Project();
        this.roadsFile = null;
        this.vehiclesFile = null;
    }

    /**
     * Getter of the current instance of project
     * 
     * @return The current instance of project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Getter of the DataBase Communicator
     * 
     * @return dbCom
     */
    public DataBaseCommunicator getDbCom() {
        return dbCom;
    }

    /**
     * Setter for RoadNetwork File
     *
     * @param roads
     */
    public void setRoads(File roads) {
        this.roadsFile = roads;
    }

    /**
     * Setter for the Vehicles File
     *
     * @param vehicles
     */
    public void setVehicles(File vehicles) {
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
     * @throws java.lang.Exception
     */
    public void createProject(String name, String description) throws Exception {
        XMLImporterRoads roadImporter = new XMLImporterRoads(roadsFile);
        project = new Project(name, description, roadImporter.importNetwork(true), null);
        new XMLImporterVehicles().importVehicles(project, vehiclesFile.getAbsolutePath());
        dbCom.addProject(project);
    }

}
