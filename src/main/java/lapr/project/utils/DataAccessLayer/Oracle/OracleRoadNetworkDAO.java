/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.Section;


/**
 *
 * @author 1160950
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


    
    private RoadNetwork<Node, Section> createRoadNetwork(ResultSet resultSet) throws SQLException {
        //parametros das RoadNetwork
        RoadNetwork<Node, Section> roadNetwork = new RoadNetwork<>(true);
        while(resultSet.next()){
            String nodeName;
            Node node;
            nodeName = resultSet.getString("id");
            node = new Node(nodeName);
            roadNetwork.addNode(node);
        }
        
        /**
        section precisa dos dois nodes origem e destino
        Node begginningNode;
        Node endingNode;
        Direction direction;
        Collection<Segment> segments;
        Road road;
        */      
        
        return roadNetwork;
    }
    
}

