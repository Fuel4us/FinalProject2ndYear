package lapr.project.utils.pathAlgorithm;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Measurable;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Graph.GraphAlgorithms.shortestPath;

/**
 * Defines method contracts for path algorithms
 */
public class PathAlgorithm {

    /**
     * <p>
     * Calculates the fastest path between two nodes, given a vehicle, for a given project.
     * <br>
     * The vehicle will travel at the maximum speed allowed on the road or for the vehicle, acceleration/breaking is ignored,
     * <br>
     * wind is considered to calculate energy expenditure (vehicle should is regarded as a particle) and traffic is ignored.
     * </p>
     * @param project The project to which the analysis belongs
     * @param start The starting node
     * @param end The ending node
     * @param vehicle The selected vehicle to which the analysis applies
     * The maximum velocity of the vehicle will be assumed if this
     * velocity is allowed in the speed limit of a segment
     * @return The Analysis containing the results
     */
    public Analysis fastestPath(Project project, Node start, Node end, Vehicle vehicle) {

        RoadNetwork roadNetwork = project.getRoadNetwork();
        List<Node> path = new ArrayList<>();

        //Verifies which path requires the least time and fills the list of nodes, returning the time expended in travelling
        double travelTime = shortestPathLeastTime(roadNetwork, start, end, path, vehicle);

        //list of sections the vehicle used along the road network

        //Todo -> ensure list represents shortest to Node end NO FIM WTV


        //Todo -> convert list to list of sections
        //ToDo Done -> Requires testing only
        List<Section> sections = new ArrayList<>();
        int size = path.size();
        for (int i = 0; i < size; i++) {
            Node node = path.get(i);
            if (i + 1 < size) {
                sections.add((Section) node.getEdge(path.get(i + 1).getElement()));
            }
        }


        // the traveling time is already contained in the travelTime double

        // ToDo energy consumption considering wind effect

        double expendedEnergy = 0;
        for (Section section : sections) {
        }

        // ToDo toll costs

        //ToDo include above information in generated report, and make it a class of its own
        return null;

    }

    /**
     * Fills a list with the nodes a vehicle uses
     * @param roadNetwork The roadNetwork
     * @param start The starting node
     * @param end The ending node
     * @param path The path
     * @param vehicle The vehicle used in the travelling
     * @return the required time to travel
     */
    double shortestPathLeastTime(RoadNetwork roadNetwork, Node start, Node end, List<Node> path, Vehicle vehicle) {
        return shortestPath(roadNetwork, start, end, path,
                //Take into account the travelling time each section requires
                eachSection -> eachSection.getElement().calculateTotalMinimumTimeInterval(roadNetwork, vehicle));
    }


    /**
     * <p>
     * Performs calculation of the most efficient path in real conditions between two nodes, given two maximum acceleration values
     * (both accelerating and braking).
     * </p>
     * <br>
     * <p>
     * The vehicle will be assumed to be travelling, whenever possible, at the highest suitable gear and 25% throttle,
     * respecting the road speed limit and taking into account the wind effect, albeit ignoring traffic.
     * </p>
     * @param project The project to which the analysis belongs
     * @param start The starting node
     * @param end The ending node
     * @param vehicle The selected vehicle to which the analysis applies
     * The maximum velocity of the vehicle will be assumed if this
     * velocity is allowed in the speed limit of a segment
     * @param maxAcceleration the maximum acceleration assumed by the vehicle
     * @param maxBraking the maximum braking assumed by the vehicle
     * @return The Analysis containing the results
     */
    public Analysis efficientPathRealConditions(Project project, Node start, Node end, Vehicle vehicle, Measurable maxAcceleration, Measurable maxBraking) {



        return null;
    }

}
