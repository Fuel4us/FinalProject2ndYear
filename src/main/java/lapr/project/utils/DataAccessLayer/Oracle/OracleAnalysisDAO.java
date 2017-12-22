package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Analysis;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Handles Data Access via OracleDB
 */
public class OracleAnalysisDAO implements AnalysisDAO {

    private Connection oracleConnection;

    public OracleAnalysisDAO() {}

    /**
     * Store an analysis into data layer
     * @param analysis an instance of {@link Analysis}
     */
    @Override
    public boolean storeAnalysis(Analysis analysis) throws SQLException {

        if (oracleConnection == null) {
            DBAccessor.DB_ACCESS_LOG.log(Level.INFO, "No connection found in " + this.getClass());
            return false;
        }

        try (CallableStatement storeAnalysisCallable = oracleConnection.prepareCall(
                "CALL STORE_ANALYSIS(?,?)"
        )) {

            int analysisID = analysis.identify();
            String name = analysis.issueRequestingEntity().getName();

            storeAnalysisCallable.setInt(1, analysisID);
            storeAnalysisCallable.setString(2, name);

            storeAnalysisCallable.executeUpdate();
        }

        return true;
    }


    /**
     * Connects this Data Access Object to a database
     * if the databaseProductName matches the database to which this DAO is an implementation of.
     * @param connection Connects this DAO to a database
     * @return true if obtaining connection was possible, and that connection refers to an OracleDB
     * @throws SQLException
     */
    @Override
    public boolean connectTo(Connection connection) throws SQLException {

        if (OracleDBAccessor.verifyConnectionIsOracle(connection)) {
            this.oracleConnection = connection;
            return true;
        }
        return false;
    }

}
