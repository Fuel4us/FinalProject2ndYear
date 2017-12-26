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
public class OracleAnalysisDAO extends OracleDAO implements AnalysisDAO {


    public OracleAnalysisDAO() {}

    /**
     * Store an analysis into data layer
     * @param analysis an instance of {@link Analysis}
     */
    @Override
    public boolean storeAnalysis(Analysis analysis) throws SQLException {

        if (super.oracleConnection == null) {
            DBAccessor.DB_ACCESS_LOG.log(Level.INFO, "No connection found in " + this.getClass());
            return false;
        }

        try (CallableStatement storeAnalysisCallable = super.oracleConnection.prepareCall(
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

}
