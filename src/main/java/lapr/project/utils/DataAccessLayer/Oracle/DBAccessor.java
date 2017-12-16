package lapr.project.utils.DataAccessLayer.Oracle;

import java.sql.SQLException;

/**
 * Establishes basic access behaviour to a DataBase
 */
public interface DBAccessor {

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
    
}
