package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Section extends Edge<String, Direction> {

    private Node beginningNode;
    private Node endingNode;
    private Direction direction;
    private Collection<Segment> segments;
    private Road owningRoad;
    private List<Double> tollFare;

    /*
    Auto generated ID
     */
    private final int id;
    private static int sectionCounter = 0;

    /*
    File Exporting templates
     */
    private static final String HTML_STRUCTURE_SEGMENT = "html_structure_segment";
    private static final String CSV_STRUCTURE_SEGMENT = "csv_structure_segment";

    /**
     * Creates a Section with a beginning and ending node, direction and collection of segments
     * @param beginningNode This section's beginning {@link Node}
     * @param endingNode This section's ending {@link Node}
     * @param direction This section's {@link Direction}
     * @param segments The {@link Collection} of segments that belong to this section
     * @param tollFare The {@link List} of toll fares of this section
     */
    public Section(Node beginningNode, Node endingNode, Direction direction, Collection<Segment> segments, Road road, List<Double> tollFare) {
        super(direction, calculateTotalLength(segments), beginningNode, endingNode);
        this.segments = segments;
        this.beginningNode = beginningNode;
        this.endingNode = endingNode;
        this.direction = direction;
        this.owningRoad = road;
        this.tollFare = tollFare;
        this.id = ++sectionCounter;
    }

    /**
     * Calculates the toll costs for this section, depending on the correspondent Road
     * @param vehicle the vehicle
     * @return the toll costs
     */
    public Measurable determineTollCosts(Vehicle vehicle) {

        if (owningRoad.getTypology().equalsIgnoreCase("regular road")) {

            //regular roads are free
            return new Measurable(0, Unit.EUROS);

        } else if (owningRoad.getTypology().equalsIgnoreCase("toll highway")) {

            //toll highway is the toll fare of each segment times the number of km in each segment
            double kmTravelled = 0;
            for (Segment segment : segments) {
                kmTravelled += segment.getLength();
            }

            double tollFare = owningRoad.retrieveVehicleClassRespectiveTollFare(vehicle);

            return new Measurable(kmTravelled * tollFare, Unit.EUROS);

        } else if (owningRoad.getTypology().equalsIgnoreCase("gantry toll highway")) {

            //gantry toll highway is the toll fare of this section
            for (int i = 0; i < tollFare.size(); i++) {
                if (i + 1 == vehicle.getVehicleClass()) {
                    return new Measurable(tollFare.get(i), Unit.EUROS);
                }
            }

        }

        return new Measurable(0, Unit.EUROS);

    }

    /**
     * Retrieves the road's typology
     * @return the road's typology
     */
    public String retrieveRoadTypology() {
        return owningRoad.getTypology();
    }

    /**
     * Checks if the list of segments contains the segment given as a parameter
     * @param segment the segment to be considered
     * @return true if the segment is in the list of segments
     */
    public boolean containsSegment(Segment segment) {
        return segments.contains(segment);
    }

    /**
     * Calculates the total minimum time interval spent for the whole section,
     * taking into account the velocity limit in each segment, its length and
     * the velocity limit of the vehicle in the typology of the segment
     * @param roadNetwork the road network of the current project
     * @param vehicle the vehicle
     * @return the total minimum time interval
     */
    public double calculateTotalMinimumTimeInterval(RoadNetwork roadNetwork, Vehicle vehicle) {

        double totalMinimumTimeInterval = 0;

        for (Segment segment : segments) {

            totalMinimumTimeInterval += segment.calculateMinimumTimeInterval(roadNetwork, vehicle);

        }

        return totalMinimumTimeInterval;

    }

    /**
     * Determines the weight of the edge, equating weight with the sum of the length of each segment
     * @param segments The instances of Segment that belong to this Section
     * @return the total weight of this Section
     */
    private static double calculateTotalLength(Collection<Segment> segments) {
        double weight = 0.0;
        for (Segment segment : segments) {
            weight += segment.getLength();
        }
        return weight;
    }

    /**
     * Prints data of each segment included in the section, according to a certain html file
     */
    public void printSegmentsFromSectionHTML(FileWriter file) throws IOException {
        for (Segment segment : segments) {
            file.write("\n");
            printDataFromSegmentHTML(segment, file);
        }
    }

    /**
     * Prints data of each segment included in the section, according to a certain csv file
     */
    public void printSegmentsFromSectionCSV(FileWriter file) throws IOException {
        for (Segment segment : segments) {
            file.write("\n");
            printDataFromSegmentCSV(segment, file);
        }
    }

    /**
     * Prints data from a given segment filling the information missing in a given HTML file template
     * @param segment the segment
     */
    private void printDataFromSegmentHTML(Segment segment, FileWriter file) throws IOException {
        StringTemplateGroup groupSegment = new StringTemplateGroup("src\\main\\resources");
        StringTemplate segmentTemplate = groupSegment.getInstanceOf(HTML_STRUCTURE_SEGMENT);
        segment.printDataFromSegment(segmentTemplate, file);
    }

    /**
     * Prints data from a given segment filling the information missing in a given CSV file template
     * @param segment the segment
     */
    private void printDataFromSegmentCSV(Segment segment, FileWriter file) throws IOException {
        StringTemplateGroup groupSegment = new StringTemplateGroup("src\\main\\resources");
        StringTemplate segmentTemplate = groupSegment.getInstanceOf(CSV_STRUCTURE_SEGMENT);
        segment.printDataFromSegment(segmentTemplate, file);
    }

    /**
     * @return owning road
     */
    public Road getOwningRoad() {
        return owningRoad;
    }

    /**
     * @return segments
     */
    public Collection<Segment> getSegments() {
        return segments;
    }

    /**
     * @return this sections auto generated id
     */
    public int getID() {
        return id;
    }

}