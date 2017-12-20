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
            this.analysisStorage = null; //  this.analysisStorage = new OracleAnalysisDAO()
        }
    }

    /**
     * Stores network analysis in a database
     * @param analysis The network analysis to be stored
     * @return true if storing operation succeeded
     */
    public void storeNetworkAnalysis(Analysis analysis) {
        try {
            //Start Transaction
            Connection connection = dbAccessor.openConnexion();
            connection.setAutoCommit(false);
            //ToDo Store analyzed sections (generated report) into respective table

            //ToDo Replace by procedure call
            analysisStorage.storeAnalysis(analysis);

            //ToDo Encapsulate behaviour in dbAccessor? //dbAccessor.commit();
            connection.commit();
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

    }

    public List<Project> fetchProjectList() {
        //ToDo return all projects through ProjectDAO
        List<Project> listProjects = null;
        return listProjects;
    }

    public boolean storeProject(Project p) {
        //ToDo Store project through ProjectDAO
        return true;
    }

}


