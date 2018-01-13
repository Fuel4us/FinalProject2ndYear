package lapr.project.utils.DataAccessLayer.Oracle;

import jdk.nashorn.internal.codegen.CompilerConstants;
import lapr.project.model.Analysis;
import lapr.project.model.Section;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.Measurable;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        verifyConnection();

        String projectName = storePrimitives(analysis);
        storeAnalysedSections(analysis, projectName);

        return true;

    }

    /**
     * Stores information that has direct mapping to the database
     * @param analysis the {@link Analysis} to store
     */
    private String storePrimitives(Analysis analysis) throws SQLException {

        try (CallableStatement storeAnalysisProcedure = super.oracleConnection
                .prepareCall("CALL STORE_ANALYSIS(?,?,?,?,?,?)")) {

            int analysisID = analysis.identify();
            String projectName = analysis.issueRequestingEntity().getId();
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
            return projectName;
        }

    }


    /**
     * Stores sections pertaining to the {@link Analysis}
     * @param analysis the {@link Analysis} to store
     */
    private void storeAnalysedSections(Analysis analysis, String projectName) throws SQLException {

        int analysisID = analysis.identify();

        try (CallableStatement storeSectionCallable = super.oracleConnection
                .prepareCall("CALL STORE_ANALYSED_SECTION(?,?,?)")) {

            for (Section section : analysis.getBestPath()) {

                storeSectionCallable.setInt(1, analysisID);
                storeSectionCallable.setInt(2, section.getID());
                String networkID = storeAnalysedSectionNetworkID(projectName);
                storeSectionCallable.setString(3,networkID);

                storeSectionCallable.executeUpdate();
            }
        }
    }

    /**
     * Defines foreign key networkID required by {@link CallableStatement}
     * @param projectName the {@link lapr.project.model.Project} whose {@link lapr.project.model.RoadNetwork} contains the entity Section analysed
     * @throws SQLException
     */
    private String storeAnalysedSectionNetworkID(String projectName) throws SQLException {

        try (CallableStatement callableStatement = oracleConnection
                .prepareCall("CALL retrieveRoadNetworkFromProject(?,?)")) {

            callableStatement.setString(1, projectName);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            ResultSet roadNetworkSet = (ResultSet) callableStatement.getObject(2);
            while (roadNetworkSet.next()) {
                if(roadNetworkSet.getString("projectName").equals(projectName)) {
                    return roadNetworkSet.getString("ID");
                }
            }
        }
        return null;
    }

}
