package lapr.project.ui;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import oracle.jdbc.pool.OracleDataSource;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Analysis;

/**
 * Triggers UI events
 */
class Main {

    /*
    ToDo Refactor this so as not to be static,
    ToDo temporary solution for testing purposes only
     */
    private static Project currentProject;
    private static DataBaseCommunicator dbCom;

    static final String SEGOE_FONT = "Segoe UI Semibold";
    static final Font FORTY_EIGHT_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 48);
    static final Font EIGHTEEN_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 18);
    static final Font FOURTEEN_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 14);
    static final Font TWELVE_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 12);
    static final Font TV_POSTER_FONT = new java.awt.Font("SF Movie Poster", 0, 48);
    static final Color LIGHTEST_COLOR = new java.awt.Color(204, 204, 204);
    static final Color DARK_GREY = new java.awt.Color(45, 46, 45);
    static final Color DARK_BLUE = new java.awt.Color(97, 122, 133);
    static final Color DARK_ORANGE = new java.awt.Color(250, 152, 60);
    static final Color LIGHT_GRAY = new java.awt.Color(87, 89, 87);
    static final Color LIGHT_BLUE = new java.awt.Color(155, 177, 189);
    static final Color LIGHT_ORANGE = new java.awt.Color(255, 219, 168);

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
        currentProject = new Project(
                "New Project",
                "Add a description",
                new RoadNetwork(true),
                new ArrayList<>()
        );
        try {
            LOGGER.log(Level.INFO, "Attempting connection to database...");
            dbCom = new DataBaseCommunicator(new OracleDataSource());
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
            LOGGER.log(Level.SEVERE, "Connection to the database failed, falling back to memory");
        }
    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        setLook();
        new Main();

        //<editor-fold desc="Demonstration purposes" default=collapsed>
        new WelcomeUI(dbCom).setVisible(true);
        //Testing purposes only
        
       // new BestPathUI(currentProject, dbCom).setVisible(true);


//        new StoreNetworkAnalysisUI(currentProject, dbCom, generatedAnalysis).setVisible(true);
        //</editor-fold>
    }

    private static void setLook() {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | javax.swing.UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(StoreNetworkAnalysisUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

}
