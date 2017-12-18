package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.model.RoadNetwork.*;



public class RoadNetworkPathFormController {

    private List<Node> nodesList;
    private List<Vehicle> listVehicles;
    private Vehicle vehicle;
    private Node origin;
    private Node destiny;
    private Project project;

    /**
     * Constructor of the controller class RoadNetworkPathFormController instanciated by
     * the UI
     * 
     * @param project The current instance of project
     */
    public RoadNetworkPathFormController(Project project) {
        this.project = project;
    }

    /**
     * Method called by the UI that returns a list with all 
     * the vehicles in the project
     * 
     * @return list of all vehicles in the project
     */
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> list = new ArrayList<>();
        //metodo que vai buscar todos os veiculos ao sql e iguala a list
        return list;
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
    
   /**
    * Method called by the UI that returns the list with all the nodes
    * 
    * @return List that contains all nodes 
    */
    public List<Node> getAllNodes(){
        List<Node> nodeList = new ArrayList<>();
        for(Node node : project.getRoadNetwork().vertices()){
            nodeList.add(node);
        }
        return nodeList;
    }
    
//    public void pickMethod(){}
  
}