/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lapr.project.controller.CopyProjectController;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import static lapr.project.ui.Main.dbCom;

/**
 *
 * @author anily
 */
public class PopUp1 extends javax.swing.JFrame {

    private static final long serialVersionUID = 8060730009198569465L;
    private static Project project;
    /**
     * Creates new form PopUp1
     */
    public PopUp1(Project prjct) {
        project=prjct;
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

        imgLateral = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonChangeData = new javax.swing.JButton();
        jButtonSetActive = new javax.swing.JButton();
        jButtonCopy = new javax.swing.JButton();
        imgLateral1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imgLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fundo.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(155, 177, 189));

        jButtonChangeData.setBackground(new java.awt.Color(45, 46, 45));
        jButtonChangeData.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonChangeData.setForeground(new java.awt.Color(45, 46, 45));
        jButtonChangeData.setText("Change project data");
        jButtonChangeData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonChangeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeDataActionPerformed(evt);
            }
        });

        jButtonSetActive.setBackground(new java.awt.Color(45, 46, 45));
        jButtonSetActive.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonSetActive.setForeground(new java.awt.Color(45, 46, 45));
        jButtonSetActive.setText("Set active");
        jButtonSetActive.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonSetActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetActiveActionPerformed(evt);
            }
        });

        jButtonCopy.setBackground(new java.awt.Color(45, 46, 45));
        jButtonCopy.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonCopy.setForeground(new java.awt.Color(45, 46, 45));
        jButtonCopy.setText("Copy project");
        jButtonCopy.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCopyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSetActive, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonChangeData, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButtonChangeData, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSetActive, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        imgLateral1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fundo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgLateral1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imgLateral1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChangeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeDataActionPerformed
        ChangeDataUI epui = new ChangeDataUI(project);
        this.setVisible(false);
        epui.setVisible(true);
    }//GEN-LAST:event_jButtonChangeDataActionPerformed

    private void jButtonSetActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetActiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSetActiveActionPerformed

    private void jButtonCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopyActionPerformed
        CopyProjectController controller = new CopyProjectController(dbCom);
        
    }//GEN-LAST:event_jButtonCopyActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PopUp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUp1(new Project("test", "description", new RoadNetwork(), new ArrayList<>())).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JLabel imgLateral1;
    private javax.swing.JButton jButtonChangeData;
    private javax.swing.JButton jButtonCopy;
    private javax.swing.JButton jButtonSetActive;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
