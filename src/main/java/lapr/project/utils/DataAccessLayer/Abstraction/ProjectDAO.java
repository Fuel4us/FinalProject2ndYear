package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Allows for different implementation of methods that fetch and store Projects on the database, for different databases
 */
public interface ProjectDAO {

    /**
     * Fetches instances of Project from the database
     * @return List<Project> a list of instances {@link Project}
     */
    List<Project> fetchProjects() throws SQLException;

    /**
     * Stores a project into the data layer
     * @param project The project to be stored
     */
    void storeProject(Project project);

}
