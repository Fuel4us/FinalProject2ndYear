package lapr.project.utils.DataAccessLayer.Oracle;


import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.RoadNetworkDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

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

            try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL fetchAllProjects")) {
                ResultSet resultSet = callableStatement.executeQuery();

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
     * Stores instance of {@link Project} in the database
     * @param project instance of {@link Project}
     */
    @Override
    public boolean storeProject(Project project) throws SQLException {
        if (this.isConnected()) {
            DBAccessor.DB_ACCESS_LOG.log(Level.INFO, "No connection found in " + this.getClass());
            return false;
        }

        try (CallableStatement storeProjectProcedure = super.oracleConnection.prepareCall("CALL STORE_PROJECT(?,?)")) {

            String projectName = project.getName();
            String description = project.getDescription();
            storeProjectProcedure.setString("name", projectName);
            storeProjectProcedure.setString("description", description);

            RoadNetwork roadNetwork = project.getRoadNetwork();
            List<Vehicle> vehicles = project.getVehicles();
            OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO();
            OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO();
            oracleRoadNetworkDAO.storeRoadNetworkInfo(roadNetwork, projectName);
//ToDo same to store vehicles

            storeProjectProcedure.executeUpdate();
        }

        return true;
    }


}
