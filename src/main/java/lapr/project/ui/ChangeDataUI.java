/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.ChangeDataController;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Pedro F.
 */
public class ChangeDataUI extends javax.swing.JFrame {


    private static final long serialVersionUID = -4862991771890138038L;
    private ChangeDataController controller;

    /**
     * Creates new form ChangeDataUI
     */
    private ChangeDataUI() {
        this.controller = new ChangeDataController(Main.currentProject, Main.dbCom);
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
        jButtonCreate = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jLabelPageTitle = new javax.swing.JLabel();
        jLabelPName = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabelPDesc = new javax.swing.JLabel();
        jButtonRoad = new javax.swing.JButton();

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

        jButtonCreate.setBackground(new java.awt.Color(45, 46, 45));
        jButtonCreate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonCreate.setForeground(new java.awt.Color(45, 46, 45));
        jButtonCreate.setText("Save changes");
        jButtonCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
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

        jTextFieldName.setBackground(new java.awt.Color(87, 89, 87));
        jTextFieldName.setForeground(new java.awt.Color(45, 46, 45));
        jTextFieldName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextFieldName.setText(controller.getName());

        jLabelPageTitle.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabelPageTitle.setForeground(new java.awt.Color(155, 177, 189));
        jLabelPageTitle.setText("Change Project Properties");

        jLabelPName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelPName.setForeground(new java.awt.Color(97, 122, 133));
        jLabelPName.setText("PROJECT NAME:");

        jTextFieldDescription.setBackground(new java.awt.Color(87, 89, 87));
        jTextFieldDescription.setForeground(new java.awt.Color(45, 46, 45));
        jTextFieldDescription.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextFieldDescription.setText(controller.getDescription());

        jLabelPDesc.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelPDesc.setForeground(new java.awt.Color(97, 122, 133));
        jLabelPDesc.setText("DESCRIPTION:");

        jButtonRoad.setBackground(new java.awt.Color(45, 46, 45));
        jButtonRoad.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButtonRoad.setForeground(new java.awt.Color(72, 89, 97));
        jButtonRoad.setText("Import new roads configuration file");
        jButtonRoad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(155, 177, 189), 2, true));
        jButtonRoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRoadActionPerformed(evt);
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabelPName)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(28, 28, 28))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabelPDesc)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButtonRoad, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jTextFieldDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jButtonCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(100, 100, 100)
                                                .addComponent(jLabelPageTitle)))
                                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imgLateral)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabelPageTitle)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelPName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelPDesc))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonRoad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        JOptionPane.showMessageDialog(null, "Changes were successfully made in your project.");
        PopUp1 pop = new PopUp1();
        pop.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        PopUp1 pop = new PopUp1();
        pop.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonRoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRoadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select your RoadNetwork file");
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
                "xml files (*.xml)", "xml");
        fileChooser.setFileFilter(xmlFilter);
        int returnVal = fileChooser.showOpenDialog(jButtonRoad);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File roads = fileChooser.getSelectedFile();
            try {
                controller.addNewRoads(roads);
                JOptionPane.showMessageDialog(null, "Your file has been loaded.");
            } catch (IOException ex) {
                Logger.getLogger(ChangeDataUI.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "IOException rose there was a problem with your file");
            } catch (SAXException ex) {
                Logger.getLogger(ChangeDataUI.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "SAXException rose there was a problem with your file");
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ChangeDataUI.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "ParserConfigurationException rose there was a problem with your file");
            } catch (Exception e) {
                Logger.getLogger(ChangeDataUI.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonRoadActionPerformed

    /**
     * Triggers UI display
     */
    public static void display() {
        Main.setLook();
        java.awt.EventQueue.invokeLater(() -> new ChangeDataUI().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonRoad;
    private javax.swing.JLabel jLabelPDesc;
    private javax.swing.JLabel jLabelPName;
    private javax.swing.JLabel jLabelPageTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables
}
