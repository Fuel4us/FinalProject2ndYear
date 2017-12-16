package lapr.project.model;

import lapr.project.model.RoadNetwork.*;
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
    private RoadNetwork<Node,Section> roadNetwork;
    private List<Vehicle> vehicles;
    private boolean active;

    /**
     * Creates a new Project, with a name, description and a roadNetwork
     * @param name This project's name
     * @param description This project's description
     * @param roadNetwork The graph view of the network of roads associated with this project
     * @param vehicles the list of vehicles in the project
     */
    public Project(String name, String description, RoadNetwork<Node,Section> roadNetwork, List<Vehicle> vehicles) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = vehicles;
        this.active = false;
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
    public RoadNetwork<Node,Section> getRoadNetwork() {
        return roadNetwork;
    }

    /**
     * Getter for the vehicles
     * @return the list of vehicles of the project
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Implementation of the equals for objects of the type Project
     * Only the name is considered
     * @param o other object
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return name != null ? name.equals(project.name) : project.name == null;
    }

    /**
     * Implementation of the hashCode for objects of the type Project
     * Only the name is considered
     * @return
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
    
    public void setActive(){
        this.active = true;
    }
    
    public void setDesactive(){
        this.active = false;
    }
}
