package lapr.project.utils.pathAlgorithm;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Graph.GraphAlgorithms.shortestPath;

/**
 * Defines method contracts for path algorithms
 */
public class PathAlgorithm {

    private static final String N10_ALGORITHM_NAME = "N10 - Fastest Path";
    private static final String N11_ALGORITHM_NAME = "N11 - Theoretical Most Energy Efficient Path";

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
     * @param load the vehicle's load
     * @return The Analysis containing the results
     */
    public Analysis fastestPath(Project project, Node start, Node end, Vehicle vehicle, Measurable load) {

        if (vehicle.getMotorType() != Vehicle.MotorType.COMBUSTION) {
            throw new IllegalArgumentException("This operation does not support electric vehicles");
        }

        if (!vehicle.hasValidLoad(load)) {
            throw new IllegalArgumentException("The selected vehicle does not support this load");
        }

        RoadNetwork roadNetwork = project.getRoadNetwork();
        List<Node> path = new LinkedList<>();

        //Verifies which path requires the least time and fills the list of nodes, returning the time expended in travelling
        double travelTime = shortestPathLeastTime(roadNetwork, start, end, (LinkedList<Node>) path, vehicle);

        //list of sections the vehicle used along the road network
        List<Section> sections = new ArrayList<>();
        int size = path.size();
        for (int i = 0; i < size; i++) {
            Node node = path.get(i);
            if (i + 1 < size) {
                sections.add((Section) node.getEdge(path.get(i + 1).getElement()));
            }
        }

        // the traveling time is already contained in the travelTime double

        Measurable expendedEnergy = new Measurable(0, Unit.KILOJOULE);
        Measurable tollCosts = new Measurable(0, Unit.EUROS);
        for (Section section : sections) {
            for (Segment segment : section.getSegments()) {
                Measurable maxLinearVelocity = segment.calculateMaximumVelocityInterval(roadNetwork, vehicle, segment.getLength());
                expendedEnergy.setQuantity(expendedEnergy.getQuantity() +
                        vehicle.determineEnergyExpenditure(segment, load, segment.getLength(), maxLinearVelocity)[0].getQuantity());
            }
            tollCosts.setQuantity(tollCosts.getQuantity() + section.determineTollCosts(vehicle).getQuantity());
        }

        return new Analysis(project, N10_ALGORITHM_NAME, sections, expendedEnergy,
                new Measurable(travelTime, Unit.HOUR), tollCosts);

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
    double shortestPathLeastTime(RoadNetwork roadNetwork, Node start, Node end, LinkedList<Node> path, Vehicle vehicle) {
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

        //ToDo Calculate **energy expenditure** in a section, considering energy variations when changing segments due to acceleration and braking :: Section
        //ToDo Find out shortest path considering **energy expenditure** (return energy and path) :: Dijkstra with the new definition of weight
        //ToDo Calculate Toll costs for the given path
        //ToDo Total Travelling time for the given path (may use bruno's method but we have to add the time spent in changing segments??) :: Section

        return new Analysis(project, N11_ALGORITHM_NAME,null,null,null,null);
    }

}
