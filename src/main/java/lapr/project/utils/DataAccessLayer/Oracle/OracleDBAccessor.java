package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles access to an Oracle Database
 */
public class OracleDBAccessor implements DBAccessor {

    private OracleDataSource oracleDataSource;
    private Connection oracleConnection;

    /*
     * Connection access specifications
     */
    private static final String SERVER_URL = "jdbc:oracle:thin:@//vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String INITIAL_SESSION_SCHEMA = "LAPR3_G38";
    private static final String SCHEMA_PASSWORD = "cygnus";

    /**
     * Restrict instantiation to current package
     */
    public OracleDBAccessor() {
        try {
            openConnexion();
            oracleConnection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
        }
    }

    /**
     * Connects to an OracleDB
     * @throws SQLException
     */
    public void openConnexion() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(SERVER_URL);
        oracleDataSource.setUser(INITIAL_SESSION_SCHEMA);
        oracleDataSource.setPassword(SCHEMA_PASSWORD);
    }

    /**
     * Verifies if the state of the connection is not null
     * @return true if connection is active
     */
    public boolean hasActiveConnection() {
        return oracleConnection != null;
    }

    /**
     * Finishes a transaction by committing changes
     */
    @Override
    public void commit() throws SQLException {
        oracleConnection.commit();
    }

    /**
     * Rolls a transaction back
     */
    @Override
    public void rollback() throws SQLException {
        oracleConnection.rollback();
    }

}
