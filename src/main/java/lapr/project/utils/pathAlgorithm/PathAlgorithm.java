package lapr.project.utils.pathAlgorithm;

import lapr.project.model.*;
import lapr.project.utils.*;
import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Graph.Graph;
import lapr.project.utils.Graph.GraphAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static lapr.project.utils.Graph.GraphAlgorithms.shortestPath;

/**
 * Defines method contracts for path algorithms
 */
public class PathAlgorithm {

    private static final String N10_ALGORITHM_NAME = "N10 - Fastest Path";
    private static final String N11_ALGORITHM_NAME = "N11 - Theoretical Most Energy Efficient Path";
    private static final String N12_ALGORITHM_NAME = "N12 - Efficient Path in Energy Saving Mode";
    private static final String N13_ALGORITHM_NAME = "N13 - Efficient Path with Polynomial Interpolation";

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
    public static Analysis fastestPath(Project project, Node start, Node end, Vehicle vehicle, Measurable load) {

        if (!vehicle.hasValidLoad(load)) {
            throw new IllegalArgumentException("The selected vehicle does not support this load");
        }

        RoadNetwork roadNetwork = project.getRoadNetwork();
        List<Node> path = new LinkedList<>();

        //Verifies which path requires the least time and fills the list of nodes, returning the time expended in travelling
        double travelTime = shortestPathLeastTime(roadNetwork, start, end, (LinkedList<Node>) path, vehicle);

        //list of sections the vehicle used along the road network
        List<Section> sections = convertNodesListToSectionsList(roadNetwork,path);

        // the traveling time is already contained in the travelTime double

        Measurable expendedEnergy = new Measurable(0, Unit.KILOJOULE);
        Measurable tollCosts = new Measurable(0, Unit.EUROS);
        for (Section section : sections) {
            for (Segment segment : section.getSegments()) {
                Measurable maxLinearVelocity = segment.calculateMaximumVelocityInterval(roadNetwork, vehicle, segment.getLength());
                expendedEnergy.setQuantity(expendedEnergy.getQuantity() +
                        segment.determineEnergyExpenditureUniformMovement(new Measurable(0, Unit.KILOMETERS_PER_HOUR), vehicle, load,
                                segment.getLength(), maxLinearVelocity, false, false));
            }
            tollCosts.setQuantity(tollCosts.getQuantity() + section.determineTollCosts(vehicle).getQuantity());
        }

        return new Analysis(project, N10_ALGORITHM_NAME, sections, expendedEnergy,
                new Measurable(travelTime, Unit.HOUR), tollCosts);

    }

    /**
     * Converts a {@link List} of instances of {@link Node} to a {@link List} of instances of {@link Section}
     * @param path A {@link List} of instances of {@link Node}
     * @return a {@link List} of instances of {@link Section}
     */
    private static List<Section> convertNodesListToSectionsList(Graph<Node, Section> roadNetwork, List<Node> path) {
        List<Section> sections = new ArrayList<>();
        int size = path.size();
        for (int i = 0; i < size; i++) {
            Node node = path.get(i);

            if (i + 1 < size) {
                Node nextNode = path.get(i + 1);
                roadNetwork.getEdges().stream()
                        .map(Edge::getElement)
                        .filter(section -> section.getBeginningNode().equals(node) &&
                                section.getEndingNode().equals(nextNode))
                        .findFirst()
                        .ifPresent(sections::add);
            }

        }
        return sections;
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
    private static double shortestPathLeastTime(RoadNetwork roadNetwork, Node start, Node end, LinkedList<Node> path, Vehicle vehicle) {
        return shortestPath(roadNetwork, start, end, path,
                //Take into account the travelling time each section requires
                eachSection -> eachSection.getElement().calculateTotalMinimumTimeInterval(roadNetwork, vehicle));
    }

    /**
     * <p>
     * Performs calculation of the theoretical most energy efficient path between two nodes, given two maximum acceleration values
     * (both accelerating and braking).
     * </p>
     * <br>
     * <p>
     * , the vehicle can be regarded as a particle and wind effect is to be considered, but traffic is to be ignored.
     * The vehicle will be assumed to be travelling, whenever possible, at the maximum speed allowed on the road or for the vehicle,
     * respecting the acceleration limits when accelerating/breaking and taking into account the wind effect, albeit ignoring traffic.
     * </p>
     * @param project The project to which the analysis belongs
     * @param start The starting node
     * @param end The ending node
     * @param vehicle The selected vehicle to which the analysis applies
     * The maximum velocity of the vehicle will be assumed if this
     * velocity is allowed in the speed limit of a segment
     * @param maxAcceleration the maximum acceleration assumed by the vehicle
     * @param maxBraking the maximum braking assumed by the vehicle
     * @param load the load that the vehicle carries (optional)
     * @return The Analysis containing the results
     */
    public static Analysis theoreticalEfficientPath(Project project, Node start, Node end, Vehicle vehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load) {
        return efficientPath(N11_ALGORITHM_NAME, project, start, end, vehicle, maxAcceleration, maxBraking, load, false, false);
    }

    /**
     * <p>
     * Performs calculation of the most efficient path in energy saving mode between two nodes, given two maximum acceleration values
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
     * @param load the load that the vehicle carries (optional)
     * @return The Analysis containing the results
     */
    public static Analysis efficientPathEnergySavingMode(Project project, Node start, Node end, Vehicle vehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load) {
        return efficientPath(N12_ALGORITHM_NAME, project, start, end, vehicle, maxAcceleration, maxBraking, load, true, false);
    }

    /**
     * <p>
     * Performs calculation of the most energy efficient path between two nodes, given two maximum acceleration values
     * (both accelerating and braking), either if the vehicle is in energy saving mode or if it is not. The calculation
     * of the torque value should be made by polynomial interpolation
     * </p>
     * <br>
     * <p>
     * , the vehicle can be regarded as a particle and wind effect is to be considered, but traffic is to be ignored.
     * The vehicle will be assumed to be travelling, whenever possible, at the maximum speed allowed on the road or for the vehicle,
     * respecting the acceleration limits when accelerating/breaking and taking into account the wind effect, albeit ignoring traffic.
     * </p>
     * @param project The project to which the analysis belongs
     * @param start The starting node
     * @param end The ending node
     * @param vehicle The selected vehicle to which the analysis applies
     * The maximum velocity of the vehicle will be assumed if this
     * velocity is allowed in the speed limit of a segment
     * @param maxAcceleration the maximum acceleration assumed by the vehicle
     * @param maxBraking the maximum braking assumed by the vehicle
     * @param load the load that the vehicle carries (optional)
     * @param energySaving true if the vehicle has the energy saving mode on
     * @return The Analysis containing the results
     */
    public static Analysis efficientPathPolynomialInterpolation(Project project, Node start, Node end, Vehicle vehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load,
                                                                boolean energySaving) {
        return efficientPath(N13_ALGORITHM_NAME, project, start, end, vehicle, maxAcceleration, maxBraking, load, energySaving, true);
    }

    /**
     * <p>
     * Performs calculation of the most energy efficient path between two nodes, given two maximum acceleration values
     * (both accelerating and braking).
     * </p>
     * <br>
     * <p>
     * , the vehicle can be regarded as a particle and wind effect is to be considered, but traffic is to be ignored.
     * The vehicle will be assumed to be travelling, whenever possible, at the maximum speed allowed on the road or for the vehicle,
     * respecting the acceleration limits when accelerating/breaking and taking into account the wind effect, albeit ignoring traffic.
     * </p>
     * @param algorithmName the algorithm's name
     * @param project The project to which the analysis belongs
     * @param start The starting node
     * @param end The ending node
     * @param vehicle The selected vehicle to which the analysis applies
     * The maximum velocity of the vehicle will be assumed if this
     * velocity is allowed in the speed limit of a segment
     * @param maxAcceleration the maximum acceleration assumed by the vehicle
     * @param maxBraking the maximum braking assumed by the vehicle
     * @param load the load that the vehicle carries (optional)
     * @param energySaving true if the vehicle has the energy saving mode on
     * @param polynomialInterpolation true if the torque has to be calculated using polynomial interpolation
     * @return The Analysis containing the results
     */
    private static Analysis efficientPath(String algorithmName, Project project, Node start, Node end, Vehicle vehicle, Measurable maxAcceleration, Measurable maxBraking, Measurable load,
                                          boolean energySaving, boolean polynomialInterpolation) {

        if (!vehicle.hasValidLoad(load)) {
            throw new IllegalArgumentException("The selected vehicle does not support this load");
        }

        LinkedList<Node> shortestPath = new LinkedList<>();
        RoadNetwork roadNetwork = project.getRoadNetwork();

        Measurable initialVelocity = vehicle.determineInitialVelocity();

        double totalExpendedEnergy = 0;

        Graph<Node, Section> roadNetworkClone = roadNetwork.clone();

        //If a FaultyInvocationException is thrown, the search will have to be restarted and the faulty section ignored from it
        boolean pathFound;
        do {
            try {

                pathFound = true;

                totalExpendedEnergy = GraphAlgorithms.shortestPath(roadNetworkClone, start, end, shortestPath,
                        //cumulative function from which both weight and the next attribute can be inferred
                        //throws an Exception if a section proves to be impossible to travel, requiring the path to be recalculated
                        (ExceptionalBiFunction<Edge<Node, Section>, Measurable, EnergyExpenditureAccelResults>)
                                (sectionEdge, successiveVelocity) ->
                                        sectionEdge.getElement().calculateEnergyExpenditureAccel(roadNetwork, successiveVelocity, vehicle, load, maxAcceleration,
                                                maxBraking, end, energySaving, polynomialInterpolation),
                        //initial value for successive velocity
                        initialVelocity,
                        //the weigth of the graph is considered to be the expended energy
                        result -> result.getEnergyExpenditure().getQuantity(),
                        //the next value for successiveVelocity becomes the final velocity of the previous section
                        EnergyExpenditureAccelResults::getFinalVelocity);

            } catch (FaultyInvocationException e) {
                //Ignore a section which cannot be travelled
                @SuppressWarnings("unchecked")
                Edge<Node, Section> faultySection = (Edge<Node, Section>) e.getFaultyObject();
                //Restart search without this section
                roadNetworkClone.removeEdge(faultySection.getElement().getBeginningNode(), faultySection.getElement().getEndingNode());
                pathFound = false;
            }

            if (roadNetworkClone.getEdges().isEmpty()) {
                //No path was found
                break;
            }

        } while (!pathFound);

        List<Section> sections = convertNodesListToSectionsList(roadNetworkClone,shortestPath);

        Measurable expendedEnergy = new Measurable(totalExpendedEnergy, Unit.KILOJOULE);

        EnergyExpenditureAccelResults finalResults = determineAccumulatedResults(roadNetwork, vehicle, load, maxAcceleration, maxBraking, end, initialVelocity, sections,
                energySaving, polynomialInterpolation);

        return new Analysis(project, algorithmName, sections, expendedEnergy, finalResults.getTimeSpent(), finalResults.getTollCosts());
    }

    /**
     * Determines the final results corresponding to the travelling of a vehicle in a path.
     * @param roadNetwork The {@link RoadNetwork} to which the sections of the path belong, and wherein vehicles travel
     * @param vehicle The selected vehicle to which the analysis applies
     * The maximum velocity of the vehicle will be assumed if this
     * velocity is allowed in the speed limit of a segment
     * @param load the load that the vehicle carries (optional)
     * @param maxAcceleration the maximum acceleration assumed by the vehicle
     * @param maxBraking the maximum braking assumed by the vehicle
     * @param end The ending node
     * @param initialVelocity The starting velocity of the vehicle
     * @param path a {@link List} of instances of {@link Section} wherein the vehicle travels
     * @param energySaving true if the vehicle is in energy saving mode
     * @param polynomialInterpolation true if the calculation of the torque is to be made by polynomial interpolation
     * @return an instance of {@link EnergyExpenditureAccelResults} containing
     * the final results corresponding to the travelling of a vehicle in a path.
     */
    private static EnergyExpenditureAccelResults determineAccumulatedResults(RoadNetwork roadNetwork, Vehicle vehicle, Measurable load,
                                                                             Measurable maxAcceleration, Measurable maxBraking, Node end,
                                                                             Measurable initialVelocity, List<Section> path, boolean energySaving,
                                                                             boolean polynomialInterpolation) {
        Measurable successiveVelocity = initialVelocity;
        double travelTime = 0;
        double tollCosts = 0;

        for (Section section : path) {
            EnergyExpenditureAccelResults results = section.calculateEnergyExpenditureAccel(roadNetwork, successiveVelocity, vehicle, load, maxAcceleration,
                    maxBraking, end, energySaving, polynomialInterpolation);
            successiveVelocity = results.getFinalVelocity();

            travelTime += results.getTimeSpent().getQuantity();
            tollCosts += results.getTollCosts().getQuantity();
        }

        return new EnergyExpenditureAccelResults(new Measurable(travelTime, Unit.HOUR), new Measurable(tollCosts, Unit.EUROS));
    }


}
