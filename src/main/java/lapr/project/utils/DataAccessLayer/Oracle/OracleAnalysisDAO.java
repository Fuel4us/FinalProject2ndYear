package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Analysis;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles Data Access via OracleDB
 */
public class OracleAnalysisDAO implements AnalysisDAO {

    private PreparedStatement saveStatement;
    private Connection oracleConnection;

    public OracleAnalysisDAO(OracleDataSource oracleDataSource) {
        try {
            this.oracleConnection = oracleDataSource.getConnection();
            saveStatement = oracleConnection.prepareStatement(
                    "INSERT INTO ANALYSIS(ID, PROJECTNAME) VALUES (?, ?)"
            );
            if (!oracleConnection.getAutoCommit()) {
                oracleConnection.commit();
            }
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
        }
    }

    /**
     * Store an analysis into data layer
     * @param analysis an instance of {@link Analysis}
     */
    @Override
    public void storeAnalysis(Analysis analysis) throws SQLException {

        int analysisID = analysis.identify();
        String name = analysis.issueRequestingEntity().getName();

        saveStatement.setInt(1, analysisID);
        saveStatement.setString(2, name);

        saveStatement.executeUpdate();
    }


}
