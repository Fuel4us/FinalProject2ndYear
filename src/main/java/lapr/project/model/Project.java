package lapr.project.model;

import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.AbstractVehicle;

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
    private List<AbstractVehicle> vehicles;

    /**
     * Creates a new Project, with a name, description and a roadNetwork
     * @param name This project's name
     * @param description This project's description
     * @param roadNetwork The graph view of the network of roads associated with this project
     */
    public Project(String name, String description, RoadNetwork roadNetwork, List<AbstractVehicle> vehicles) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
    }

    /**
     * @return this Project's name
     */
    public String getName() {
        return name;
    }
}
