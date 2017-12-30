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
    private Project project;

    private List<Vehicle> vehicles;
    private RoadNetwork roadNetwork;

    /**
     * Full constructor for the class CreateProjectController
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
        this.project = new Project();
    }

    public Project getProject() {
        return project;
    }

    public DataBaseCommunicator getDbCom() {
        return dbCom;
    }
    
     /**
     * Validate the project file integrity
     *
     * @param filename
     * @param type
     * @return
     */
    public boolean validate(String filename, String type) {
        File file = new File(filename);
        if (file.exists()) {
            return true;
        }
        return false;
    }
    
    public boolean newProject(String fileType, String inputType, String fileName) {
        
        ImportFile file = new ImportFile();
        FileParser type;
        FileParser type2;
        
        switch(inputType) {
            case ".xml":
                type = new XMLImporterVehicles();
                type2 = new XMLImporterRoads(null, true); // String cannot be converted to File
                break;
            default:
                return false;
        }
        
        if(fileType.equalsIgnoreCase("vehicles")) {
            file.importVehicles(type, project, fileName);
        } else {
            System.out.println("Didn't find");
        }
        if(fileType.equalsIgnoreCase("network")) {
            file.importNetwork(type2, project, fileName);
        } else {
            System.out.println("Didn't find");
        }
        
        return false;
        
    }

    public void setProject(int id, String name, String description) {
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
    }
    
    
}
