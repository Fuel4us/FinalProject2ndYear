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
            //select para ir buscar os nodes
            String nodeName;
            Node node;
            nodeName = resultSet.getString("id");
            node = new Node(nodeName);
            roadNetwork.addNode(node);

        //select pra ir buscar as sections
        //section precisa dos dois nodes origem e destino
        Node begginningNode;
        Node endingNode;
//ir buscar nodes
        Direction direction;
        Collection<Segment> segments = new ArrayList<>();
        Road road;
//ir buscar direction da mesma forma que eu faço com os enums do veiculo
//select que dá um result set dos segments pertencentes a esta section
//while que itera sobre o result set dos segments, e cria um segment, adicionando-o à section
//select que determina qual a road desta section
//adicionar section
        //resultSet dos segments
        while(resultSet.next()){
            int index = resultSet.getInt("index");
            double initialHeight = resultSet.getDouble("initialHeight");
            double finalHeight = resultSet.getDouble("finalheight");
            double length = resultSet.getDouble("length");
            double windAngle = resultSet.getDouble("windAngle");
            double windSpeed = resultSet.getDouble("windSpeed");
            double maxVelocity = resultSet.getDouble("maxVelocity");
            double minVelocity = resultSet.getDouble("minVelocity");
            segments.add(new Segment(index,initialHeight,finalHeight,length,windAngle,windSpeed,maxVelocity,minVelocity));
        }
        }
        return roadNetwork;
    }

}

