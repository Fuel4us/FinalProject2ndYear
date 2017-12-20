package lapr.project.utils.DataAccessLayer;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Oracle.OracleAnalysisDAO;
import lapr.project.utils.DataAccessLayer.Oracle.OracleDBAccessor;
import lapr.project.utils.Graph.GraphAlgorithms;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            //ToDo encapsulate in analysisStorage.storeAnalysis(analysis) via procedure call
//            try (PreparedStatement saveStatement = connection.prepareStatement(
//                    "INSERT INTO ANALYSIS(ID, PROJECTNAME) VALUES (?, ?)"
//            )) {
//
//                analysisStorage.storeAnalysis(analysis);
//
//            }

            //ToDo Encapsulate behaviour in dbAccessor? //dbAccessor.commit();
            connection.commit();
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


