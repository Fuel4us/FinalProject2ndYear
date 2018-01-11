package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.Project;
import lapr.project.model.Vehicle.Vehicle;

import java.sql.SQLException;
import java.util.List;

/**
 * Allows for different implementation of methods that fetch and store Projects on the database, for different databases
 */
public interface ProjectDAO extends DataAccessObject {

    /**
     * Fetches instances of Project from the database
     * @return List<Project> a list of instances {@link Project}
     */
    List<Project> fetchProjects() throws SQLException;

    /**
     * Stores instance of {@link Project} in the database
     * @param project instance of {@link Project}
     */
    void storeProjectInformation(Project project) throws SQLException;

    /**
     * Allows to change {@link Project} column "name"
     * Has to be called before calling setName(newName) of {@link Project}
     * @param project {@link Project}
     * @param projectName new Name of {@link Project}
     * @return true if success
     * @throws SQLException
     */
    boolean changeProjectName(Project project, String projectName) throws SQLException;

    /**
     * Allows to change {@link Project} column "description"
     * Has to be called before calling setDescription(newName) of {@link Project}
     * If name also has to change, has to be called before changeProjectName method
     * @param project {@link Project}
     * @param newDescription String identifier of {@link Project}
     * @return true if success
     * @throws SQLException
     */
    boolean changeDescription(Project project, String newDescription) throws SQLException;

    /**
     * Allows {@link lapr.project.model.RoadNetwork.RoadNetwork} growth
     * @param project {@link Project}
     * @return true if success
     * @throws SQLException
     */
    void addRoads(Project project) throws SQLException;

    /**
     * Allows to add more instances of {@link Vehicle}
     * @param project {@link Project}
     * @param addedVehicles {@link List} of instances of {@link Vehicle} to add to the database, associated to the already stored {@link Project}
     * @return true if success
     * @throws SQLException
     */
    void addVehicles(Project project, List<Vehicle> addedVehicles) throws SQLException;
}
