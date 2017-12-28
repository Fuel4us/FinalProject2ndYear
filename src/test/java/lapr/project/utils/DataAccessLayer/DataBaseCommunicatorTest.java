package lapr.project.utils.DataAccessLayer;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.*;
import lapr.project.utils.DataAccessLayer.Abstraction.AnalysisDAO;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DataBaseCommunicatorTest {

    private DataBaseCommunicator dbCom;
    private Analysis analysisExpected;
    private MockDBAccessor mockDBAccessor;
    private MockConnection mockConnection;
    private MockAnalysisDAO mockAnalysisStorage;


    public DataBaseCommunicatorTest() {
        try {
            dbCom = new DataBaseCommunicator(new OracleDataSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {


        //Mock elements
        mockDBAccessor = new MockDBAccessor(true);
        //PACKAGE PRIVATE SETTER FOR TESTING PURPOSES ONLY -> SEE JAVADOC
        dbCom.setDbAccessor(mockDBAccessor);


        //Test elements for analysis
        RoadNetwork roadNetworkTest = new RoadNetwork(false, "1", "the road network");

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");
        Node nodeTest3 = new Node("n03");
        Node nodeTest4 = new Node("n04");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);
        roadNetworkTest.addNode(nodeTest3);
        roadNetworkTest.addNode(nodeTest4);

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Road roadTest1 = new Road("A01", "A01", "gantry toll highway");
        Road roadTest2 = new Road("A02", "A02", "toll highway", tollFaresRoadTest);
        Road roadTest3 = new Road("E01", "E01", "regular road");

        Segment segmentTest1 = new Segment(0, 250, 50, 2.5, 10, 2.5, 120, 50);
        Segment segmentTest3 = new Segment(0, 50, 250, 25, -30, 1.75, 90, 50);
        Segment segmentTest5 = new Segment(0, 250, 50, 50, 10, 2.5, 90, 0);

        List<Segment> segmentsTest1 = new ArrayList<>();
        segmentsTest1.add(segmentTest1);

        List<Segment> segmentsTest2 = new ArrayList<>();
        segmentsTest2.add(segmentTest3);

        List<Segment> segmentsTest3 = new ArrayList<>();
        segmentsTest3.add(segmentTest5);

        List<Double> tollFaresSectionTest = new ArrayList<>();
        tollFaresSectionTest.add(0.25);
        tollFaresSectionTest.add(0.35);
        tollFaresSectionTest.add(0.40);

        Section sectionTest1 = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest1, roadTest1, tollFaresSectionTest);
        Section sectionTest2 = new Section(nodeTest2, nodeTest3, Direction.BIDIRECTIONAL, segmentsTest2, roadTest2, new ArrayList<>());
        Section sectionTest3 = new Section(nodeTest3, nodeTest4, Direction.BIDIRECTIONAL, segmentsTest3, roadTest3, new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest1);
        roadNetworkTest.addSection(nodeTest2, nodeTest3, sectionTest2);
        roadNetworkTest.addSection(nodeTest3, nodeTest4, sectionTest3);

        Project projectTest = new Project("project 1", "the project", roadNetworkTest, new ArrayList<>());

        List<Section> sectionsExpected = new ArrayList<>();
        sectionsExpected.add(sectionTest1);
        sectionsExpected.add(sectionTest2);
        sectionsExpected.add(sectionTest3);


        analysisExpected = new Analysis(projectTest, "N10 - Fastest Path",
                sectionsExpected, new Measurable(1384560, Unit.KILOJOULE), new Measurable(0.925, Unit.HOUR), new Measurable(4, Unit.EUROS));

    }

    /**
     * Success use case
     * Method : storeNetworkAnalysis
     */
    @Test
    public void ensureAnalysisIsStoredAssumingDataSourceIsAvailable() throws Exception {

        //PACKAGE PRIVATE SETTER FOR TESTING PURPOSES ONLY -> SEE JAVADOC
        MockAnalysisDAO mockAnalysisStorage = new MockAnalysisDAO();
        dbCom.setAnalysisStorage(mockAnalysisStorage);

        //Assert return true line is reached
        if (!dbCom.storeNetworkAnalysis(analysisExpected)) throw new AssertionError();

        Analysis actual = mockAnalysisStorage.retrieveStoredAnalysis(analysisExpected.identify());

        assert mockConnection.isCommitted();

        assertEquals(analysisExpected.getBestPath(), actual.getBestPath());
        assertEquals(analysisExpected.getExpendedEnergy().getQuantity(), actual.getExpendedEnergy().getQuantity(), 0);
        assertEquals(analysisExpected.getTravelTime().getQuantity(), actual.getTravelTime().getQuantity(), 0);
        assertEquals(analysisExpected.getTravelCost().getQuantity(), actual.getTravelCost().getQuantity(), 0);

    }

    /**
     * Failing use case
     * Method : storeNetworkAnalysis
     */
    @Test
    public void ensureAnalysisStorageRollsBackForInactiveConnection() throws Exception {

        MockAnalysisDAO mockAnalysisStorage = new MockAnalysisDAO();
        dbCom.setAnalysisStorage(mockAnalysisStorage);
        dbCom.storeNetworkAnalysis(analysisExpected);

        //Set connection simulation to false so that an exception may be thrown when opening connections
        mockDBAccessor.simulateConnection(false);

        //Assert simulating inactive connection throws SQLException
        Assertions.assertThrows(SQLException.class, () -> mockDBAccessor.openConnexion());

        //Assert thrown SQLException is caught and return line is reached
        assert !dbCom.storeNetworkAnalysis(analysisExpected);

        //Assert connection is rolled back for active connection, when transaction fails
        // Simulate active connection
        mockDBAccessor.simulateConnection(true);
        // Simulate transaction failure with active connection
        mockDBAccessor.openConnexion();
        mockConnection.setSimulateTransactionFailure(true);
        //Test SQLException is caught by rolling back (logging should be available on console output)
        dbCom.storeNetworkAnalysis(analysisExpected);
        assert mockConnection.isRolledBack();
    }

    /*
    Mock classes
     */

    /**
     * <p>
     * Simulates an AnalysisDAO without resorting
     * to an actual database implementation
     * </p>
     */
    private class MockAnalysisDAO implements AnalysisDAO {

        private List<Analysis> analysisList;

        MockAnalysisDAO() {
            this.analysisList = new ArrayList<>();
        }

        /**
         * <p>
         * Store an analysisList into data layer
         * </p>
         * <br>
         * <p>
         * May require a connection to be set through
         * DataAccessObject {@code connectTo} method
         * </p>
         * @param analysis an instance of {@link Analysis}
         */
        @Override
        public boolean storeAnalysis(Analysis analysis) throws SQLException {
            return this.analysisList.add(analysis);
        }

        Analysis retrieveStoredAnalysis(int analysisID) {
            for (Analysis analysis : analysisList) {
                if (analysis.identify() == analysisID) {
                    return analysis;
                }
            }
            return null;
        }

        @Override
        public boolean connectTo(Connection connection) throws SQLException {
            return true;
        }

    }

    /**
     * <p>
     * Simulates a DBAccessor without resorting
     * to an actual database implementation
     * </p>
     */
    private class MockDBAccessor implements DBAccessor {

        private boolean simulateConnection;

        MockDBAccessor(boolean simulateConnection) {
            this.simulateConnection = simulateConnection;
        }

        void simulateConnection(boolean simulateConnection) {
            this.simulateConnection = simulateConnection;
        }

        /**
         * Connects to a database
         * @throws SQLException
         */
        @Override
        public Connection openConnexion() throws SQLException {
            if (simulateConnection) {
                if (mockConnection == null) {
                    mockConnection = new MockConnection();
                }
                return mockConnection;
            }
            throw new SQLException();
        }

        /**
         * Verifies if the state of the connection is active (i.e not null)
         * @return true if connection is active
         */
        @Override
        public boolean hasActiveConnection() {
            return simulateConnection;
        }

    }

    /**
     * <p>
     * Mocks a connection without resorting
     * to an actual database implementation
     * </p>
     */
    private class MockConnection implements Connection {
        private boolean committed;

        private boolean rolledBack;

        private boolean simulateTransactionFailure;

        MockConnection() {
            committed = false;
            rolledBack = false;
            simulateTransactionFailure = false;
        }

        /**
         * Checks that a connection was used to commit a transaction
         */
        boolean isCommitted() {
            return committed;
        }

        /**
         * Checks that a connection was used to roll back a transaction
         */
        boolean isRolledBack() {
            return rolledBack;
        }


        /**
         * Actives transaction failure mode, implying commit attempts will fail
         */
        void setSimulateTransactionFailure(boolean simulateTransactionFailure) {
            this.simulateTransactionFailure = simulateTransactionFailure;
        }

        /**
         * Simulates a commit.
         * @throws SQLException if simulateTransactionFailure is set to true
         */
        @Override
        public void commit() throws SQLException {
            if (!simulateTransactionFailure) {
                committed = true;
            }
            else throw new SQLException();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String s) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String s) throws SQLException {
            return null;
        }

        @Override
        public String nativeSQL(String s) throws SQLException {
            return null;
        }

        @Override
        public void setAutoCommit(boolean b) throws SQLException {

        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return false;
        }

        @Override
        public void rollback() throws SQLException {
            rolledBack = true;
        }

        @Override
        public void close() throws SQLException {

        }

        @Override
        public boolean isClosed() throws SQLException {
            return false;
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return null;
        }

        @Override
        public void setReadOnly(boolean b) throws SQLException {

        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return false;
        }

        @Override
        public void setCatalog(String s) throws SQLException {

        }

        @Override
        public String getCatalog() throws SQLException {
            return null;
        }

        @Override
        public void setTransactionIsolation(int i) throws SQLException {

        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return 0;
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return null;
        }

        @Override
        public void clearWarnings() throws SQLException {

        }

        @Override
        public Statement createStatement(int i, int i1) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
            return null;
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return null;
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

        }

        @Override
        public void setHoldability(int i) throws SQLException {

        }

        @Override
        public int getHoldability() throws SQLException {
            return 0;
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return null;
        }

        @Override
        public Savepoint setSavepoint(String s) throws SQLException {
            return null;
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {

        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {

        }

        @Override
        public Statement createStatement(int i, int i1, int i2) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
            return null;
        }

        @Override
        public Clob createClob() throws SQLException {
            return null;
        }

        @Override
        public Blob createBlob() throws SQLException {
            return null;
        }

        @Override
        public NClob createNClob() throws SQLException {
            return null;
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        @Override
        public boolean isValid(int i) throws SQLException {
            return false;
        }

        @Override
        public void setClientInfo(String s, String s1) throws SQLClientInfoException {

        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        @Override
        public String getClientInfo(String s) throws SQLException {
            return null;
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return null;
        }

        @Override
        public Array createArrayOf(String s, Object[] objects) throws SQLException {
            return null;
        }

        @Override
        public Struct createStruct(String s, Object[] objects) throws SQLException {
            return null;
        }

        @Override
        public void setSchema(String s) throws SQLException {

        }

        @Override
        public String getSchema() throws SQLException {
            return null;
        }

        @Override
        public void abort(Executor executor) throws SQLException {

        }

        @Override
        public void setNetworkTimeout(Executor executor, int i) throws SQLException {

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return 0;
        }

        @Override
        public <T> T unwrap(Class<T> aClass) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> aClass) throws SQLException {
            return false;
        }
    }

}