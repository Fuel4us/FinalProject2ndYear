package lapr.project.model;

import lapr.project.model.RoadNetwork.Road;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <p>
 * Handles database read and write operations
 * </p>
 */
public class DataBaseCommunicator {

    private OracleDataSource oracleDataSource;

    public DataBaseCommunicator() throws SQLException {
        openConnexion();
    }

    /**
     * Opens a connection for this instance
     */
    private void openConnexion() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL("jdbc:oracle:thin:@//vsrvbd1.dei.isep.ipp.pt:1521/pdborcl");
        oracleDataSource.setUser("LAPR3_G38");
        oracleDataSource.setPassword("cygnus");
    }

    /**
     * Stores network analysis to specified project
     * @param project The project to which the network analysis will be associated in the database
     * @param analysis The network analysis to be stored
     * @return true if storing operation succeeded
     */
    public boolean storeNetworkAnalysis(Project project, Analysis<Road> analysis) throws SQLException {

        Connection connection = oracleDataSource.getConnection();

        Statement statement = connection.createStatement();

        String projectID = project.getName();
        String analysisID = String.valueOf(analysis.exposeID());

        List<Road> path = analysis.showResults();

        //Associate every road of the path of the analysis with the analysis and store them
        for (Road road : path) {
            //associate roads (Analysed Roads) with this Analysis via its ID and the corresponding road ID
            statement.executeUpdate(
                    "INSERT INTO ANALYSEDROAD (ANALYSISID,ROADID) VALUES (" + analysisID + "," + road.getID() + ");");
        }

        //Insert analysis associating it with the project via its ID
        String analysisInsertionCommand =
                "INSERT INTO ANALYSIS (ID, PROJECTNAME) VALUES (" + analysisID + "," + projectID + ");";

        statement.executeUpdate(analysisInsertionCommand);

        connection.close();

        return false;
    }

}


