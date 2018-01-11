/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.RoadNetwork.*;
import lapr.project.utils.DataAccessLayer.Abstraction.RoadNetworkDAO;
import lapr.project.utils.Graph.Edge;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * Retrieves the Road Network for a given project
 */
public class OracleRoadNetworkDAO extends OracleDAO implements RoadNetworkDAO {

    private Double tollFare;
    private static final String fare = "tollFare";


    /**
     * Creates an instance of {@link RoadNetwork} from a given project name
     * @param projectName name of the project
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    @Override
    public RoadNetwork retrieveRoadNetwork(String projectName) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection
                .prepareCall("CALL retrieveRoadNetworkFromProject(?,?)")) {

            callableStatement.setString(1, projectName);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            ResultSet networkSet = (ResultSet) callableStatement.getObject(2);
            return retrieveRoadNetwork(networkSet);
        }
    }

    /**
     * Creates an instance of {@link RoadNetwork} from a given ResultSet of project entities
     * @param resultSet ResultSet object
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    private RoadNetwork retrieveRoadNetwork(ResultSet resultSet) throws SQLException {
        String networkID = resultSet.getString("ID");
        String description = resultSet.getString("description");
        RoadNetwork roadNetwork = new RoadNetwork(networkID, description);

        addNodesToRoadNetwork(networkID, roadNetwork);
        addSectionsToRoadNetwork(networkID, roadNetwork);

        return roadNetwork;
    }

    /**
     * Retrieves node entities from database and creates objects {@link Node} to place in respective {@link RoadNetwork}
     * @param networkID network identifier
     * @param roadNetwork instance of {@link RoadNetwork}
     * @throws SQLException
     */
    private void addNodesToRoadNetwork(String networkID, RoadNetwork roadNetwork) throws SQLException {

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getNodeSet(?)")) {
            callableStatement.setString(1, networkID);
            ResultSet nodeSet = callableStatement.executeQuery();
            while (nodeSet.next()) {
                String nodeName;
                Node node;
                nodeName = nodeSet.getString("ID");
                node = new Node(nodeName);
                roadNetwork.addNode(node);
            }
        }
    }

    /**
     * Determines {@link Section} direction
     * @param sectionSet instance of {@link ResultSet}
     * @return instance of {@link Direction}
     * @throws SQLException
     */
    private Direction determineDirection(ResultSet sectionSet) throws SQLException {
        Direction roadDirection = null;
        Direction[] directionEnum = Direction.values();
        for (Direction direction : directionEnum) {
            String directionStr = sectionSet.getString("vehicleType");
            if (directionStr.equals(direction.toString())) {
                roadDirection = direction;
            }
        }
        return roadDirection;
    }

    /**
     * Retrieves from database and creates tollFareList of Road
     * @param roadID Road identifier
     * @return {@link List} of {@link Double} tollFare belonging to {@link Road}
     * @throws SQLException
     */
    private List<Double> fillRoadTollFareList(String roadID) throws SQLException {
        List<Double> tollFareRoadList = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getRoadTollSet(?)")) {
            callableStatement.setString(1, roadID);
            ResultSet roadTollSet = callableStatement.executeQuery();
            while (roadTollSet.next()) {
                tollFare = roadTollSet.getDouble("tollFare");
                tollFareRoadList.add(tollFare);
            }
        }

        return tollFareRoadList;
    }

    /**
     * Retrieves from database and creates {@link Road} object
     * @param sectionID identifier of {@link Section} contained in {@link Road}
     * @return instance of {@link Road}
     * @throws SQLException
     */
    private Road createRoad(int sectionID) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getRoadSet(?)")) {
            callableStatement.setInt(1, sectionID);
            ResultSet roadSet = callableStatement.executeQuery();
            String roadID = roadSet.getString("ID");
            String roadName = roadSet.getString("name");
            String typology = roadSet.getString("typology");

            List<Double> tollFareRoadList = fillRoadTollFareList(roadID);
            return new Road(roadID, roadName, typology, tollFareRoadList);
        }
    }

    /**
     * Retrieves instances of {@link Segment} contained on a given {@link Section} from database
     * @param sectionID identifier of {@link Section}
     * @return {@link Collection} of instances of {@link Segment}
     * @throws SQLException
     */
    private Collection<Segment> fetchSectionSegments(int sectionID) throws SQLException {
        Collection<Segment> segments = new ArrayList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getSegmentsSet(?)")) {
            callableStatement.setInt(1, sectionID);
            ResultSet segmentSet = callableStatement.executeQuery();
            while (segmentSet.next()) {
                int index = segmentSet.getInt("index");
                double initialHeight = segmentSet.getDouble("initialHeight");
                double finalHeight = segmentSet.getDouble("finalHeight");
                double length = segmentSet.getDouble("length");
                double windAngle = segmentSet.getDouble("windAngle");
                double windSpeed = segmentSet.getDouble("windSpeed");
                double maxVelocity = segmentSet.getDouble("maxVelocity");
                double minVelocity = segmentSet.getDouble("minVelocity");
                segments.add(new Segment(index, initialHeight, finalHeight, length, windAngle, windSpeed, maxVelocity, minVelocity));
            }
        }
        return segments;
    }

    /**
     * Retrieves from database and creates tollFareList of Section
     * @param sectionID section identifier
     * @return {@link List} of {@link Double} tollFare belonging to {@link Section}
     * @throws SQLException
     */
    private List<Double> fillSectionTollFareList(int sectionID) throws SQLException {
        List<Double> tollFareSectionList = new LinkedList<>();
        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getSectionTollSet(?)")) {
            callableStatement.setInt(1, sectionID);
            ResultSet sectionTollSet = callableStatement.executeQuery();
            while (sectionTollSet.next()) {
                tollFare = sectionTollSet.getDouble("tollFare");
                tollFareSectionList.add(tollFare);
            }
        }

        return tollFareSectionList;
    }

    /**
     * Retrieves and creates instances of {@link Section} from database, adding them to a given {@link RoadNetwork}
     * @param networkID identifier of {@link RoadNetwork}
     * @param roadNetwork instance of {@link RoadNetwork}
     * @throws SQLException
     */
    private void addSectionsToRoadNetwork(String networkID, RoadNetwork roadNetwork) throws SQLException {

        try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL getSectionSet(?)")) {
            callableStatement.setString(1, networkID);
            ResultSet sectionSet = callableStatement.executeQuery();
            while (sectionSet.next()) {
                int sectionId = sectionSet.getInt("ID");

                Node beginningNode = new Node(sectionSet.getString("beginningNodeID"));
                Node endingNode = new Node(sectionSet.getString("endingNodeID"));

                Direction roadDirection = determineDirection(sectionSet);

                Road road = createRoad(sectionId);

                Collection<Segment> segments = fetchSectionSegments(sectionId);

                List<Double> tollFareSectionList = fillSectionTollFareList(sectionId);

                Section section = new Section(beginningNode, endingNode, roadDirection, segments, road, tollFareSectionList);
                roadNetwork.addSection(beginningNode, endingNode, section);
            }
        }

    }

    /**
     * Stores a {@link RoadNetwork} in database
     * @param roadNetwork instance of {@link RoadNetwork}
     * @param projectName identifier of {@link lapr.project.model.Project}
     * @throws SQLException
     */
    @Override
    public void storeRoadNetwork(RoadNetwork roadNetwork, String projectName) throws SQLException {
        verifyConnection();

        String networkID = storeRoadNetworkInfo(roadNetwork, projectName);
        storeRoadNetworkGraph(roadNetwork, networkID);

    }

    /**
     * Stores information of RoadNetwork
     * @param roadNetwork the {@link RoadNetwork} to store
     * @param projectName identifier of {@link lapr.project.model.Project}
     * @return String networkID
     * @throws java.sql.SQLException
     */
    private String storeRoadNetworkInfo(RoadNetwork roadNetwork, String projectName) throws SQLException {

        try (CallableStatement storeRoadNetworkInfoProcedure = oracleConnection.prepareCall("CALL storeRoadNetworkInfoProcedure(?,?,?)")) {

            String networkID = roadNetwork.getId();
            String description = roadNetwork.getDescription();
            storeRoadNetworkInfoProcedure.setString("id", networkID);
            storeRoadNetworkInfoProcedure.setString("description", description);
            storeRoadNetworkInfoProcedure.setString("projectName", projectName);

            storeRoadNetworkInfoProcedure.executeUpdate();
            return networkID;
        }
    }

    /**
     * Stores edges and vertexes of RoadNetwork
     * @param roadNetwork the {@link RoadNetwork} to store
     * @param networkID roadNetwork identifier
     * @throws SQLException
     */
    void storeRoadNetworkGraph(RoadNetwork roadNetwork, String networkID) throws SQLException {

        Iterable<Node> nodes = roadNetwork.vertices();
        for (Node node : nodes) {
            storeNode(node, networkID);
        }

        storeSections(roadNetwork, networkID);
    }

    /**
     * Stores instances of {@link Section} and respective information
     * @param roadNetwork the {@link RoadNetwork} to store
     * @param networkID roadNetwork identifier
     * @throws SQLException
     */
    private void storeSections(RoadNetwork roadNetwork, String networkID) throws SQLException {

        List<Road> roadsToStore = new LinkedList<>();

        List<Edge<Node, Section>> edges = roadNetwork.getEdges();
        List<Section> sections = new LinkedList<>();
        for (Edge<Node, Section> edge : edges) {
            sections.add(edge.getElement());
        }

        List<Integer> storedSections = new LinkedList<>();
        for (Section section : sections) {
            Road road = section.getOwningRoad();
            if (!roadsToStore.contains(road)) {
                roadsToStore.add(section.getOwningRoad());
                storeRoad(road);
                List<Double> tollFareList = road.getTollFare();
                for (Double tollFare : tollFareList) {
                    storeTollFareRoad(tollFare, road.getId());
                }
            }
            if (!storedSections.contains(section.getID())) {
                storeSection(section, networkID);
                storedSections.add(section.getID());
                List<Double> tollFareList = section.getTollFare();
                for (Double tollFare : tollFareList) {
                    storeTollFareSection(tollFare, section.getID());
                }
                Collection<Segment> segments = section.getSegments();
                for (Segment segment : segments) {
                    storeSegment(segment, section.getID());
                }
            }
        }
    }

    /**
     * Stores instances of {@link Node} from a given {@link RoadNetwork} in database
     * @param node instance of {@link Node}
     * @param networkID {@link RoadNetwork} identifier
     * @throws SQLException
     */
    private void storeNode(Node node, String networkID) throws SQLException {
        try (CallableStatement storeNodeProcedure = oracleConnection.prepareCall("CALL storeNodeProcedure(?,?)")) {

            storeNodeProcedure.setString("id", node.getId());
            storeNodeProcedure.setString("networkID", networkID);

            storeNodeProcedure.executeUpdate();
        }
    }

    /**
     * Stores instances of {@link Road} in database
     * @param road instance of {@link Road}
     * @throws SQLException
     */
    private void storeRoad(Road road) throws SQLException {
        try (CallableStatement storeRoadProcedure = oracleConnection.prepareCall("CALL storeRoadProcedure(?,?,?)")) {

            storeRoadProcedure.setString("id", road.getId());
            storeRoadProcedure.setString("name", road.getName());
            storeRoadProcedure.setString("typology", road.getTypology());

            storeRoadProcedure.executeUpdate();
        }
    }

    /**
     * Stores instances of {@link Section} from a given {@link RoadNetwork} in database
     * @param section instance of {@link Section}
     * @param networkID {@link RoadNetwork} identifier
     * @throws SQLException
     */
    private void storeSection(Section section, String networkID) throws SQLException {
        try (CallableStatement storeSectionProcedure = oracleConnection.prepareCall("CALL storeSectionProcedure(?,?,?,?,?,?)")) {

            storeSectionProcedure.setInt("id", section.getID());
            storeSectionProcedure.setString("networkID", networkID);
            storeSectionProcedure.setString("beginningNodeID", section.getOriginVertex().getElement());
            storeSectionProcedure.setString("endingNodeID", section.getDestinyVertex().getElement());
            storeSectionProcedure.setString("direction", section.getDirection().toString());
            storeSectionProcedure.setString("owningRoadID", section.getOwningRoad().getId());

            storeSectionProcedure.executeUpdate();
        }
    }

    /**
     * Stores a given road tollFare on database
     * @param tollFare {@link Double} tollFare belonging to {@link Road}
     * @param roadID road identifier
     * @throws SQLException
     */
    private void storeTollFareRoad(Double tollFare, String roadID) throws SQLException {
        try (CallableStatement storeTollFareRoadProcedure = oracleConnection.prepareCall("CALL storeTollFareRoadProcedure(?,?)")) {

            storeTollFareRoadProcedure.setString("roadID", roadID);
            storeTollFareRoadProcedure.setDouble("tollFare", tollFare);

            storeTollFareRoadProcedure.executeUpdate();
        }
    }

    /**
     * Stores a given section tollFare on database
     * @param tollFare {@link Double} tollFare belonging to {@link Section}
     * @param sectionID section identifier
     * @throws SQLException
     */
    private void storeTollFareSection(Double tollFare, int sectionID) throws SQLException {
        try (CallableStatement storeTollFareSectionProcedure = oracleConnection.prepareCall("CALL storeTollFareSectionProcedure(?,?)")) {

            storeTollFareSectionProcedure.setInt("sectionID", sectionID);
            storeTollFareSectionProcedure.setDouble("tollFare", tollFare);

            storeTollFareSectionProcedure.executeUpdate();
        }
    }

    /**
     * Stores instances of {@link Segment} from a given {@link Section} in database
     * @param segment instance of {@link Segment}
     * @param sectionID {@link Section} identifier
     * @throws SQLException
     */
    private void storeSegment(Segment segment, int sectionID) throws SQLException {
        try (CallableStatement storeSegmentProcedure = oracleConnection.prepareCall("CALL storeSegmentProcedure(?,?,?,?,?,?,?,?,?)")) {

            segment.storeSegmentInformation(storeSegmentProcedure);
            storeSegmentProcedure.setInt("sectionID", sectionID);

            storeSegmentProcedure.executeUpdate();
        }
    }
}

