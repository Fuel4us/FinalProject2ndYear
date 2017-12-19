package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.utils.FileParser.*;

public class AddVehiclesController {
    private final Project project;
    private final String vehiclesFile;
    
     public AddVehiclesController(Project project, String fileName) {
        this.project = project;
        this.vehiclesFile = fileName;
    }
     
     /**
     * Imports the information of the file to the project 
     * 
     * @return true if correctly imported
     * <p> false if there is an error </p>
     *  
     */
    public boolean importVehicles() {
        if (vehiclesFile.length() < 5) {
            return false;
        }

        String extension = vehiclesFile.substring(vehiclesFile.lastIndexOf("."));

        ImportFile input = new ImportFile();
        FileParser type;
        switch (extension) {
            case ".xml":
                type = new XMLImporterVehicles();
                break;
            default:
                return false;
        }

        input.importVehicles(type, project, vehiclesFile);
        return true;
    }
    
}
