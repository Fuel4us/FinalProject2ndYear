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
    //ToDo Create ProjectDAO (Data Access Object)

    public DataBaseCommunicator(DataSource dataSource) {
        if (dataSource instanceof OracleDataSource) {
            this.dbAccessor = new OracleDBAccessor();
            this.analysisStorage = new OracleAnalysisDAO((OracleDataSource) dbAccessor.source());
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
            //ToDo Store analyzed roads (generated report) into respective table
            analysisStorage.storeAnalysis(analysis);
            dbAccessor.commit();
        } catch (SQLException e) {
            if (dbAccessor.hasActiveConnection()) {
                try {
                    dbAccessor.rollback();
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


