/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.BestPathComparisonAllAnalysisController;
import lapr.project.model.Analysis;
import lapr.project.model.Vehicle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

/**
 * @author anily
 */
public class BestPathComparisonAllAnalysisUI extends javax.swing.JFrame {

    private static final long serialVersionUID = -4597792551848402104L;
    private BestPathComparisonAllAnalysisController comparisonController;
    private static List<Analysis> analysisList;
    private static List<Vehicle> vehiclesList;

    /**
     * Creates new form BestPathComparisonForm
     * @param analysisList
     */
    public BestPathComparisonAllAnalysisUI(List<Analysis> analysisList, List<Vehicle> vehiclesList) {
        this.comparisonController = new BestPathComparisonAllAnalysisController();
        BestPathComparisonAllAnalysisUI.analysisList = analysisList;
        BestPathComparisonAllAnalysisUI.vehiclesList = vehiclesList;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imgLateral = new javax.swing.JLabel();
        orangeBorder = new javax.swing.JPanel();
        jButtonViewResults = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jComboBoxAnalysis = new javax.swing.JComboBox<Analysis>();
        jLabel2 = new javax.swing.JLabel();
        jButtonGenerateFile = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        InitializeUIElements initializer = new InitializeUIElements();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(45, 46, 45));

        imgLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fundo.png"))); // NOI18N

        orangeBorder.setBackground(new java.awt.Color(250, 152, 60));

        javax.swing.GroupLayout orangeBorderLayout = new javax.swing.GroupLayout(orangeBorder);
        orangeBorder.setLayout(orangeBorderLayout);
        orangeBorderLayout.setHorizontalGroup(
                orangeBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 10, Short.MAX_VALUE)
        );
        orangeBorderLayout.setVerticalGroup(
                orangeBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        initializer.initializeJButton(jButtonViewResults, Main.EIGHTEEN_SEGOE_FONT, "View detailed results for this analysis", Main.DARK_GREY,
                new javax.swing.border.LineBorder(Main.DARK_ORANGE, 4, true));
        jButtonViewResults.setBackground(new java.awt.Color(45, 46, 45));
        jButtonViewResults.addActionListener(this::jButtonViewResultsActionPerformed);

        initializer.initializeJButton(jButtonBack, Main.FORTY_EIGHT_SEGOE_FONT, "«", Main.DARK_GREY,
                new javax.swing.border.LineBorder(Main.DARK_ORANGE, 4, true));
        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.addActionListener(this::jButtonBackActionPerformed);

        List<Analysis> analysisListModel = analysisList;
        DefaultComboBoxModel<Analysis> analysisModel = new DefaultComboBoxModel<>();
        for (Analysis obj : analysisListModel) {
            analysisModel.addElement(obj);
        }
        jComboBoxAnalysis.setModel(analysisModel);
        jComboBoxAnalysis.setBackground(new java.awt.Color(204, 204, 204));

        initializer.initializeLabels(jLabel2, Main.TV_POSTER_FONT, "Road Network Comparison", SwingConstants.CENTER, Main.LIGHT_BLUE);

        initializer.initializeJButton(jButtonGenerateFile, Main.EIGHTEEN_SEGOE_FONT, "Generate file", Main.DARK_GREY,
                new javax.swing.border.LineBorder(Main.DARK_ORANGE, 4, true));
        jButtonGenerateFile.setBackground(new java.awt.Color(45, 46, 45));
        jButtonGenerateFile.addActionListener(evt -> {
            try {
                jButtonGenerateFileActionPerformed(evt);
            } catch (IOException e) {
                Main.LOGGER.log(Level.WARNING, e.getMessage());
            }
        });

        initializer.initializeJButton(jButtonSave, Main.EIGHTEEN_SEGOE_FONT, "Save results", Main.DARK_GREY,
                new javax.swing.border.LineBorder(Main.DARK_ORANGE, 4, true));
        jButtonSave.setBackground(new java.awt.Color(45, 46, 45));
        jButtonSave.addActionListener(this::jButtonSaveActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orangeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonViewResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxAnalysis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButtonGenerateFile, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imgLateral)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addComponent(jComboBoxAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonViewResults, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonGenerateFile, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonViewResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewResultsActionPerformed
        BestPathComparisonResultsUI bestPathComparisonResultsUI =
                new BestPathComparisonResultsUI((Analysis) jComboBoxAnalysis.getSelectedItem());
    }//GEN-LAST:event_jButtonViewResultsActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        BestPathComparisonForm.display();
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonGenerateFileActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_jButtonGenerateFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Insert the name the file to which you wish to export data");

        Main.SupportedOutputFileTypes selectedOutputFormat = Main.displayOutputExtensionChoiceUI(this);

        selectFilter(fileChooser, selectedOutputFormat);

        int returnVal = fileChooser.showOpenDialog(jButtonGenerateFile);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();
            for (int i = 0; i < analysisList.size(); i++) {
                for (int j = 0; j < vehiclesList.size(); j++) {
                    if (i == j) {
                        comparisonController.export(selectedOutputFormat, outputFile, analysisList.get(i), vehiclesList.get(j));
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Your data was exported.");
        }
    }//GEN-LAST:event_jButtonGenerateFileActionPerformed

    /**
     * Chooses the appropriate filter according to the {@code selectedOutputFormat}
     */
    private void selectFilter(JFileChooser fileChooser, Main.SupportedOutputFileTypes selectedOutputFormat) {
        switch (selectedOutputFormat) {
            case HTML:
                FileNameExtensionFilter htmlFilter = new FileNameExtensionFilter(
                        "html files (*.html)", "html");
                fileChooser.setFileFilter(htmlFilter);
                break;
        }

    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        for(Analysis analysis : analysisList) {
            if (comparisonController.storeGeneratedNetworkAnalysis(analysis)) {
                JOptionPane.showMessageDialog(null, "Analysis saved successfully");
            } else {
                JOptionPane.showMessageDialog(null, "There was an error storing the analysis, please check your internet connection and try again later.");
            }
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * Triggers UI display
     */
    public static void display() {
        Main.setLook();
        java.awt.EventQueue.invokeLater(() -> new BestPathComparisonAllAnalysisUI(analysisList, vehiclesList).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonGenerateFile;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonViewResults;
    /**
     * private javax.swing.JComboBox<String> jComboBoxAnalysis;
     */
    private javax.swing.JComboBox<Analysis> jComboBoxAnalysis;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables
}
