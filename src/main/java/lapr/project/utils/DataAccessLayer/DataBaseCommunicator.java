package lapr.project.utils.DataAccessLayer;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;
import lapr.project.utils.DataAccessLayer.Oracle.OracleAnalysisDAO;
import lapr.project.utils.DataAccessLayer.Oracle.OracleDBAccessor;
import lapr.project.utils.DataAccessLayer.Oracle.OracleProjectDAO;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Handles database transaction flow
 * </p>
 */
public class DataBaseCommunicator {

    private DBAccessor dbAccessor;
    private Connection connection;

    /*/
    Data Access Objects
     */
    private AnalysisDAO analysisStorage;
    private ProjectDAO projectStorage;

    /**
     * <p>
     * Creates an instance of this class, taking into account the
     * {@link DataSource} that will be used to make the connection.
     * </p>
     * <br>
     * <p>
     * Initialization of {@link DBAccessor} and corresponding Data Access
     * Objects is inferred through the type of the implementation of the
     * DataSource instance, which means multiple types of databases may be
     * supported in the future without alterations in this class.
     * </p>
     * @param dataSource Indicates the database to which to connect
     */
    public DataBaseCommunicator(DataSource dataSource) {
        if (dataSource instanceof OracleDataSource) {
            this.dbAccessor = new OracleDBAccessor();
            this.analysisStorage = new OracleAnalysisDAO();
            this.projectStorage = new OracleProjectDAO();
        }
    }

    /**
     * Attempts rolling back on transaction failure
     * @param connection the connection on which the exception was generated
     * @param e the exception that caused the transaction failure
     */
    private static void attemptFailSafeRecovery(Connection connection, SQLException e) {
        if (connection != null) {
            try {
                connection.rollback();
                DBAccessor.logSQLException(e);
            } catch (SQLException ex) {
                DBAccessor.logSQLException(ex);
            }
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

            //Allow Data Access Object behaviour through newly opened connexion
            if (analysisStorage.connectTo(connection)) {
                analysisStorage.storeAnalysis(analysis);
                connection.commit();
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            attemptFailSafeRecovery(connection, e);
        }
        return false;
    }

    /**
     * Fetches the list of projects stored in the database
     * @return a {@link List} containing all stored instances of {@link Project}
     */
    public List<Project> fetchProjectList() {

        List<Project> projects = new ArrayList<>();
        try {
            //Start Transaction
            connection = dbAccessor.openConnexion();
            connection.setAutoCommit(false);

            //Allow Data Access Object behaviour through newly opened connexion
            if (projectStorage.connectTo(connection)) {
                projects = projectStorage.fetchProjects();
                connection.commit();
                connection.close();
            }

        } catch (SQLException e) {
            attemptFailSafeRecovery(connection, e);
        }
        return projects;
    }

    /**
     * Adds a given project passed by parameter to the database
     * @param project The project to store in the database
     */
    public boolean addProject(Project project) {

        try {
            //Start Transaction
            Connection connection = dbAccessor.openConnexion();
            connection.setAutoCommit(false);

            //Allow Data Access Object behaviour through newly opened connexion
            if (projectStorage.connectTo(connection)) {
                projectStorage.storeProject(project);
                connection.commit();
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            attemptFailSafeRecovery(connection, e);
        }
        return false;
    }

    /**
     * Changes project data
     * Has to be called before calling methods setName and setDescription of {@link Project}
     * @param project The project to update in the database
     */
    public boolean changeProjectData(Project project, String newName, String newDescription) {

        try {
            //Start Transaction
            Connection connection = dbAccessor.openConnexion();
            connection.setAutoCommit(false);

            //Allow Data Access Object behaviour through newly opened connexion
            if (projectStorage.connectTo(connection)) {
                if(project.getDescription().equals(newDescription)) {
                    projectStorage.changeDescription(project, newDescription);
                }
                if(project.getName().equals(newName)){
                    projectStorage.changeProjectName(project, newName);
                }
                connection.commit();
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            attemptFailSafeRecovery(connection, e);
        }
        return false;
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
