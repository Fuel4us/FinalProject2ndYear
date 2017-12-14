package lapr.project.model;

import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;

import java.util.List;

/**
 * <p>
 * A project is a container of road networks
 * and allows several analysis tools to be used
 * </p>
 */
public class Project {

    private String name;
    private String description;
    private RoadNetwork roadNetwork;
    private List<Vehicle> vehicles;

    /**
     * Full constructor for the class Project
     * @param name the name of the project
     * @param description the description of the project
     * @param roadNetwork the road network of the project
     * @param vehicles the list of vehicles in the project
     */
    public Project(String name, String description, RoadNetwork roadNetwork, List<Vehicle> vehicles) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = vehicles;
    }

}
