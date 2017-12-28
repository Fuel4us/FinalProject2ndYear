/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import javax.swing.*;
import lapr.project.controller.SelectProjectController;
import static lapr.project.ui.Main.dbCom;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;

/**
 *
 * Triggers all subsequent UIs
 */
public class WelcomeUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 8935086433569396442L;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonSetProject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel orangeBorder2;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form Welcome
     */
    public WelcomeUI() {
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

        jPanel3 = new javax.swing.JPanel();
        imgLateral = new javax.swing.JLabel();
        jButtonCreate = new javax.swing.JButton();
        orangeBorder2 = new javax.swing.JPanel();
        jButtonSetProject = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(45, 46, 45));

        imgLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fundo.png"))); // NOI18N

        jButtonCreate.setBackground(new java.awt.Color(45, 46, 45));
        jButtonCreate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonCreate.setForeground(new java.awt.Color(45, 46, 45));
        jButtonCreate.setText("Create project");
        jButtonCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        orangeBorder2.setBackground(new java.awt.Color(250, 152, 60));

        javax.swing.GroupLayout orangeBorder2Layout = new javax.swing.GroupLayout(orangeBorder2);
        orangeBorder2.setLayout(orangeBorder2Layout);
        orangeBorder2Layout.setHorizontalGroup(
            orangeBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        orangeBorder2Layout.setVerticalGroup(
            orangeBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButtonSetProject.setBackground(new java.awt.Color(45, 46, 45));
        jButtonSetProject.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonSetProject.setForeground(new java.awt.Color(45, 46, 45));
        jButtonSetProject.setText("I already have an existing project");
        jButtonSetProject.setToolTipText("");
        jButtonSetProject.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonSetProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetProjectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 177, 189));
        jLabel1.setText("Please select what you wish to do");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orangeBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSetProject, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                            .addComponent(jButtonCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orangeBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(imgLateral)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jButtonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButtonSetProject, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        CreateProjectUI ui = new CreateProjectUI();
        setVisible(false);
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jButtonSetProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetProjectActionPerformed
        SelectProjectController controller = new SelectProjectController(dbCom);
        
        if(controller.fetchProjectsList().isEmpty()){
            JOptionPane.showMessageDialog(
                        this,
                        "It's not possible to open a project because no one was created yet!",
                        "Open Project",
                        JOptionPane.INFORMATION_MESSAGE);
        }else {
            SelectProjectUI ui = new SelectProjectUI(controller);
            setVisible(false);
        }  
    }//GEN-LAST:event_jButtonSetProjectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new WelcomeUI().setVisible(true));
    }


}
