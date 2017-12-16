package lapr.project.model;

import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Oracle.OracleAnalysisDAO;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * Handles database read and write operations
 * </p>
 */
public class DataBaseCommunicator {

    private AnalysisDAO analysisStorage;
    //ToDo Create ProjectDAO (Data Access Object)

    public DataBaseCommunicator() {
        this.analysisStorage = new OracleAnalysisDAO();
    }

    /**
     * Stores network analysis to specified project
     * @param project The project to which the network analysis will be associated in the database
     * @param analysis The network analysis to be stored
     * @return true if storing operation succeeded
     */
    public void storeNetworkAnalysis(Project project, Analysis analysis) {
        //ToDo Start Transaction
        try {
            //ToDo Store analyzed roads (generated report) into respective table
            analysisStorage.storeAnalysis(analysis);
        } catch (SQLException e) {
            if () {
            }
        }
        //ToDo Commit transaction
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


