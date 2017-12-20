package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.Analysis;

import java.sql.SQLException;

/**
 * Analysis Data Access Object
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
public interface AnalysisDAO extends DataAccessObject {

    /**
     * <p>
     * Store an analysis into data layer
     * </p>
     * <br>
     * <p>
     * May require a connection to be set through
     * {@link DataAccessObject}'s {@code connectTo} method
     * </p>
     * @param analysis an instance of {@link Analysis}
     */
    boolean storeAnalysis(Analysis analysis) throws SQLException;

}
