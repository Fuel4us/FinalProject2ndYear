package lapr.project.utils.DataAccessLayer.Abstraction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data Access Object
 * <p>
 * Ensures insulation between the Data Layer
 * and the base application.
 * </p>
 * <p>
 * This interface should be implemented by objects
 * whose information needs to be saved in a database.
 * </p>
 * <p>
 * The various implementations deal with the concrete database specifications,
 * while this interface ensures an abstraction is provided
 * to deal with data layer operations regardless of those same specifications.
 * </p>
 */
interface DataAccessObject {

    boolean connectTo(Connection connection) throws SQLException;

}
