/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.SelectProjectController;
import lapr.project.model.Project;

import javax.swing.*;
import java.util.List;

/**
 * @author anily, antelo
 */
public final class SelectProjectUI extends javax.swing.JFrame {

    InitializeUIElements initializer = new InitializeUIElements();
    private static final long serialVersionUID = -5188965937946662366L;
    private SelectProjectController selectProjectController;
    private boolean verifyProjectWasSelected;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCompForm;
    private javax.swing.JButton jButtonPathForm;
    private javax.swing.JButton jButtonPopUp;
    private javax.swing.JButton jButtonSetActive;
    /**
    private javax.swing.JComboBox<String> jComboBoxProjects;
    */
    private javax.swing.JComboBox<Project> jComboBoxProjects;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form SelectProjectUI
     */
    SelectProjectUI() {
        selectProjectController = new SelectProjectController(Main.dbCom);
        verifyProjectWasSelected = false;
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
        jButtonCompForm = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jButtonPathForm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxProjects = new javax.swing.JComboBox<Project>();
        jButtonSetActive = new javax.swing.JButton();
        jButtonPopUp = new javax.swing.JButton();


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

        jButtonCompForm.setBackground(new java.awt.Color(45, 46, 45));
        jButtonCompForm.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonCompForm.setForeground(new java.awt.Color(45, 46, 45));
        jButtonCompForm.setText("Road Network Comparison Form");
        jButtonCompForm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonCompForm.addActionListener(evt -> jButtonCompFormActionPerformed(evt));

        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        jButtonBack.setForeground(new java.awt.Color(45, 46, 45));
        jButtonBack.setText("«");
        jButtonBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonBack.addActionListener(evt -> jButtonBackActionPerformed(evt));

        jButtonPathForm.setBackground(new java.awt.Color(45, 46, 45));
        jButtonPathForm.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonPathForm.setForeground(new java.awt.Color(45, 46, 45));
        jButtonPathForm.setText("Road Network Path Form");
        jButtonPathForm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonPathForm.addActionListener(evt -> jButtonPathFormActionPerformed(evt));

        jLabel2.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 177, 189));
        jLabel2.setText("Select");

        jLabel3.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 177, 189));
        jLabel3.setText("a");

        jLabel4.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 177, 189));
        jLabel4.setText("project");

        List<Project> projectsList = selectProjectController.fetchProjectsList();
        DefaultComboBoxModel<Project> projectsModel = new DefaultComboBoxModel<>();
        for (Project obj : projectsList) {
            projectsModel.addElement(obj);
        }
        jComboBoxProjects.setModel(projectsModel);
        jComboBoxProjects.setBackground(new java.awt.Color(204, 204, 204));

        jButtonSetActive.setBackground(new java.awt.Color(45, 46, 45));
        jButtonSetActive.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonSetActive.setForeground(new java.awt.Color(45, 46, 45));
        jButtonSetActive.setText("Set active");
        jButtonSetActive.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonSetActive.addActionListener(evt -> jButtonSetActiveActionPerformed(evt));

        jButtonPopUp.setBackground(new java.awt.Color(45, 46, 45));
        jButtonPopUp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonPopUp.setForeground(new java.awt.Color(45, 46, 45));
        jButtonPopUp.setText("Other functionalities");
        jButtonPopUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonPopUp.addActionListener(evt -> jButtonPopUpActionPerformed(evt));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orangeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCompForm, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(jButtonPathForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxProjects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSetActive, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imgLateral)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jComboBoxProjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSetActive, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPopUp, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPathForm, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCompForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        WelcomeUI.display();
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonCompFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompFormActionPerformed
        if (verifyProjectWasSelected) {
            BestPathComparisonForm.display();
            setVisible(false);
        } else {
            showOptionPaneRequiredActiveProject();
        }
    }//GEN-LAST:event_jButtonCompFormActionPerformed

    private void jButtonPathFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPathFormActionPerformed
        if (verifyProjectWasSelected) {
            BestPathUI.display();
            setVisible(false);
        } else {
            showOptionPaneRequiredActiveProject();
        }
    }//GEN-LAST:event_jButtonPathFormActionPerformed

    private void jButtonSetActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetActiveActionPerformed
        if (jComboBoxProjects.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Select a project!",
                    "Open Project",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            verifyProjectWasSelected = true;
            Project selectedProject = (Project) jComboBoxProjects.getSelectedItem();
            selectProjectController.setCurrentProject(selectedProject);
            JOptionPane.showMessageDialog(null, selectedProject.getName()+" is now your active project.");
        }

    }//GEN-LAST:event_jButtonSetActiveActionPerformed

    private void jButtonPopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPopUpActionPerformed
        if (verifyProjectWasSelected) {
            new PopUp1(this).setVisible(true);
        } else {
            showOptionPaneRequiredActiveProject();
        }
    }//GEN-LAST:event_jButtonPopUpActionPerformed

    /**
     * Triggers UI display
     */
    public static void display() {
        Main.setLook();
       java.awt.EventQueue.invokeLater(() -> new SelectProjectUI().setVisible(true));
    }

    private void showOptionPaneRequiredActiveProject(){
        JOptionPane.showMessageDialog(
                this,
                "Set a project as active first!",
                "Require active project",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
