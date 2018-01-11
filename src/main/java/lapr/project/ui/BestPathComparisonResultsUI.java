/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.model.Analysis;

/**
 *
 * @author anily
 */
public class BestPathComparisonResultsUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 2562000671949976456L;
    private static Analysis analysis;

    /**
     * Creates new form NewJFrame
     */
    BestPathComparisonResultsUI(Analysis analysis) {
        this.analysis = analysis;
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

        jPanelVehicleAnalysis = new javax.swing.JPanel();
        projectNameTextField = new javax.swing.JTextField();
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
        jPanel2 = new javax.swing.JPanel();
        jLabelPageTitle = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jButtonRequestAnalysis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(45, 46, 45));

        jPanelVehicleAnalysis.setBackground(new java.awt.Color(45, 46, 45));

        projectNameTextField.setBackground(new java.awt.Color(97, 122, 133));
        projectNameTextField.setForeground(new java.awt.Color(204, 204, 204));
        projectNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        projectNameTextField.setText("<sample project name>");
        projectNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));

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

        javax.swing.GroupLayout jPanelVehicleAnalysisLayout = new javax.swing.GroupLayout(jPanelVehicleAnalysis);
        jPanelVehicleAnalysis.setLayout(jPanelVehicleAnalysisLayout);
        jPanelVehicleAnalysisLayout.setHorizontalGroup(
            jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVehicleAnalysisLayout.createSequentialGroup()
                .addGroup(jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVehicleAnalysisLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(projectNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVehicleAnalysisLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelVehicleAnalysisLayout.createSequentialGroup()
                                .addComponent(energyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(costTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sectionsResultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelVehicleAnalysisLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(algorithmLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(algorithmNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVehicleAnalysisLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(energyLabel)
                        .addGap(98, 98, 98)
                        .addComponent(timeLabel)
                        .addGap(140, 140, 140)
                        .addComponent(costLabel)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanelVehicleAnalysisLayout.setVerticalGroup(
            jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVehicleAnalysisLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projectNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(algorithmNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(algorithmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(energyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVehicleAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(energyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sectionsResultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
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
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(45, 46, 45));

        jLabelPageTitle.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabelPageTitle.setForeground(new java.awt.Color(155, 177, 189));
        jLabelPageTitle.setText("Best Path Comparison Form");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPageTitle)
                .addGap(120, 120, 120))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabelPageTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(45, 46, 45));

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

        jButtonRequestAnalysis.setBackground(new java.awt.Color(45, 46, 45));
        jButtonRequestAnalysis.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonRequestAnalysis.setForeground(new java.awt.Color(45, 46, 45));
        jButtonRequestAnalysis.setText("Request new analysis");
        jButtonRequestAnalysis.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonRequestAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRequestAnalysisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRequestAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addGap(100, 100, 100))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRequestAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orangeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelVehicleAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPanelVehicleAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(orangeBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(imgLateral, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonRequestAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRequestAnalysisActionPerformed
        //ToDo
    }//GEN-LAST:event_jButtonRequestAnalysisActionPerformed

    /**
     * Triggers UI display
     */
    public static void display() {
        Main.setLook();
        java.awt.EventQueue.invokeLater(() -> new BestPathComparisonResultsUI(analysis).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel algorithmLabel;
    private javax.swing.JTextField algorithmNameTextField;
    private javax.swing.JLabel costLabel;
    private javax.swing.JTextField costTextField;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JTextField energyTextField;
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonRequestAnalysis;
    private javax.swing.JLabel jLabelPageTitle;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelVehicleAnalysis;
    private javax.swing.JPanel orangeBorder;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JTextField sectionsResultTextField;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField timeTextField;
    // End of variables declaration//GEN-END:variables
}
