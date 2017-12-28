package lapr.project.utils.DataAccessLayer.Oracle;


import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Retrieves all Project entities from the database and creates a list of instances
 */
public class OracleProjectDAO extends OracleDAO implements ProjectDAO {

    public OracleProjectDAO() {}

    /**
     * Stores instances of Project from database in a List
     *
     * @return List<Project> a list of instances {@link Project}
     * @throws SQLException
     */
    @Override
    public List<Project> fetchProjects() throws SQLException {

        List<Project> projects = new LinkedList<>();

        ResultSet resultSet = null;
        try (CallableStatement callableStatement = oracleConnection.prepareCall("call fetchAllProjects")) {
            resultSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String projectName;
        String projectDescription;
        List<Vehicle> vehicles;
        RoadNetwork roadNetwork;
        OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO();
        OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO();
        Project project;
        while (resultSet.next()) {
            projectName = resultSet.getString("name");
            projectDescription = resultSet.getString("description");
            vehicles = oracleVehicleDAO.retrieveVehicles(projectName);
            roadNetwork = oracleRoadNetworkDAO.retrieveRoadNetwork(projectName);

            project = new Project(projectName, projectDescription, roadNetwork, vehicles);
            projects.add(project);
        }

        return projects;
    }

    /**
     * Stores a project into the data layer
     *
     * @param project The project to be stored
     */
    @Override
    public void storeProject(Project project) {
        //ToDo
    }


}
