package lapr.project.utils.DataAccessLayer.Oracle;


import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles Data Access via OracleDB
 */
public class OracleProjectDAO extends OracleDAO implements ProjectDAO {

    /**
     * Retrieves all Project entities from the database and creates a list of instances
     * @return a {@link List} of instances of {@link Project}
     */
    @Override
    public List<Project> fetchProjects() throws SQLException {

        if (this.isConnected()) {

            List<Project> projects = new LinkedList<>();

            try (PreparedStatement preparedStatement = super.oracleConnection.prepareStatement("SELECT * FROM PROJECT")) {

                ResultSet resultSet = preparedStatement.executeQuery();

                Project project;
                String projectName;
                String projectDescription;

                List<Vehicle> vehicles;
                RoadNetwork roadNetwork;

                OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO();
                OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO();

                while (resultSet.next()) {
                    projectName = resultSet.getString("name");
                    projectDescription = resultSet.getString("description");
                    vehicles = oracleVehicleDAO.retrieveVehicles(projectName);
                    roadNetwork = oracleRoadNetworkDAO.retrieveRoadNetwork(projectName);

                    project = new Project(projectName, projectDescription, roadNetwork, vehicles);
                    projects.add(project);
                }

            }

            return projects;
        }

        return new ArrayList<>();
    }

    /**
     * Stores a project into the data layer
     * @param project The project to be stored
     */
    @Override
    public void storeProject(Project project) {
        //ToDo
    }


}
