/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import lapr.project.model.RoadNetwork.Direction;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Abstraction.RoadNetworkDAO;


/**
 *
 * Retrieves the Road Network for a given project
 */
public class OracleRoadNetworkDAO extends OracleDAO implements RoadNetworkDAO {

    public OracleRoadNetworkDAO() {}

    /**
     * Creates an instance of {@link RoadNetwork} from a given project name
     * @param projectName name of the project
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    @Override
    public RoadNetwork retrieveRoadNetwork(String projectName) throws SQLException {
        ResultSet networkSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call retrieveRoadNetworkFromProject(?)")) {
            callableStatement.setString(1, projectName);
            networkSet = callableStatement.executeQuery();
        }

        return retrieveRoadNetwork(networkSet);
    }

    /**
     * Creates an instance of {@link RoadNetwork} from a given ResultSet of project entities
     * @param resultSet ResultSet object
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    private RoadNetwork retrieveRoadNetwork(ResultSet resultSet) throws SQLException {
        String networkID = resultSet.getString("ID");
        RoadNetwork roadNetwork = new RoadNetwork(true);
        roadNetwork.setId(networkID);

        addNodesToRoadNetwork(networkID, roadNetwork);
        addSectionsToRoadNetwork(networkID, roadNetwork);

        return roadNetwork;
    }

    private void addNodesToRoadNetwork(String networkID, RoadNetwork roadNetwork) throws SQLException {

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getNodeSet(?)")) {
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

    private List<Double> fillRoadTollFareList(String roadID) throws SQLException {
        List<Double> tollFareRoadList = new LinkedList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getRoadTollSet(?)")) {
            callableStatement.setString(1, roadID);
            ResultSet roadTollSet = callableStatement.executeQuery();
            while (roadTollSet.next()) {
                Double tollFare = roadTollSet.getDouble("tollFare");
                tollFareRoadList.add(tollFare);
            }
        }

        return tollFareRoadList;
    }

    private Road createRoad(int sectionID) throws SQLException {
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getRoadSet(?)")) {
            callableStatement.setInt(1, sectionID);
            ResultSet roadSet = callableStatement.executeQuery();
            String roadID = roadSet.getString("ID");
            String roadName = roadSet.getString("name");
            String typology = roadSet.getString("typology");

            List<Double> tollFareRoadList = fillRoadTollFareList(roadID);
            return new Road(roadID, roadName, typology, tollFareRoadList);
        }
    }

    private Collection<Segment> fetchSectionSegments(int sectionID) throws SQLException {
        Collection<Segment> segments = new ArrayList<>();

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getSegmentsSet(?)")) {
            callableStatement.setInt(1, sectionID);
            ResultSet segmentSet = callableStatement.executeQuery();
            while(segmentSet.next()) {
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

    private List<Double> fillSectionTollFareList(int sectionID) throws SQLException {
        List<Double> tollFareSectionList = new LinkedList<>();
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getSectionTollSet(?)")) {
            callableStatement.setInt(1, sectionID);
            ResultSet sectionTollSet = callableStatement.executeQuery();
            while (sectionTollSet.next()) {
                Double tollFare = sectionTollSet.getDouble("tollFare");
                tollFareSectionList.add(tollFare);
            }
        }

        return tollFareSectionList;
    }

    private void addSectionsToRoadNetwork(String networkID, RoadNetwork roadNetwork) throws SQLException {

        try (CallableStatement callableStatement = oracleConnection.prepareCall("call getSectionSet(?)")) {
            callableStatement.setString(1, networkID);
            ResultSet sectionSet = callableStatement.executeQuery();
            while(sectionSet.next()){
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

}

