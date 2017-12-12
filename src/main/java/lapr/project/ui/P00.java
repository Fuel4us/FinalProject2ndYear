/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

/**
 *
 * @author anily
 */
public class P00 extends javax.swing.JPanel {

    /**
     * Creates new form P00
     */
    public P00() {
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
        jButtonCreate = new javax.swing.JButton();
        orangeBorder = new javax.swing.JPanel();
        jButtonCopy = new javax.swing.JButton();
        jButtonSetProject = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(45, 46, 45));

        imgLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fundo.png"))); // NOI18N

        jButtonCreate.setBackground(new java.awt.Color(45, 46, 45));
        jButtonCreate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonCreate.setForeground(new java.awt.Color(45, 46, 45));
        jButtonCreate.setText("Create project");
        jButtonCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonCreate.setDisabledIcon(null);
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

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
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jButtonCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonSetProject, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imgLateral)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButtonSetProject, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jButtonCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCopyActionPerformed

    private void jButtonSetProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetProjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSetProjectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonCopy;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonSetProject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables
}
