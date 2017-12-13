package lapr.project.model;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.DriverManager;
import java.sql.SQLException;

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
    public boolean storeNetworkAnalysis(Project project, Analysis analysis) {
        return false;
    }


}


