package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.model.RoadNetwork.*;
import lapr.project.utils.Graph.*;



public class bestPathController {

    private List<Node> nodesList;
    private List<Vehicle> listVehicles;
    private Vehicle vehicle;
    private Node origin;
    private Node destiny;
    private Project project;

    /**
     * Constructor of the controller class bestPathController instanciated by
     * the UI
     * 
     * @param project The current instance of project
     */
    public bestPathController(Project project) {
        this.project = project;
        this.nodesList = null;
        this.listVehicles = null;
        this.vehicle = null;
        this.origin = null;
        this.destiny = null;
    }

    /**
     * Method called by the UI that returns a list with all 
     * the vehicles in the project
     * 
     * @return List<Vehicle> list of all vehicles in the project
     */
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> list = new ArrayList<>();
        //metodo que vai buscar todos os veiculos ao sql e iguala a list
        setListVehicles(list);
        return getListVehicles();
    }
        
    /**
     * Method that sets teh origin and destiny nodes received in the parameters
     *
     * @param origin Origin node.
     * @param destiny Destiny node.
     * 
     */
    public void setNodes(Node origin, Node destiny){
        this.origin=origin;
        this.destiny=destiny;
    }
    
   
    public List<Node> getAllNodes(){
        List<Node> list = new ArrayList<>();
        for(Edge<Road,Section> edge : getProject().getRoadNetwork().edges()){
            
        }
        setNodesList(list);
        return getNodesList();
    }
    
    /**
     * Method that returns a list with all the nodes in the project
     * 
     * @return List<Node> list of all nodes in the project
     */
    public List<Node> getNodesList() {
        return nodesList;
    }

    /**
     * Method that sets a list with all the nodes in the project
     * 
     * @param List<Node> list of all nodes in the project
     */
    public void setNodesList(List<Node> nodesList) {
        this.nodesList = nodesList;
    }

    /**
     * Method that returns a list with all the vehicles in the project
     * 
     * @return List<Vehicle> list of all vehicles in the project
     */
    public List<Vehicle> getListVehicles() {
        return listVehicles;
    }

    /**
     * Method that sets a list with all the vehicles in the project
     * 
     * @param List<Vehicle> list of all vehicles in the project
     */
    public void setListVehicles(List<Vehicle> listVehicles) {
        this.listVehicles = listVehicles;
    }

    /**
     * Method that returns the vehicle picked by the user in the UI
     * 
     * @return Vehicle Vehicle picked by the user in the UI
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Method that sets a vehicle picked by the user in the UI
     * 
     * @param Vehicle Vehicle picked by the user in the UI
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Method that returns the origin node picked by the user in the UI
     * 
     * @return Node Origin node picked by the user in the UI
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * Method that sets the origin node picked by the user in the UI
     * 
     * @param origin Origin node picked by the user in the UI
     */
    public void setOrigin(Node origin) {
        this.origin = origin;
    }

    /**
     * Method that returns the destiny node picked by the user in the UI
     * 
     * @return Node Destiny node picked by the user in the UI
     */
    public Node getDestiny() {
        return destiny;
    }

    /**
     * Method that sets the destiny node picked by the user in the UI
     * 
     * @param destiny Destiny node picked by the user in the UI
     */
    public void setDestiny(Node destiny) {
        this.destiny = destiny;
    }

    /**
     * Method that returns the current instance of project
     * 
     * @return project The current instance of project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Method that sets the current instance of project
     * 
     * @param project The current instance of project
     */
    public void setProject(Project project) {
        this.project = project;
    }
    

}
