package lapr.project.utils.DataAccessLayer.Oracle;


import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ToDo
 */
public class OracleProjectDAO implements ProjectDAO {

    private PreparedStatement statement;
    private OracleDataSource oracleDataSource;

    public OracleProjectDAO(OracleDataSource oracleDataSource) {
        this.oracleDataSource = oracleDataSource;
        try {
            Connection oracleConnection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
        }
    }

    /**
     * Stores instances of Project from database in a List
     *
     * @return List<Project> a list of instances {@link Project}
     * @throws SQLException
     */
    @Override
    public List<Project> fetchProjects() throws SQLException {

        List<Project> projects = new LinkedList<>();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM PROJECT;"
        );

        String projectName;
        String projectDescription;
        List<Vehicle> vehicles;
        RoadNetwork roadNetwork;
//        OracleVehicleDAO oracleVehicleDAO = new OracleVehicleDAO(oracleDataSource);
        OracleRoadNetworkDAO oracleRoadNetworkDAO = new OracleRoadNetworkDAO(oracleDataSource);
        Project project;
        while (resultSet.next()) {
            projectName = resultSet.getString("name");
            projectDescription = resultSet.getString("description");
//            vehicles = oracleVehicleDAO.createVehicle(projectName);
            roadNetwork = oracleRoadNetworkDAO.createRoadNetwork(projectName);

//            project = new Project(projectName, projectDescription, roadNetwork, vehicles);
//            projects.add(project);
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
