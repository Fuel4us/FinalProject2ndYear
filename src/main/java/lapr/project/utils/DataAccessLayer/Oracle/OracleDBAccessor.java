package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles access to an Oracle Database
 */
public class OracleDBAccessor implements DBAccessor {

    private OracleDataSource oracleDataSource;
    private Connection oracleConnection;

    /*
    Database product name
     */
    private static final String ORACLE_DATABASE_PRODUCT_NAME = "Oracle";

    /*
     * Connection access specifications
     */
    private static final String SERVER_URL = "jdbc:oracle:thin:@//vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String INITIAL_SESSION_SCHEMA = "LAPR3_G38";
    private static final String SCHEMA_ACCESS_KEY = "cygnus";

    /**
     * Restrict instantiation to current package
     */
    public OracleDBAccessor() {
        try {
            initConnexion();
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
        }
    }

    /**
     * Initializes connection by setting its properties
     * @throws SQLException for invalid properties
     */
    private void initConnexion() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(SERVER_URL);
        oracleDataSource.setUser(INITIAL_SESSION_SCHEMA);
        oracleDataSource.setPassword(SCHEMA_ACCESS_KEY);
    }

    /**
     * Connects to an OracleDB
     * @throws SQLException
     */
    public Connection openConnexion() throws SQLException {

        return oracleDataSource.getConnection();
    }

    /**
     * Verifies if the state of the connection is not null
     * @return true if connection is active
     */
    public boolean hasActiveConnection() {
        return oracleConnection != null;
    }

    /**
     * Verifies that a given {@link Connection} is done to an OracleDB
     * @param connection An instance of {@link Connection}
     * @return true if the database product name matches that of an OracleDB
     * @throws SQLException requires connection to be valid
     */
    static boolean verifyConnectionIsOracle(Connection connection) throws SQLException {
        String databaseProductName = "";

        if (connection != null) {
            DatabaseMetaData metaData = connection.getMetaData();

            if (metaData != null) {
                databaseProductName = metaData.getDatabaseProductName();
            }

        }

        return databaseProductName.equalsIgnoreCase(ORACLE_DATABASE_PRODUCT_NAME);
    }

}
