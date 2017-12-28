package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * An Oracle Data Access Object
 * This class should be extended by
 */
public class OracleDAO {

    protected Connection oracleConnection;

    /**
     * Connects this Data Access Object to a database
     * if the databaseProductName matches the database to which this DAO is an implementation of.
     * @param connection Connects this DAO to a database
     * @return true if obtaining connection was possible, and that connection refers to an OracleDB
     * @throws SQLException
     */
    public boolean connectTo(Connection connection) throws SQLException {

        if (OracleDBAccessor.verifyConnectionIsOracle(connection)) {
            this.oracleConnection = connection;
            return true;
        }
        return false;
    }

    /**
     * Verifies if an Oracle Data Access Object has an active connection
     * @return true if connection is not null
     */
    boolean isConnected() {
        return this.oracleConnection != null;
    }

}
