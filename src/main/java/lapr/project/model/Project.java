package lapr.project.model;

import lapr.project.model.RoadNetwork.RoadNetwork;

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

    public Project(String name, String description, RoadNetwork roadNetwork) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
    }

}
