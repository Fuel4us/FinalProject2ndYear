package lapr.project.utils.DataAccessLayer.Abstraction;

import lapr.project.model.Project;

import java.sql.ResultSet;
import java.util.List;

public interface ProjectDAO {

    /**
     * Fetches instances of Project from the database
     * @return List<Project> a list of instances {@link Project}
     */
    List<Project> fetchProjects();

}
