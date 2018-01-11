package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;

/**
 * An Oracle Data Access Object
 * This class should be extended by Data Access Objects which are implemented in regards to OracleDB specifications
 */
class OracleDAO {

    protected Connection oracleConnection;

    /**
     * Connects this Data Access Object to a database
     * if the databaseProductName matches the database to which this DAO is an implementation of.
     *
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
     * Verifies if a Data Access Object is connected
     */
    void verifyConnection() {
        if (!this.isConnected()) {
            DBAccessor.DB_ACCESS_LOG.log(Level.INFO, "No connection found in {0}", this.getClass());
        }
    }

    /**
     * Verifies if an Oracle Data Access Object has an active connection
     *
     * @return true if connection is not null
     */
    boolean isConnected() {
        return this.oracleConnection != null;
    }

    /**
     * Stores information that requires units
     *
     * @param measurable the {@link Measurable} to store
     */
    int storeStatisticalInfo(Measurable measurable) throws SQLException {

        try (CallableStatement storeMeasurableFunction = oracleConnection.prepareCall("{? = call STORE_MEASURABLE(?,?)}")) {

            double quantity = measurable.getQuantity();
            Unit unit = measurable.getUnit();

            storeMeasurableFunction.registerOutParameter(1, Types.INTEGER);

            storeMeasurableFunction.setDouble(2, quantity);
            storeMeasurableFunction.setString(3, unit.toString());

            storeMeasurableFunction.executeUpdate();

            return storeMeasurableFunction.getInt(1);
        }

    }

}
