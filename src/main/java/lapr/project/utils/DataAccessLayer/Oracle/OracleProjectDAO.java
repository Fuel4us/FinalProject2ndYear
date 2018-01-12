package lapr.project.utils.DataAccessLayer.Oracle;


import lapr.project.model.Project;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;
import oracle.jdbc.OracleTypes;

import java.sql.*;
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
     * @throws java.sql.SQLException
     */
    @Override
    public List<Project> fetchProjects() throws SQLException {

        if (this.isConnected()) {

            List<Project> projects = new LinkedList<>();

            try (CallableStatement callableStatement = oracleConnection.prepareCall("CALL fetchAllProjects(?)")) {

                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.executeQuery();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                Project project;
                String projectName;
                String projectDescription;

                List<Vehicle> vehicles;
                RoadNetwork roadNetwork;

                OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO();
                oracleVehicleDAO.connectTo(this.oracleConnection);

                OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO();
                oracleRoadNetworkDAO.connectTo(this.oracleConnection);

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
     * Stores information of {@link Project} in the database
     * @param project instance of {@link Project}
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public void storeProjectInformation(Project project) throws SQLException {
        verifyConnection();

        String projectName = storeProject(project);

        RoadNetwork roadNetwork = project.getRoadNetwork();
        OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO();
        oracleRoadNetworkDAO.connectTo(this.oracleConnection);
        oracleRoadNetworkDAO.storeRoadNetwork(roadNetwork, projectName);

        List<Vehicle> vehicles = project.getVehicles();
        OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO();
        oracleVehicleDAO.connectTo(this.oracleConnection);
        for (Vehicle vehicle : vehicles) {
            oracleVehicleDAO.storeVehicleInfo(vehicle, projectName);
        }
    }

    /**
     * Stores columns of entity correspondent to {@link Project} in the database
     * @param project instance of {@link Project}
     * @return
     * @throws java.sql.SQLException
     */
    private String storeProject(Project project) throws SQLException {

        try (CallableStatement storeProjectProcedure = super.oracleConnection.prepareCall("CALL storeProjectProcedure(?,?)")) {

            String projectName = project.getName();
            String description = project.getDescription();
            storeProjectProcedure.setString("projectName", projectName);
            storeProjectProcedure.setString("description", description);

            storeProjectProcedure.executeUpdate();
            return projectName;
        }
    }

    /**
     * Allows to change {@link Project} column "name"
     * Has to be called before calling setName(newName) of {@link Project}
     * @param project {@link Project}
     * @param projectNewName String identifier of {@link Project}, new name
     * @return true if success
     * @throws SQLException
     */
    @Override
    public boolean changeProjectName(Project project, String projectNewName) throws SQLException {
        verifyConnection();

        try (CallableStatement changeProjectNameProcedure = super.oracleConnection.prepareCall("CALL CHANGEPROJECTNAMEPROCEDURE(?,?)")) {

            String projectOldName = project.getName();
            changeProjectNameProcedure.setString("oldName", projectOldName);
            changeProjectNameProcedure.setString("newName", projectNewName);

            changeProjectNameProcedure.executeUpdate();
            return true;
        }

    }

    /**
     * Allows to change {@link Project} column "description"
     * Has to be called before calling setDescription(newName) of {@link Project}
     * If name also has to change, has to be called before changeProjectName method
     * @param project {@link Project}
     * @param newDescription String identifier of {@link Project}
     * @return true if success
     * @throws SQLException
     */
    @Override
    public boolean changeDescription(Project project, String newDescription) throws SQLException {
        verifyConnection();

        try (CallableStatement changeDescriptionProcedure = super.oracleConnection.prepareCall("CALL changeDescriptionProcedure(?,?)")) {

            String originalName = project.getName();
            changeDescriptionProcedure.setString("originalName", originalName);
            changeDescriptionProcedure.setString("newDescription", newDescription);

            changeDescriptionProcedure.executeUpdate();
            return true;
        }

    }

    /**
     * Allows {@link RoadNetwork} growth
     * @param project {@link Project}
     * @return true if success
     * @throws SQLException
     */
    @Override
    public void addRoads(Project project) throws SQLException {
        verifyConnection();

            RoadNetwork roadNetwork = project.getRoadNetwork();
            OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO();
            oracleRoadNetworkDAO.connectTo(this.oracleConnection);
            oracleRoadNetworkDAO.storeRoadNetworkGraph(roadNetwork, roadNetwork.getId(), project.getName());
    }

    /**
     * Allows to add more instances of {@link Vehicle}
     * @param project {@link Project}
     * @param addedVehicles {@link List} of instances of {@link Vehicle} to add to the database, associated to the already stored {@link Project}
     * @return true if success
     * @throws SQLException
     */
    @Override
    public void addVehicles(Project project, List<Vehicle> addedVehicles) throws SQLException {
        verifyConnection();

            String projectName = project.getName();

            OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO();
            oracleVehicleDAO.connectTo(this.oracleConnection);
            for (Vehicle vehicle : addedVehicles) {
                oracleVehicleDAO.storeVehicleInfo(vehicle, projectName);
            }
    }


}
