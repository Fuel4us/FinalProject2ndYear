package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Analysis;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles Data Access via OracleDB
 */
public class OracleAnalysisDAO implements AnalysisDAO {

    private PreparedStatement saveStatement;

    public OracleAnalysisDAO(OracleDataSource oracleDataSource) {
        try {
            Connection oracleConnection = oracleDataSource.getConnection();
            saveStatement = oracleConnection.prepareStatement(
                    "INSERT INTO ANALYSIS(ID, PROJECTNAME) VALUES (?, ?)"
            );
            oracleConnection.commit();
            oracleConnection.close();
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
        saveStatement.setInt(1, analysisID);
        saveStatement.setString(2, analysis.issueRequestingEntity().getName());

        saveStatement.executeUpdate();
    }


}
