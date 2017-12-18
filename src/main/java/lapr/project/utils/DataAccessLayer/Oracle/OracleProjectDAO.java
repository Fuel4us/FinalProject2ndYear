package lapr.project.utils.DataAccessLayer.Oracle;


import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.Abstraction.ProjectDAO;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ToDo
 */
public class OracleProjectDAO implements ProjectDAO {

    private PreparedStatement statement;

    public OracleProjectDAO(OracleDataSource oracleDataSource) {
        try {
            Connection oracleConnection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
        }
    }

    /**
     * Stores instances of Project from database in a List
     * @return List<Project> a list of instances {@link Project}
     * @throws SQLException
     */
    @Override
    public List<Project> fetchProjects() throws SQLException {

        ArrayList<Project> projects = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM PROJECT;"
            );
            String projectName;
            String projectDescription;
            Project project;
            while(resultSet.next()) {
                projectName = resultSet.getString("name");
                projectDescription = resultSet.getString("description");
                ResultSet vehicleSet = statement.executeQuery(
                        "SELECT * FROM VEHICLE, PROJECT WHERE VEHICLE.PROJECTNAME = PROJECT.NAME AND PROJECT.NAME = projectName;"
                );
                ArrayList<Vehicle> vehicles = new ArrayList<>();
                while(vehicleSet.next()) {
                    //chamar metodo da entidade veiculo, que recebe um result set, para criar um veiculo
                    //vehicles.add(vehicle);
                }

                ResultSet networkSet = statement.executeQuery(
                        "SELECT * FROM ROADNETWORK, PROJECT WHERE ROADNETWORK.PROJECTNAME = PROJECT.NAME AND PROJECT.NAME = projectName;"
                );
                //chamar metodo da entidade roadNetwork, que recebe um result set, para criar uma roadnetwork

                //project = new Project(projectName, projectDescription, roadNetwork, vehicles);
            }

        return projects;
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
