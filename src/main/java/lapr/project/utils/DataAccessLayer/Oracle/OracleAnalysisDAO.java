package lapr.project.utils.DataAccessLayer.Oracle;

import lapr.project.model.Analysis;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;

/**
 * Handles Data Access via OracleDB
 */
public class OracleAnalysisDAO extends OracleDAO implements AnalysisDAO {

    /**
     * Store an analysis into data layer
     * @param analysis an instance of {@link Analysis}
     */
    @Override
    public boolean storeAnalysis(Analysis analysis) throws SQLException {

        if (this.isConnected()) {
            DBAccessor.DB_ACCESS_LOG.log(Level.INFO, "No connection found in " + this.getClass());
            return false;
        }

        storePrimitives(analysis);
        storeAnalysedSections(analysis);

        return true;

    }


    /**
     * Stores information that requires units
     * @param measurable the {@link Measurable} to store
     */
    private int storeStatisticalInfo(Measurable measurable) throws SQLException {

        try (CallableStatement storeMeasurableFunction = super.oracleConnection
                .prepareCall("{? = call STORE_MEASURABLE(?,?)}")) {

            double quantity = measurable.getQuantity();
            Unit unit = measurable.getUnit();

            storeMeasurableFunction.registerOutParameter(1, Types.INTEGER);

            storeMeasurableFunction.setDouble(2, quantity);
            storeMeasurableFunction.setString(3, unit.toString());

            storeMeasurableFunction.executeUpdate();

            return storeMeasurableFunction.getInt(1);
        }

    }

    /**
     * Stores information that has direct mapping to the database
     * @param analysis the {@link Analysis} to store
     */
    private void storePrimitives(Analysis analysis) throws SQLException {

        try (CallableStatement storeAnalysisProcedure = super.oracleConnection
                .prepareCall("CALL STORE_ANALYSIS(?,?,?,?,?,?)")) {

            int analysisID = analysis.identify();
            String projectName = analysis.issueRequestingEntity().getName();
            String algorithmName = analysis.getAlgorithmName();
            Measurable expendedEnergy = analysis.getExpendedEnergy();
            Measurable travelTime = analysis.getTravelTime();
            Measurable travelCost = analysis.getTravelCost();

            storeAnalysisProcedure.setInt(1, analysisID);
            storeAnalysisProcedure.setString(2, projectName);
            storeAnalysisProcedure.setString(3, algorithmName);
            //Store quantity and unit information in Measurable table and link it through the returned ID
            //Measurable Primary Key becomes Analysis Foreign Key
            storeAnalysisProcedure.setInt(4, storeStatisticalInfo(expendedEnergy));
            storeAnalysisProcedure.setInt(5, storeStatisticalInfo(travelTime));
            storeAnalysisProcedure.setInt(6, storeStatisticalInfo(travelCost));

            storeAnalysisProcedure.executeUpdate();
        }

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
                storeSectionCallable.setInt(2, section.getID());

                storeSectionCallable.executeUpdate();

            }

        }

    }

}
