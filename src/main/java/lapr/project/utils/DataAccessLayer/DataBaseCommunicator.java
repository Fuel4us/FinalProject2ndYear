package lapr.project.utils.DataAccessLayer;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Oracle.OracleAnalysisDAO;
import lapr.project.utils.DataAccessLayer.Oracle.OracleDBAccessor;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * Handles database transaction flow
 * </p>
 */
public class DataBaseCommunicator {

    private DBAccessor dbAccessor;
    private AnalysisDAO analysisStorage;
    private Connection connection;

    /**
     * <p>
     * Creates an instance of this class, taking into account
     * the {@link DataSource} that will be used to make the connection.
     * </p>
     * <br>
     * <p>
     * Initialization of {@link DBAccessor} and corresponding Data Access Objects
     * is inferred through the type of the implementation of the DataSource instance,
     * which means multiple types of databases may be supported in the
     * future without alterations in this class.
     * </p>
     * @param dataSource Indicates the database to which to connect
     */
    public DataBaseCommunicator(DataSource dataSource) {
        if (dataSource instanceof OracleDataSource) {
            this.dbAccessor = new OracleDBAccessor();
            this.analysisStorage = new OracleAnalysisDAO();
        }
    }

    /** 
     * Stores network analysis in a database
     * @param analysis The network analysis to be stored
     */
    public boolean storeNetworkAnalysis(Analysis analysis) {
        try {
            //Start Transaction
            connection = dbAccessor.openConnexion();
            connection.setAutoCommit(false);

            if (analysisStorage.connectTo(connection)) {
                //ToDo Store analyzed sections (generated report) into respective table
                analysisStorage.storeAnalysis(analysis);
                connection.commit();
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            if (dbAccessor.hasActiveConnection()) {
                try {
                    connection.rollback();
                    DBAccessor.logSQLException(e);
                } catch (SQLException ex) {
                    DBAccessor.logSQLException(ex);
                }
            }
        }
        return false;
    }

    public List<Project> fetchProjectList() {
        //ToDo return all projects through ProjectDAO
        throw new UnsupportedOperationException();
    }

    public boolean storeProject(Project p) {
        //ToDo Store project through ProjectDAO
        throw new UnsupportedOperationException();
    }

    /**
     * Package-private setters for <em>testing purposes</em> only.
     * <br>
     * This implementation relies on the fact that
     * <em>this is the only class in this package.</em>
     * thus being only invokable by this class' test suite
     */
    void setDbAccessor(DBAccessor dbAccessor) {
        this.dbAccessor = dbAccessor;
    }

    /**
     * Package-private setters for <em>testing purposes</em> only.
     * <br>
     * This implementation relies on the fact that
     * <em>this is the only class in this package.</em>
     * thus being only invokable by this class' test suite
     */
    void setAnalysisStorage(AnalysisDAO analysisStorage) {
        this.analysisStorage = analysisStorage;
    }

}
