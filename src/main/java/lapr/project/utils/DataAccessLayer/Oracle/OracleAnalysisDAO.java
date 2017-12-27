package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Analysis;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Handles Data Access via OracleDB
 */
public class OracleAnalysisDAO extends OracleDAO implements AnalysisDAO {

    public OracleAnalysisDAO() {
    }

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

//        storePrimitives(analysis);
        storeAnalysedSections(analysis);

        return true;

    }


    /**
     * Stores sections pertaining to the {@link Analysis}
     * @param analysis the {@link Analysis} to store
     */
    private void storeAnalysedSections(Analysis analysis) throws SQLException {

        int analysisID = analysis.identify();

        try (CallableStatement storeSectionCallable = super.oracleConnection
                .prepareCall("CALL STORE_ANALYSED_SECTION(?,?)")) {

            for (Section section : analysis.getBestPath()) {

                storeSectionCallable.setInt(1, analysisID);
                storeSectionCallable.setInt(1, section.getID());

                storeSectionCallable.executeUpdate();

            }

        }

    }

}
