package lapr.project.model;

import java.util.ArrayList;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.Vehicle;
import java.util.List;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;

/**
 * <p>
 * A project is a container of road networks
 * and allows several analysis tools to be used
 * </p>
 */
public class Project {

    private int id;
    private int countEquals;
    private String name;
    private String description;
    private RoadNetwork roadNetwork;
    private List<Vehicle> vehicles;

    /**
     * Creates a new Project, with a name, description and a roadNetwork
     * @param name This project's name
     * @param description This project's description
     * @param roadNetwork The graph view of the network of roads associated with this project
     * @param vehicles the list of vehicles in the project
     */
    public Project(int id,String name, String description, RoadNetwork roadNetwork, List<Vehicle> vehicles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = new ArrayList<Vehicle>();
        
        if (id > countEquals) {
            countEquals = id++;
        }
    }
    
    public Project(String name, String description, RoadNetwork roadNetwork, List<Vehicle> vehicles) {
        this.id = countEquals++;
        this.name = name;
        this.description = description;
        this.roadNetwork = roadNetwork;
        this.vehicles = new ArrayList<Vehicle>();
    }

    /**
     * Build a Project instance with default data
     */
    public Project() {
        id = 0;
        name = "";
        description = "";
        roadNetwork = new RoadNetwork();
        vehicles = new ArrayList<>();
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

    /**
     * Getter of the name
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of description
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of roadNetwork
     * @return the instance of RoadNetwork of the project
     */
    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    /**
     * Getter of vehicles
     * @return list of instance Vehicle of the project
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    /**
    * Setter for the list of Vehicles
    * 
    * @param vehicles 
    */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Clone project
     * @return
     * @throws CloneNotSupportedException
     */
    public Project cloneProject() throws CloneNotSupportedException {
       return new Project(getId(), getName() + " (Copy)", getDescription() + " (Copy)", getRoadNetwork(), getVehicles());
    }
   
}
