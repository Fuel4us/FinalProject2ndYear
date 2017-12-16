package lapr.project.utils.DataAccessLayer.Oracle;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles access to an Oracle Database
 */
class OracleDBAccessor {

    private OracleDataSource oracleDataSource;
    Connection oracleConnection;

    /*
     * Connection access specifications
     */
    private static final String SERVER_URL = "jdbc:oracle:thin:@//vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String INITIAL_SESSION_SCHEMA = "LAPR3_G38";
    private static final String SCHEMA_PASSWORD = "cygnus";

    private static final Logger ORACLE_ACCESS_LOG = Logger.getLogger("OracleAccessLog");

    /**
     * Restrict instantiation to current package
     */
    OracleDBAccessor() {
        try {
            openConnexion();
            oracleConnection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            logSQLException(e);
        }
    }

    /**
     * Opens a connection for this instance
     */
    private void openConnexion() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(SERVER_URL);
        oracleDataSource.setUser(INITIAL_SESSION_SCHEMA);
        oracleDataSource.setPassword(SCHEMA_PASSWORD);
    }

    /**
     * Logs a SQL Exception
     * @param e an instance of {@link SQLException}
     */
    void logSQLException(SQLException e) {
        ORACLE_ACCESS_LOG.log(Level.WARNING, e.getSQLState());
    }

}
