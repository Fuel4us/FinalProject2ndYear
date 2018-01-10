package lapr.project.model;

import java.util.ArrayList;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.Vehicle;
import java.util.List;

/**
 * <p>
 * A project is a container of road networks and allows several analysis tools
 * to be used
 * </p>
 */
public class Project {

    private String name;
    private String description;
    private RoadNetwork roadNetwork;
    private List<Vehicle> vehicles;
    private int idName = 1;

    public Project(String name, String description, RoadNetwork roadNetwork, List<Vehicle> vehicles) {
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = vehicles;
    }

    /**
     * Build a Project instance with default data
     */
    public Project() {
        name = "";
        description = "";
        roadNetwork = new RoadNetwork();
        vehicles = new ArrayList<>();
    }

    /**
     * Implementation of the equals for objects of the type Project Only the
     * name is considered
     *
     * @param o other object
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Project project = (Project) o;

        return name != null ? name.equals(project.name) : project.name == null;
    }

    /**
     * Implementation of the hashCode for objects of the type Project Only the
     * name is considered
     *
     * @return
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    /**
     * Getter of the name
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of description
     *
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of roadNetwork
     *
     * @return the instance of RoadNetwork of the project
     */
    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    /**
     * Getter of vehicles
     *
     * @return list of instance Vehicle of the project
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Setter for the project name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the project description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Clone project
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Project cloneProject() throws CloneNotSupportedException {
        return new Project(getName() + " (Copy)", getDescription() + " (Copy)", getRoadNetwork(), getVehicles());
    }
    
    String addNameIfEquals(List<Project> project, String name) {
        
        for (Project p: project) {
            if (p.getName().equalsIgnoreCase(name)) {
                name += idName;
                idName++;
            }
        }
        return name;
    }

}
