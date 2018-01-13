package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Vehicle;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.utils.FileParser.FileParser;
import lapr.project.utils.FileParser.XMLImporter;

import java.io.File;
import java.util.List;

/**
 * Class that represents the Controller for the UC2 - Create Project
 */
public class CreateProjectController {

    private final DataBaseCommunicator dbCom;
    private File roadsFile;
    private File vehiclesFile;
    private FileParser importer;

    /**
     * Enumerates supported file types
     */
    public enum SupportedFileTypes {
        XML
    }

    /**
     * Full constructor for the class CreateProjectController
     * File parsing method defaults to XML but can be changed
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
        importer = new XMLImporter(this.roadsFile,this.vehiclesFile);
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
        RoadNetwork roadNetwork = importer.importNetwork(true);
        List<Vehicle> vehicles = importer.importVehicles();
        Project project = new Project(name, name, description, roadNetwork, vehicles);
        dbCom.addProject(project);
        return project;
    }

    /**
     * Sets the parsing mode, so that a specific type of file format can be read
     * @param fileFormat the file format to read
     */
    public void setExtensionParsingMode(SupportedFileTypes fileFormat) {
        switch (fileFormat) {
            case XML:
                importer = new XMLImporter(this.roadsFile, this.vehiclesFile);
                break;
        }
    }

}
