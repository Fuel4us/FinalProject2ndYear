package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Establishes basic access behaviour to a DataBase
 */
public interface DBAccessor {

    Logger DB_ACCESS_LOG = Logger.getLogger("DataBaseAccessLog");

    /**
     * Connects to a database
     * @throws SQLException
     */
    void openConnexion() throws SQLException;

    /**
     * Verifies if the state of the connection is active (i.e not null)
     * @return true if connection is active
     */
    boolean hasActiveConnection();

    /**
     * Finishes a transaction by committing changes
     */
    void commit() throws SQLException;

    /**
     * Rolls a transaction back
     */
    void rollback() throws SQLException;


}
