/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.model.Analysis;

import java.util.List;

/**
 *
 * @author anily
 */
public class BestPathComparisonResults extends javax.swing.JFrame {

    private static final long serialVersionUID = 2562000671949976456L;

    /**
     * Creates new form NewJFrame
     */
    BestPathComparisonResults(List<Analysis> analysisList) {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
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
        jButtonGenerateFile = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        projectNameTextField = new javax.swing.JTextField();
        jLabelTitle = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        algorithmNameTextField = new javax.swing.JTextField();
        energyTextField = new javax.swing.JTextField();
        costTextField = new javax.swing.JTextField();
        timeTextField = new javax.swing.JTextField();
        sectionsResultTextField = new javax.swing.JTextField();
        algorithmLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        projectNameLabel = new javax.swing.JLabel();
        energyLabel = new javax.swing.JLabel();
        costLabel = new javax.swing.JLabel();
        imgLateral = new javax.swing.JLabel();
        orangeBorder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(45, 46, 45));

        jButtonGenerateFile.setBackground(new java.awt.Color(45, 46, 45));
        jButtonGenerateFile.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonGenerateFile.setForeground(new java.awt.Color(45, 46, 45));
        jButtonGenerateFile.setText("Generate file");
        jButtonGenerateFile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonGenerateFile.addActionListener(evt -> jButtonGenerateFileActionPerformed(evt));

        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        jButtonBack.setForeground(new java.awt.Color(45, 46, 45));
        jButtonBack.setText("«");
        jButtonBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonBack.addActionListener(evt -> jButtonBackActionPerformed(evt));

        projectNameTextField.setBackground(new java.awt.Color(97, 122, 133));
        projectNameTextField.setForeground(new java.awt.Color(204, 204, 204));
        projectNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        projectNameTextField.setText("<sample project name>");
        projectNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        jLabelTitle.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(155, 177, 189));
        jLabelTitle.setText("Best path comparison results");

        jButtonSave.setBackground(new java.awt.Color(45, 46, 45));
        jButtonSave.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonSave.setForeground(new java.awt.Color(45, 46, 45));
        jButtonSave.setText("Save results");
        jButtonSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonSave.addActionListener(evt -> jButtonSaveActionPerformed(evt));

        algorithmNameTextField.setBackground(new java.awt.Color(97, 122, 133));
        algorithmNameTextField.setForeground(new java.awt.Color(204, 204, 204));
        algorithmNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        algorithmNameTextField.setText("<sample algorithm name>");
        algorithmNameTextField.setToolTipText("");
        algorithmNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        energyTextField.setBackground(new java.awt.Color(97, 122, 133));
        energyTextField.setForeground(new java.awt.Color(204, 204, 204));
        energyTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        energyTextField.setText("<sample energy>");
        energyTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        costTextField.setBackground(new java.awt.Color(97, 122, 133));
        costTextField.setForeground(new java.awt.Color(204, 204, 204));
        costTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        costTextField.setText("<sample cost>");
        costTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        timeTextField.setBackground(new java.awt.Color(97, 122, 133));
        timeTextField.setForeground(new java.awt.Color(204, 204, 204));
        timeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timeTextField.setText("<sample time>");
        timeTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        sectionsResultTextField.setBackground(new java.awt.Color(97, 122, 133));
        sectionsResultTextField.setForeground(new java.awt.Color(204, 204, 204));
        sectionsResultTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sectionsResultTextField.setText("<sample path>");
        sectionsResultTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

        algorithmLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        algorithmLabel.setForeground(new java.awt.Color(97, 122, 133));
        algorithmLabel.setText("ANALYSIS ALGORITHM:");

        timeLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(97, 122, 133));
        timeLabel.setText("TRAVEL TIME:");

        projectNameLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        projectNameLabel.setForeground(new java.awt.Color(97, 122, 133));
        projectNameLabel.setText("PROJECT NAME:");

        energyLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        energyLabel.setForeground(new java.awt.Color(97, 122, 133));
        energyLabel.setText("EXPENDED ENERGY:");

        costLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        costLabel.setForeground(new java.awt.Color(97, 122, 133));
        costLabel.setText("COST:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(projectNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(projectNameTextField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(energyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(costTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonGenerateFile, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sectionsResultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonBack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(algorithmLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(algorithmNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(energyLabel)
                        .addGap(98, 98, 98)
                        .addComponent(timeLabel)
                        .addGap(140, 140, 140)
                        .addComponent(costLabel)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projectNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(algorithmNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(algorithmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(energyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(energyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sectionsResultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonGenerateFile, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orangeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imgLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGenerateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerateFileActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel algorithmLabel;
    private javax.swing.JTextField algorithmNameTextField;
    private javax.swing.JLabel costLabel;
    private javax.swing.JTextField costTextField;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JTextField energyTextField;
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonGenerateFile;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JTextField sectionsResultTextField;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField timeTextField;
    // End of variables declaration//GEN-END:variables
}