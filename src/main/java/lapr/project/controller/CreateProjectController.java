package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.*;

import java.util.List;

/**
 * Class that represents the Controller for the UC2 - Create Project
 */
public class CreateProjectController {

    private DataBaseCommunicator dbCom;
    private Project project;

    private List<Vehicle> vehicles;
    private RoadNetwork<Road, Section> roadNetwork;

    /**
     * Full constructor for the class CreateProjectController
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
    }

//    public void importVehicles() {
//
//    }

//    public void importRoadNetwork() {
//
//    }

    /**
     * Creates the project
     * @param name the name of the project
     * @param description the description of the project
     */
    public void createProject(String name, String description) {
        project = new Project(name, description, roadNetwork, vehicles);
    }
}
