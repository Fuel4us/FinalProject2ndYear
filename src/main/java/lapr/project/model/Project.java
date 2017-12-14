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

    public Project(String name, String description, RoadNetwork roadNetwork, List<AbstractVehicle> vehicles) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = vehicles;
    }

}
