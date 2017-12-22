package lapr.project.utils.DataAccessLayer.Abstraction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Establishes basic access behaviour to a DataBase and logging utility
 */
public interface DBAccessor {

    Logger DB_ACCESS_LOG = Logger.getLogger("DataBaseAccessLog");

    /**
     * Connects to a database
     * @throws SQLException
     */
    Connection openConnexion() throws SQLException;

    /**
     * Verifies if the state of the connection is active (i.e not null)
     * @return true if connection is active
     */
    boolean hasActiveConnection();

    /**
     * Logs a SQL Exception
     * @param e an instance of {@link SQLException}
     */
    static void logSQLException(SQLException e) {
        DB_ACCESS_LOG.log(Level.WARNING, e.getSQLState());
        DB_ACCESS_LOG.log(Level.WARNING, () -> {
            StringBuilder errorBuffer = new StringBuilder();
            while (e.getNextException() != null) {
                errorBuffer.append(e.getNextException());
            }
            return errorBuffer.toString();
        });
        DB_ACCESS_LOG.log(Level.WARNING, "Error Code: " + String.valueOf(e.getErrorCode()));
    }

}
