package lapr.project.controller;

import java.io.File;
import lapr.project.model.*;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.*;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.FileParser.FileParser;
import lapr.project.utils.FileParser.ImportFile;
import lapr.project.utils.FileParser.XMLImporterRoads;
import lapr.project.utils.FileParser.XMLImporterVehicles;

/**
 * Class that represents the Controller for the UC2 - Create Project
 */
public class CreateProjectController {

    private DataBaseCommunicator dbCom;
    private static Project project;

    private File roadsFile;
    private File vehiclesFile;

    /**
     * Full constructor for the class CreateProjectController
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
        project = new Project();
        this.roadsFile = null;
        this.vehiclesFile = null;
    }

    public Project getProject() {
        return project;
    }

    public DataBaseCommunicator getDbCom() {
        return dbCom;
    }

    public void setRoads(File roads) {
        this.roadsFile = roads;
    }

    public void setVehicles(File vehicles) {
        this.vehiclesFile = vehicles;
    }

    public File getRoadsFile() {
        return roadsFile;
    }

    public File getVehiclesFile() {
        return vehiclesFile;
    }

    public void createProject(String name, String description) throws Exception {
        XMLImporterRoads roadImporter = new XMLImporterRoads(roadsFile);
        project = new Project(name, description, roadImporter.importNetwork(), null);
        new XMLImporterVehicles().importVehicles(project, vehiclesFile.getAbsolutePath());
    }

}
