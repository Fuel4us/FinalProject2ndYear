/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.NetworkAnalysisController;
import lapr.project.model.Analysis;
import lapr.project.model.Vehicle;
import lapr.project.utils.FileParser.ExportHTML;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides user interface components for the display and storage of a network
 * analysis
 */
class StoreNetworkAnalysisUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 606009290497975171L;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField analysisResultTextField;
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonGenerateFile;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables

    private NetworkAnalysisController networkAnalysisController;

    /**
     * Creates new form StoreNetworkAnalysisUI
     */
    StoreNetworkAnalysisUI(Analysis generatedAnalysis, Vehicle vehicle) {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        analysisResultTextField.setEditable(false);
        analysisResultTextField.setText(generatedAnalysis.generateReport());
        networkAnalysisController = new NetworkAnalysisController(Main.dbCom, generatedAnalysis, vehicle);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imgLateral = new javax.swing.JLabel();
        orangeBorder = new javax.swing.JPanel();
        jButtonGenerateFile = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        analysisResultTextField = new javax.swing.JTextField();
        jLabelTitle = new javax.swing.JLabel();
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

        initializer.initializeJButton(jButtonGenerateFile, Main.EIGHTEEN_SEGOE_FONT, "Generate file", Main.DARK_GREY,
                new javax.swing.border.LineBorder(Main.DARK_ORANGE, 4, true));
        jButtonGenerateFile.setBackground(new java.awt.Color(45, 46, 45));
        jButtonGenerateFile.addActionListener(this::jButtonGenerateFileActionPerformed);

        initializer.initializeJButton(jButtonBack, Main.FORTY_EIGHT_SEGOE_FONT, "«", Main.DARK_GREY,
                new javax.swing.border.LineBorder(Main.DARK_ORANGE, 4, true));
        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.addActionListener(this::jButtonBackActionPerformed);

        analysisResultTextField.setBackground(new java.awt.Color(97, 122, 133));
        analysisResultTextField.setForeground(new java.awt.Color(204, 204, 204));
        analysisResultTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        analysisResultTextField.setText("<sample path>");
        analysisResultTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        initializer.initializeLabels(jLabelTitle, Main.TV_POSTER_FONT, "Network analysis result", SwingConstants.CENTER, Main.LIGHT_BLUE);

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
                                .addGap(66, 66, 66)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabelTitle))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jButtonGenerateFile, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(analysisResultTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imgLateral)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTitle)
                                .addGap(18, 18, 18)
                                .addComponent(analysisResultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonGenerateFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButtonGenerateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showSaveDialog(jButtonGenerateFile);
        String fileName = JOptionPane.showInputDialog(jButtonGenerateFile, "Choose the name for this file");

        Main.SupportedOutputFileTypes selectedOutputFormat = Main.displayOutputExtensionChoiceUI(this);
        networkAnalysisController.setOutputFormat(selectedOutputFormat);
        fileName = appendFileFormat(fileName, selectedOutputFormat);

        String dir = fileChooser.getSelectedFile().getAbsolutePath();
        File file = new File(dir + System.getProperty("file.separator") + fileName);

        try {
            networkAnalysisController.exportData(file);
        } catch (IOException ex) {
            Logger.getLogger(StoreNetworkAnalysisUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String appendFileFormat(String fileName, Main.SupportedOutputFileTypes selectedOutputFormat) {
        switch (selectedOutputFormat) {
            case HTML:
                String[] splitFileName = fileName.split("\\.");
                if (!splitFileName[splitFileName.length - 1].equals(ExportHTML.HTML_FILE_EXTENSION)) {
                    fileName += ExportHTML.HTML_FILE_EXTENSION;
                }
                break;
        }
        return fileName;
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        BestPathUI.display();
        dispose();
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        if (networkAnalysisController.storeGeneratedNetworkAnalysis()) {
            JOptionPane.showMessageDialog(null, "Analysis saved successfully");
        } else {
            JOptionPane.showMessageDialog(null, "There was an error storing the analysis, please check your internet connection and try again later.");
        }
    }

}
