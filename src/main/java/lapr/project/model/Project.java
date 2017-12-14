package lapr.project.model;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
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
    private RoadNetwork<Road,Section> roadNetwork;
    private List<Vehicle> vehicles;

    /**
     * Full constructor for the class Project
     * @param name the name of the project
     * @param description the description of the project
     * @param roadNetwork the road network of the project
     * @param vehicles the list of vehicles in the project
     */
    public Project(String name, String description, RoadNetwork<Road,Section> roadNetwork, List<Vehicle> vehicles) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = vehicles;
    }

    /**
     * Getter for the name
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the road network
     * @return the road network of the project
     */
    public RoadNetwork<Road,Section> getRoadNetwork() {
        return roadNetwork;
    }

    /**
     * Getter for the vehicles
     * @return the list of vehicles of the project
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
