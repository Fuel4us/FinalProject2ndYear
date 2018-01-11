/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.ArrayList;
import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.Vehicle.Vehicle;

import javax.swing.*;
import java.util.List;
import lapr.project.controller.BestPathController;
import lapr.project.model.Analysis;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

/**
 *
 * @author anily
 */
public class BestPathComparisonForm extends JFrame {

    private static final long serialVersionUID = -8320152827152597624L;
    private DefaultListModel<Vehicle> vehicleModel;
    private DefaultListModel<Vehicle> selectedVehicles;
    private BestPathController controller;

    /**
     * Creates new form BestPathComparisonForm
     *
     */
    public BestPathComparisonForm() {
        this.controller = new BestPathController(Main.currentProject);
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
        jButtonBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListNodes2 = new javax.swing.JList<Node>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListNodes1 = new javax.swing.JList<Node>();
        jLabelNode1 = new javax.swing.JLabel();
        jLabelNode2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListVehicles = new javax.swing.JList<Vehicle>();
        jLabelAlgorithm1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelLoad = new javax.swing.JLabel();
        jTextFieldLoad = new javax.swing.JTextField();
        addVehicleButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListVehicles1 = new javax.swing.JList<Vehicle>();
        removeVehicleButton = new javax.swing.JButton();
        jLabelAlgorithm2 = new javax.swing.JLabel();
        jLabelLoad1 = new javax.swing.JLabel();
        jTextFieldMaxAceleration = new javax.swing.JTextField();
        jLabelLoad2 = new javax.swing.JLabel();
        jTextFieldMaxBraking = new javax.swing.JTextField();

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

        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        jButtonBack.setForeground(new java.awt.Color(45, 46, 45));
        jButtonBack.setText("«");
        jButtonBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 4));
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        List<Node> nodesList2 = controller.getAllNodes();
        DefaultListModel<Node> nodesModel2 = new DefaultListModel<>();
        for (Node obj : nodesList2) {
            nodesModel2.addElement(obj);
        }
        jListNodes2.setModel(nodesModel2);
        jListNodes2.setBackground(new java.awt.Color(97, 122, 133));
        jListNodes2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListNodes2.setForeground(new java.awt.Color(255, 255, 255));
        /**
        jListNodes2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Node 1", "Node 2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        */
        jListNodes2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListNodes2);

        List<Node> nodesList = controller.getAllNodes();
        DefaultListModel<Node> nodesModel = new DefaultListModel<>();
        for (Node obj : nodesList) {
            nodesModel.addElement(obj);
        }
        jListNodes1.setModel(nodesModel);
        jListNodes1.setBackground(new java.awt.Color(97, 122, 133));
        jListNodes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListNodes1.setForeground(new java.awt.Color(255, 255, 255));
        /**
        jListNodes1.setModel(null);
        */
        jListNodes1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListNodes1);

        jLabelNode1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelNode1.setForeground(new java.awt.Color(97, 122, 133));
        jLabelNode1.setText("ORIGIN NODE:");

        jLabelNode2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelNode2.setForeground(new java.awt.Color(97, 122, 133));
        jLabelNode2.setText("DESTINY NODE:");

        List<Vehicle> vehicleList = controller.getAllVehicles();
        for (Vehicle obj : vehicleList) {
            vehicleModel.addElement(obj);
        }
        jListVehicles.setModel(vehicleModel);
        jListVehicles.setBackground(new java.awt.Color(97, 122, 133));
        jListVehicles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListVehicles.setForeground(new java.awt.Color(255, 255, 255));
        /**
        jListVehicles.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "V1", "V2", "V3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        */
        jListVehicles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jListVehicles);

        jLabelAlgorithm1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelAlgorithm1.setForeground(new java.awt.Color(97, 122, 133));
        jLabelAlgorithm1.setText("VEHICLES:");

        jLabel2.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 177, 189));
        jLabel2.setText("Road Network Comparison Form");

        jButton1.setText("N10 - Fastest Path");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("N11 -  Theoretical most energy efficient path");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("N12 - Most efficient path in energy saving mode");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabelLoad.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelLoad.setForeground(new java.awt.Color(97, 122, 133));
        jLabelLoad.setText("LOAD:");

        jTextFieldLoad.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldLoad.setForeground(new java.awt.Color(255, 255, 255));

        addVehicleButton.setText("add vehicle");
        addVehicleButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(97, 122, 133), 3, true));
        addVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehicleButtonActionPerformed(evt);
            }
        });

        jListVehicles.setModel(selectedVehicles);
        jListVehicles1.setBackground(new java.awt.Color(97, 122, 133));
        jListVehicles1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListVehicles1.setForeground(new java.awt.Color(255, 255, 255));
        /**
        jListVehicles1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "V1", "V2", "V3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        */
        jListVehicles1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jListVehicles1);

        removeVehicleButton.setText("remove vehicle");
        removeVehicleButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(97, 122, 133), 3, true));
        removeVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVehicleButtonActionPerformed(evt);
            }
        });

        jLabelAlgorithm2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelAlgorithm2.setForeground(new java.awt.Color(97, 122, 133));
        jLabelAlgorithm2.setText("SELECTED VEHICLES:");

        jLabelLoad1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelLoad1.setForeground(new java.awt.Color(97, 122, 133));
        jLabelLoad1.setText("MAX ACCELERATION:");

        jTextFieldMaxAceleration.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldMaxAceleration.setForeground(new java.awt.Color(255, 255, 255));

        jLabelLoad2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelLoad2.setForeground(new java.awt.Color(97, 122, 133));
        jLabelLoad2.setText("MAX BRAKING:");

        jTextFieldMaxBraking.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldMaxBraking.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNode2)
                                    .addComponent(jLabelLoad))
                                .addGap(87, 87, 87))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelLoad2)
                                .addComponent(jLabelLoad1)
                                .addComponent(jScrollPane2)
                                .addComponent(jTextFieldLoad)
                                .addComponent(jTextFieldMaxAceleration)
                                .addComponent(jTextFieldMaxBraking)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabelAlgorithm1))
                                    .addComponent(addVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(removeVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAlgorithm2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNode1)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imgLateral)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlgorithm1)
                    .addComponent(jLabelNode1)
                    .addComponent(jLabelAlgorithm2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addVehicleButton)
                            .addComponent(removeVehicleButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabelNode2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLoad1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMaxAceleration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLoad2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMaxBraking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        SelectProjectUI.display();
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();

            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.valueOf("km"));
            List<Vehicle> selectedVehiclesList = new ArrayList<>();
            for (int i = 0; i < selectedVehicles.size(); i++) {
                selectedVehiclesList.add(selectedVehicles.get(i));
            }
            if (startNode == null
                    || endNode == null) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (int j = 0; j < selectedVehiclesList.size(); j++) {
                    Analysis generatedAnalysis = controller.analyzeFastestPath(startNode, endNode, selectedVehiclesList.get(j), load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI comparisonResultsUI = new BestPathComparisonAllAnalysisUI(analysisList);
                comparisonResultsUI.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid load value");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();
            Measurable maxAceleration = new Measurable(Double.parseDouble(jTextFieldMaxAceleration.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable maxBraking = new Measurable(Double.parseDouble(jTextFieldMaxBraking.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.valueOf("km"));
            List<Vehicle> selectedVehiclesList = new ArrayList<>();
            for (int i = 0; i < selectedVehicles.size(); i++) {
                selectedVehiclesList.add(selectedVehicles.get(i));
            }
            if (startNode == null
                    || endNode == null
                    ) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes, as well as a vehicle.");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else if ((Double.compare(maxAceleration.getQuantity(), 0) == -1) || (Double.compare(maxBraking.getQuantity(), 0) == 1)) {
                JOptionPane.showMessageDialog(null, "Please enter a positive value for maxAceleration and a negative value for maxBraking");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (int j = 0; j < selectedVehiclesList.size(); j++) {
                    Analysis generatedAnalysis = controller.analyzeTheoreticalEfficientPath(startNode, endNode, selectedVehiclesList.get(j), maxAceleration, maxBraking, load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI allAnalysisResults = new BestPathComparisonAllAnalysisUI(analysisList);
                allAnalysisResults.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid load value");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void addVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVehicleButtonActionPerformed
        selectedVehicles.addElement(jListVehicles.getSelectedValue());
        vehicleModel.remove(jListVehicles.getSelectedIndex());
    }//GEN-LAST:event_addVehicleButtonActionPerformed

    private void removeVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVehicleButtonActionPerformed
        vehicleModel.addElement(jListVehicles1.getSelectedValue());
        jListVehicles1.remove(jListVehicles1.getSelectedIndex());
    }//GEN-LAST:event_removeVehicleButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();
            Measurable maxAceleration = new Measurable(Double.parseDouble(jTextFieldMaxAceleration.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable maxBraking = new Measurable(Double.parseDouble(jTextFieldMaxBraking.getText()), Unit.METERS_PER_SECOND_SQUARED);
            List<Vehicle> selectedVehiclesList = new ArrayList<>();
            for (int i = 0; i < selectedVehicles.size(); i++) {
                selectedVehiclesList.add(selectedVehicles.get(i));
            }
            if (startNode == null
                    || endNode == null
                    ) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes, as well as a vehicle.");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else if ((Double.compare(maxAceleration.getQuantity(), 0) == -1) || (Double.compare(maxBraking.getQuantity(), 0) == 1)) {
                JOptionPane.showMessageDialog(null, "Please enter a positive value for maxAceleration and a negative value for maxBraking");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (int j = 0; j < selectedVehiclesList.size(); j++) {
                    Analysis generatedAnalysis = controller.analyzeEfficientPathEnergySavingMode(startNode, endNode, selectedVehiclesList.get(j), maxAceleration, maxBraking);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI allAnalysisResults = new BestPathComparisonAllAnalysisUI(analysisList);
                allAnalysisResults.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid values");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(BestPathComparisonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BestPathComparisonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BestPathComparisonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BestPathComparisonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new BestPathComparisonForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVehicleButton;
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAlgorithm1;
    private javax.swing.JLabel jLabelAlgorithm2;
    private javax.swing.JLabel jLabelLoad;
    private javax.swing.JLabel jLabelLoad1;
    private javax.swing.JLabel jLabelLoad2;
    private javax.swing.JLabel jLabelNode1;
    private javax.swing.JLabel jLabelNode2;
    /**
    private javax.swing.JList<String> jListNodes1;
    */
    private javax.swing.JList<Node> jListNodes1;
    /**
    private javax.swing.JList<String> jListNodes2;
    */
    private javax.swing.JList<Node> jListNodes2;
    /**
    private javax.swing.JList<String> jListVehicles;
    */
    private javax.swing.JList<Vehicle> jListVehicles;
    /**
    private javax.swing.JList<String> jListVehicles1;
    */
    private javax.swing.JList<Vehicle> jListVehicles1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextFieldLoad;
    private javax.swing.JTextField jTextFieldMaxAceleration;
    private javax.swing.JTextField jTextFieldMaxBraking;
    private javax.swing.JPanel orangeBorder;
    private javax.swing.JButton removeVehicleButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Triggers UI display
     */
    public static void display() {
        Main.setLook();
        java.awt.EventQueue.invokeLater(() -> new BestPathComparisonForm().setVisible(true));
    }
}
