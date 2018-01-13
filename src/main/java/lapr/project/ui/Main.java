package lapr.project.ui;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork;
import lapr.project.utils.DataAccessLayer.Abstraction.DBAccessor;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Triggers UI events
 */
public class Main {

    static Project currentProject;
    static DataBaseCommunicator dbCom;

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
    static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {}

    /**
     * Application main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        initStatic();
        WelcomeUI.display();
    }

    /**
     * Initializes objects which are unique during the lifecycle of the program.
     */
    private static void initStatic() {
        currentProject = new Project(
                "New Project","New Project",
                "Add a description",
                new RoadNetwork(),
                new ArrayList<>()
        );
        try {
            LOGGER.log(Level.INFO, "Initializing database connection assets...");
            dbCom = new DataBaseCommunicator(new OracleDataSource());
        } catch (SQLException e) {
            DBAccessor.logSQLException(e);
            LOGGER.log(Level.SEVERE, "Initialization failed, some operations may not be available.");
        }
    }

    /**
     * Defines UI look and feel
     * Access is package private so that it may be invoked by other UIs
     */
    static void setLook() {

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

    /**
     * Defines the current active project
     */
    public static void setCurrentProject(Project project) {
        Main.currentProject = project;
    }

    /**
     * Initializes a {@link JFileChooser} to open files with a filter defined according to the {@code selectedExtension}
     * @param selectedExtension an instance of {@link SupportedInputFileTypes}
     * @return the prepared {@link JFileChooser}
     */
    static JFileChooser initFileChooserProperties(SupportedInputFileTypes selectedExtension, String dialogTitle) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle(dialogTitle);
        setFileChooserFilter(fileChooser, selectedExtension);
        return fileChooser;
    }

    /**
     * Defines a filter based off of the {@code selectedExtension}
     * @param fileChooser The {@link JFileChooser} to which this property is to be set
     * @param selectedExtension the selected {@link SupportedInputFileTypes}
     */
    private static void setFileChooserFilter(JFileChooser fileChooser, SupportedInputFileTypes selectedExtension) {
        switch (selectedExtension) {
            case XML:
                FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
                fileChooser.setFileFilter(xmlFilter);
                break;
        }

    }

    /**
     * Displays a UI that prompts for the choice of the parsing mode to use to import information
     * @return the {@code selectedType} - instance of {@link SupportedInputFileTypes}
     */
    static SupportedInputFileTypes displayExtensionChoiceUI(JFrame parentComponent) {
        SupportedInputFileTypes selectedType = null;
        boolean validExtension;
        do {
            String selection = JOptionPane.showInputDialog("Choose the file format you want to parse.\nCurrently supported formats are "
                    + Arrays.toString(SupportedInputFileTypes.values()));
            try {
                selectedType = SupportedInputFileTypes.valueOf(selection);
                validExtension = true;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(parentComponent, "Please insert a valid value.");
                validExtension = false;
            }
        } while (!validExtension);
        return selectedType;
    }

    /**
     * Displays a UI that prompts for the choice of the parsing mode to use to import information
     * @return the {@code selectedType} - instance of {@link Main.SupportedOutputFileTypes}
     */
    static Main.SupportedOutputFileTypes displayOutputExtensionChoiceUI(JFrame parentComponent) {
        Main.SupportedOutputFileTypes selectedType = null;
        boolean validExtension;
        do {
            String selection = JOptionPane.showInputDialog("Choose the desired output file format.\nCurrently supported formats are "
                    + Arrays.toString(Main.SupportedOutputFileTypes.values()));
            try {
                selectedType = Main.SupportedOutputFileTypes.valueOf(selection);
                validExtension = true;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(parentComponent, "Please insert a valid value.");
                validExtension = false;
            }
        } while (!validExtension);
        return selectedType;
    }


    /**
     * Enumerates supported file during input types
     */
    public enum SupportedInputFileTypes {
        XML
    }

    /**
     * Enumerates supported file types
     */
    public enum SupportedOutputFileTypes {
        HTML
    }


}
