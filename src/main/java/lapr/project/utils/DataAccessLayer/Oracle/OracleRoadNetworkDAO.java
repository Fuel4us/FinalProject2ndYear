/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

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
import lapr.project.utils.DataAccessLayer.Abstraction.RoadNetworkDAO;


/**
 *
 * Retrieves the Road Network for a given project
 */
public class OracleRoadNetworkDAO extends OracleDAO implements RoadNetworkDAO {

    private PreparedStatement statement;

    public OracleRoadNetworkDAO() {}

    /**
     * Creates an instance of {@link RoadNetwork} from a given project name
     * @param projectName name of the project
     * @return instance of {@link RoadNetwork}
     * @throws SQLException
     */
    @Override
    public RoadNetwork retrieveRoadNetwork(String projectName) throws SQLException {
        ResultSet networkSet = statement.executeQuery(
                "SELECT * FROM ROADNETWORK, PROJECT WHERE ROADNETWORK.PROJECTNAME = PROJECT.NAME AND PROJECT.NAME = projectName;"
        );
        //retrieveRoadNetworkFromProject(projectName)
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

        //select sections
        ResultSet sectionSet = statement.executeQuery(
                "SELECT * FROM section WHERE SECTION.ID = NETWORKSECTION.SECTIONID AND NETWORKSECTION.NETWORKID = ROADNETWORK.ID AND ROADNETWORK.ID = networkID;"
        );
        //getSectionSet(networkID)
        while(sectionSet.next()){
            int sectionId = sectionSet.getInt("ID");
            //section needs origin and ending node
            Node beginningNode = new Node(sectionSet.getString("beginningNodeID"));
            Node endingNode = new Node(sectionSet.getString("endingNodeID"));

            //section's direction obtained in a similar way with vehicle data
            Direction roadDirection = null;
            Direction[] directionEnum = Direction.values();
            for (Direction direction : directionEnum) {
                String directionStr = sectionSet.getString("vehicleType");
                if (directionStr.equals(direction.toString())) {
                    roadDirection = direction;
                }
            }

            //select that determines section's road
            ResultSet roadSet = statement.executeQuery(
                    "SELECT * FROM ROAD WHERE SECTION.ID = sectionId AND ROAD.ID = SECTION.OWNINGROAD"
            );
            //getRoadSet(networkID)
            String roadID = roadSet.getString("ID");
            String roadName = roadSet.getString("name");
            String typology = roadSet.getString("typology");
            ResultSet roadTollSet = statement.executeQuery(
                    "SELECT * FROM TOLLFAREROAD WHERE ROAD.ID = roadID AND ROAD.ID = TOLLFAREROAD.ROADID"
            );
            //getRoadTollSet(roadID)
            List<Double> tollFareRoadList = new LinkedList<>();
            while (roadTollSet.next()) {
                Double tollFare = roadTollSet.getDouble("tollFare");
                tollFareRoadList.add(tollFare);
            }
            Road road = new Road(roadID, roadName, typology, tollFareRoadList);

            //select section's segments
            Collection<Segment> segments = new ArrayList<>();
            ResultSet segmentSet = statement.executeQuery(
                        "SELECT * FROM SEGMENT WHERE SECTION.ID = sectionId AND SEGMENT.SECTIONID = SECTION.ID"
            );
            //getSegmentsSet(sectionID)
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
            //section needs list of tollfare
            ResultSet sectionTollSet = statement.executeQuery(
                    "SELECT * FROM TOLLFARESECTION WHERE SECTION.ID = sectionID AND SECTION.ID = TOLLFARESECTION.SECTIONID"
            );
            //getSectionTollSet(sectionID)
            List<Double> tollFareSectionList = new LinkedList<>();
            while (sectionTollSet.next()) {
                Double tollFare = sectionTollSet.getDouble("tollFare");
                tollFareSectionList.add(tollFare);
            }
            Section section = new Section(beginningNode, endingNode, roadDirection, segments, road, tollFareSectionList);
            roadNetwork.addSection(beginningNode, endingNode, section);
        }
        return roadNetwork;
    }

    private void addNodesToRoadNetwork(String networkID, RoadNetwork roadNetwork) throws SQLException {
        ResultSet nodeSet = statement.executeQuery(
                "SELECT * FROM NODE WHERE NODE.ID = NETWORKNODE.NODEID AND NETWORKNODE.NETWORKID = ROADNETWORK.ID AND ROADNETWORK.ID = networkID;"
        );
        //getNodeSet(networkID)
        while (nodeSet.next()) {
            String nodeName;
            Node node;
            nodeName = nodeSet.getString("ID");
            node = new Node(nodeName);
            roadNetwork.addNode(node);
        }
    }

}

