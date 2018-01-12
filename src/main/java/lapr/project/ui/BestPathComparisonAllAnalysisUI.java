/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.BestPathController;
import lapr.project.model.Analysis;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author anily
 */
public class BestPathComparisonAllAnalysisUI extends javax.swing.JFrame {

    private static final long serialVersionUID = -4597792551848402104L;
    private BestPathController controller;
    private static List<Analysis> analysisList;

    /**
     * Creates new form BestPathComparisonForm
     *
     * @param analysisList
     */
    public BestPathComparisonAllAnalysisUI(List<Analysis> analysisList) {
        this.controller = new BestPathController(Main.currentProject);
        BestPathComparisonAllAnalysisUI.analysisList = analysisList;
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

        jButtonViewResults.setBackground(new java.awt.Color(45, 46, 45));
        jButtonViewResults.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonViewResults.setForeground(new java.awt.Color(45, 46, 45));
        jButtonViewResults.setText("View detailed results for this analysis");
        jButtonViewResults.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonViewResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewResultsActionPerformed(evt);
            }
        });

        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        jButtonBack.setForeground(new java.awt.Color(45, 46, 45));
        jButtonBack.setText("«");
        jButtonBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        List<Analysis> analysisListModel = analysisList;
        DefaultComboBoxModel<Analysis> analysisModel = new DefaultComboBoxModel<>();
        for (Analysis obj : analysisListModel) {
            analysisModel.addElement(obj);
        }
        jComboBoxAnalysis.setModel(analysisModel);
        jComboBoxAnalysis.setBackground(new java.awt.Color(204, 204, 204));
        /**
        jComboBoxAnalysis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        */
        jComboBoxAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnalysisActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 177, 189));
        jLabel2.setText("Road Network Comparison");

        jButtonGenerateFile.setBackground(new java.awt.Color(45, 46, 45));
        jButtonGenerateFile.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonGenerateFile.setForeground(new java.awt.Color(45, 46, 45));
        jButtonGenerateFile.setText("Generate file");
        jButtonGenerateFile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonGenerateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateFileActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(45, 46, 45));
        jButtonSave.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonSave.setForeground(new java.awt.Color(45, 46, 45));
        jButtonSave.setText("Save results");
        jButtonSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

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
        BestPathComparisonResultsUI  bestPathComparisonResultsUI = new BestPathComparisonResultsUI((Analysis)jComboBoxAnalysis.getSelectedItem());
    }//GEN-LAST:event_jButtonViewResultsActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        WelcomeUI.display();
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jComboBoxAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnalysisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAnalysisActionPerformed

    private void jButtonGenerateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerateFileActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * Triggers UI display
     */
    public static void display() {
        Main.setLook();
        java.awt.EventQueue.invokeLater(() -> new BestPathComparisonAllAnalysisUI(analysisList).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonGenerateFile;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonViewResults;
    /**
    private javax.swing.JComboBox<String> jComboBoxAnalysis;
    */
    private javax.swing.JComboBox<Analysis> jComboBoxAnalysis;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables
}