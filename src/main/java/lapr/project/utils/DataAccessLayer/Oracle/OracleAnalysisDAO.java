package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Analysis;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles Data Access via OracleDB
 */
public class OracleAnalysisDAO extends OracleDBAccessor implements AnalysisDAO {

    private PreparedStatement saveStatement;

    public OracleAnalysisDAO() {
        super();
        try {
            saveStatement = oracleConnection.prepareStatement(
                    "INSERT INTO ANALYSIS(ID, PROJECTNAME) VALUES (?, ?)"
            );
        } catch (SQLException e) {
            super.logSQLException(e);
        }
    }

    /**
     * Store an analysis into data layer
     * @param analysis an instance of {@link Analysis}
     */
    @Override
    public void storeAnalysis(Analysis analysis) {

        try {
            int analysisID = analysis.identify();
            saveStatement.setInt(1, analysisID);
            saveStatement.setString(2, analysis.issueRequestingEntity().getName());

            saveStatement.executeUpdate();

        } catch (SQLException e) {
            super.logSQLException(e);
        }


    }

}
