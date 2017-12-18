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
import lapr.project.model.RoadNetwork.Direction;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;


/**
 *
 * ToDo
 */
public class OracleRoadNetworkDAO extends OracleDBAccessor{

    private PreparedStatement statement;

    public OracleRoadNetworkDAO() {
        super();
        try {
            statement = oracleConnection.prepareStatement("");
        } catch (SQLException e) {
            super.logSQLException(e);
        }
    }


//    /**
//     * Creates an instance of {@link RoadNetwork} from a given ResultSet of project entities
//     * @param resultSet
//     * @return
//     * @throws SQLException
//     */
//    private RoadNetwork createRoadNetwork(ResultSet resultSet) throws SQLException {
//        String networkID = resultSet.getString("ID");
//        RoadNetwork roadNetwork = new RoadNetwork(true);
//        roadNetwork.setId(networkID);
//
//        //selects nodes
//        ResultSet nodeSet = statement.executeQuery(
//                "SELECT * FROM NODE WHERE NODE.ID = NETWORKNODE.NODEID AND NETWORKNODE.NETWORKID = ROADNETWORK.ID AND ROADNETWORK.ID = networkID;"
//        );
//        while (nodeSet.next()) {
//            String nodeName;
//            Node node;
//            nodeName = resultSet.getString("id");
//            node = new Node(nodeName);
//            roadNetwork.addNode(node);
//        }
//
//        //select pra ir buscar as sections
//        ResultSet sectionSet = statement.executeQuery(
//                "SELECT * FROM THROTTLE WHERE SECTION.ID = NETWORKSECTION.SECTIONID AND NETWORKSECTION.NETWORKID = ROADNETWORK.ID AND ROADNETWORK.ID = networkID;"
//        );
//        while(sectionSet.next()){
//            int sectionId = sectionSet.getInt("ID");
//            //section precisa dos dois nodes origem e destino
//            Node begginningNode;
//            Node endingNode;
//            Direction direction;
//            Collection<Segment> segments = new ArrayList<>();
//            Road road;
//            //ir buscar direction da mesma forma que eu faço com os enums do veiculo
//            // select que determina qual a road desta section
//                ResultSet segmentSet = statement.executeQuery(
//                        "SELECT * FROM SEGMENT WHERE SECTION.ID = sectionId AND SEGMENT.SECTIONID = SECTION.ID"
//                );
//                while(segmentSet.next()) {
//                    int index = resultSet.getInt("index");
//                    double initialHeight = segmentSet.getDouble("initialHeight");
//                    double finalHeight = segmentSet.getDouble("finalheight");
//                    double length = segmentSet.getDouble("length");
//                    double windAngle = segmentSet.getDouble("windAngle");
//                    double windSpeed = segmentSet.getDouble("windSpeed");
//                    double maxVelocity = segmentSet.getDouble("maxVelocity");
//                    double minVelocity = segmentSet.getDouble("minVelocity");
//                    segments.add(new Segment(index, initialHeight, finalHeight, length, windAngle, windSpeed, maxVelocity, minVelocity));
//                }
//                //adicionar section
//        }
//        return roadNetwork;
//    }

}

