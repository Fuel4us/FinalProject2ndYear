/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import lapr.project.model.Node;
import lapr.project.model.Vehicle;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private final List<Vehicle> vehicleList;
    private DefaultListModel<Vehicle> selectedVehiclesModel;
    private BestPathController controller;

    /**
     * Creates new form BestPathComparisonForm
     *
     */
    private BestPathComparisonForm() {
        super("Best Path");
        this.controller = new BestPathController(Main.currentProject);
        vehicleList = controller.getAllVehicles();
        vehicleModel = new DefaultListModel<>();
        vehicleList.forEach(vehicleModel::addElement);
        initComponents();
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

        //<editor-fold desc="object initialization">
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
        jListSelectedVehicles = new javax.swing.JList<Vehicle>();
        removeVehicleButton = new javax.swing.JButton();
        jLabelAlgorithm2 = new javax.swing.JLabel();
        jLabelLoad1 = new javax.swing.JLabel();
        jTextFieldMaxAcceleration = new javax.swing.JTextField();
        jLabelLoad2 = new javax.swing.JLabel();
        jTextFieldMaxBraking = new javax.swing.JTextField();
        InitializeUIElements initializer = new InitializeUIElements();

        //</editor-fold>
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

        initializer.initializeJButton(jButtonBack, Main.FORTY_EIGHT_SEGOE_FONT, "«", Main.DARK_GREY, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 4));
        jButtonBack.setBackground(new java.awt.Color(45, 46, 45));
        jButtonBack.addActionListener(this::jButtonBackActionPerformed);

        List<Node> nodesList = controller.getAllNodes();
        DefaultListModel<Node> nodesModel2 = new DefaultListModel<>();
        nodesList.forEach(nodesModel2::addElement);

        jListNodes2.setModel(nodesModel2);
        jListNodes2.setBackground(new java.awt.Color(97, 122, 133));
        jListNodes2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListNodes2.setForeground(new java.awt.Color(255, 255, 255));
        jListNodes2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListNodes2);

        DefaultListModel<Node> nodesModel = new DefaultListModel<>();
        nodesList.forEach(nodesModel::addElement);

        jListNodes1.setModel(nodesModel);
        jListNodes1.setBackground(new java.awt.Color(97, 122, 133));
        jListNodes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListNodes1.setForeground(new java.awt.Color(255, 255, 255));
        jListNodes1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListNodes1);

        initializer.initializeLabels(jLabelNode1, Main.TWELVE_SEGOE_FONT, "ORIGIN NODE:", SwingConstants.RIGHT, Main.DARK_BLUE);

        initializer.initializeLabels(jLabelNode2, Main.TWELVE_SEGOE_FONT, "DESTINY NODE:", SwingConstants.RIGHT, Main.DARK_BLUE);

        jListVehicles.setModel(vehicleModel);
        jListVehicles.setBackground(new java.awt.Color(97, 122, 133));
        jListVehicles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListVehicles.setForeground(new java.awt.Color(255, 255, 255));
        jListVehicles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jListVehicles);

        initializer.initializeLabels(jLabelAlgorithm1, Main.TWELVE_SEGOE_FONT, "VEHICLES:", SwingConstants.RIGHT, Main.DARK_BLUE);
        initializer.initializeLabels(jLabel2, Main.TV_POSTER_FONT, "Road Network Comparison Form", SwingConstants.CENTER, Main.LIGHT_BLUE);

        jButton1.setText("N10 - Fastest Path");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton1.addActionListener(this::executeFastestPath);

        jButton2.setText("N11 -  Theoretical most energy efficient path");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton2.addActionListener(this::executeTheoreticalEfficientPath);

        jButton3.setText("N12 - Most efficient path in energy saving mode");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton3.addActionListener(this::executeEfficientPathEnergySavingMode);

        initializer.initializeLabels(jLabelLoad, Main.TWELVE_SEGOE_FONT, "LOAD:", SwingConstants.RIGHT, Main.DARK_BLUE);

        jTextFieldLoad.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldLoad.setForeground(new java.awt.Color(255, 255, 255));

        addVehicleButton.setText("add vehicle");
        addVehicleButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(97, 122, 133), 3, true));
        addVehicleButton.addActionListener(evt -> addVehicleButtonActionPerformed());

        selectedVehiclesModel = new DefaultListModel<>();
        jListSelectedVehicles.setModel(selectedVehiclesModel);
        jListSelectedVehicles.setBackground(new java.awt.Color(97, 122, 133));
        jListSelectedVehicles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(155, 177, 189), 2));
        jListSelectedVehicles.setForeground(new java.awt.Color(255, 255, 255));
        jListSelectedVehicles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jListSelectedVehicles);

        removeVehicleButton.setText("remove vehicle");
        removeVehicleButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(97, 122, 133), 3, true));
        removeVehicleButton.addActionListener(evt -> removeVehicleButtonActionPerformed());

        initializer.initializeLabels(jLabelAlgorithm2, Main.TWELVE_SEGOE_FONT, "SELECTED VEHICLES:", SwingConstants.RIGHT, Main.DARK_BLUE);

        initializer.initializeLabels(jLabelLoad1, Main.TWELVE_SEGOE_FONT, "MAX ACCELERATION:", SwingConstants.RIGHT, Main.DARK_BLUE);

        jTextFieldMaxAcceleration.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldMaxAcceleration.setForeground(new java.awt.Color(255, 255, 255));

        initializer.initializeLabels(jLabelLoad2, Main.TWELVE_SEGOE_FONT, "MAX BRAKING:", SwingConstants.RIGHT, Main.DARK_BLUE);

        jTextFieldMaxBraking.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldMaxBraking.setForeground(new java.awt.Color(255, 255, 255));

        //<editor-fold desc="Layout positioning">
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
                                .addComponent(jTextFieldMaxAcceleration)
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
                        .addComponent(jTextFieldMaxAcceleration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        //</editor-fold>

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        SelectProjectUI.display();
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void executeFastestPath(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();

            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.KILOGRAM);

            List<Vehicle> selectedVehiclesList = controller.getAllVehicles();
            selectedVehiclesList.forEach(vehicleModel::addElement);

            if (startNode == null
                    || endNode == null) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (Vehicle aSelectedVehiclesList : selectedVehiclesList) {
                    Analysis generatedAnalysis = controller.analyzeFastestPath(startNode, endNode, aSelectedVehiclesList, load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI comparisonResultsUI = new BestPathComparisonAllAnalysisUI(analysisList, selectedVehiclesList);
                comparisonResultsUI.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid load value");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void executeTheoreticalEfficientPath(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int option = JOptionPane.showConfirmDialog(null,"Do you intend to use polynomial interpolation? (N13)");
        if (option == JOptionPane.YES_OPTION){
            executeN13ButtonN11();
        } else if (option == JOptionPane.NO_OPTION) {
            executeN11();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void executeEfficientPathEnergySavingMode(ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int option = JOptionPane.showConfirmDialog(null,"Do you intend to use polynomial interpolation? (N13)");
        if (option == JOptionPane.YES_OPTION){
            executeN13ButtonN12();
        } else if (option == JOptionPane.NO_OPTION) {
            executeN12();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void addVehicleButtonActionPerformed() {//GEN-FIRST:event_addVehicleButtonActionPerformed
        selectedVehiclesModel.addElement(jListVehicles.getSelectedValue());
        vehicleModel.remove(jListVehicles.getSelectedIndex());
    }//GEN-LAST:event_addVehicleButtonActionPerformed

    private void removeVehicleButtonActionPerformed() {//GEN-FIRST:event_removeVehicleButtonActionPerformed
        vehicleModel.addElement(jListSelectedVehicles.getSelectedValue());
        selectedVehiclesModel.remove(jListSelectedVehicles.getSelectedIndex());
    }//GEN-LAST:event_removeVehicleButtonActionPerformed

    /**
     *
     * Creates an analysis according to algorithm of n13
     */
    private void executeN13ButtonN11() {
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();
            Measurable maxAcceleration = new Measurable(Double.parseDouble(jTextFieldMaxAcceleration.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable maxBraking = new Measurable(Double.parseDouble(jTextFieldMaxBraking.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.KILOGRAM);

            List<Vehicle> selectedVehiclesList = controller.getAllVehicles();
            selectedVehiclesList.forEach(selectedVehiclesModel::addElement);

            if (startNode == null
                    || endNode == null) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (Vehicle aSelectedVehiclesList : selectedVehiclesList) {
                    Analysis generatedAnalysis = controller.efficientPathPolynomialInterpolationN11Button(startNode, endNode, aSelectedVehiclesList, maxAcceleration, maxBraking, load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI comparisonResultsUI = new BestPathComparisonAllAnalysisUI(analysisList, selectedVehiclesList);
                comparisonResultsUI.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid values");
        }
    }

    /**
     *
     * Creates an analysis according to algorithm of n13
     */
    private void executeN13ButtonN12() {
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();
            Measurable maxAcceleration = new Measurable(Double.parseDouble(jTextFieldMaxAcceleration.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable maxBraking = new Measurable(Double.parseDouble(jTextFieldMaxBraking.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.KILOGRAM);

            List<Vehicle> selectedVehiclesList;
            ListModel<Vehicle> selectedVehiclesModel = jListSelectedVehicles.getModel();
            selectedVehiclesList = IntStream.range(0, selectedVehiclesModel.getSize())
                    .mapToObj(selectedVehiclesModel::getElementAt)
                    .collect(Collectors.toList());

            if (startNode == null || endNode == null) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (Vehicle selectedVehicle : selectedVehiclesList) {
                    Analysis generatedAnalysis = controller.efficientPathPolynomialInterpolationN12Button(startNode, endNode, selectedVehicle, maxAcceleration, maxBraking, load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI comparisonResultsUI = new BestPathComparisonAllAnalysisUI(analysisList, selectedVehiclesList);
                comparisonResultsUI.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid values");
        }
    }

    /**
     * Creates an analysis according to algorithm of n12
     */
    private void executeN12() {
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();
            Measurable maxAcceleration = new Measurable(Double.parseDouble(jTextFieldMaxAcceleration.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable maxBraking = new Measurable(Double.parseDouble(jTextFieldMaxBraking.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.KILOGRAM);

            List<Vehicle> selectedVehiclesList = controller.getAllVehicles();
            selectedVehiclesList.forEach(vehicleModel::addElement);

            if (startNode == null
                    || endNode == null) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (Vehicle aSelectedVehiclesList : selectedVehiclesList) {
                    Analysis generatedAnalysis = controller.analyzeEfficientPathEnergySavingMode(startNode, endNode, aSelectedVehiclesList, maxAcceleration, maxBraking, load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI comparisonResultsUI = new BestPathComparisonAllAnalysisUI(analysisList, selectedVehiclesList);
                comparisonResultsUI.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid load value to the selected vehicle");
        }
    }

    /**
     * Creates an analysis according to algorithm of n11
     */
    private void executeN11() {
        try {
            Node startNode = jListNodes1.getSelectedValue();
            Node endNode = jListNodes2.getSelectedValue();
            Measurable maxAcceleration = new Measurable(Double.parseDouble(jTextFieldMaxAcceleration.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable maxBraking = new Measurable(Double.parseDouble(jTextFieldMaxBraking.getText()), Unit.METERS_PER_SECOND_SQUARED);
            Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.KILOGRAM);

            List<Vehicle> selectedVehiclesList = controller.getAllVehicles();
            selectedVehiclesList.forEach(vehicleModel::addElement);

            if (startNode == null
                    || endNode == null) {
                JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes");

            } else if (startNode.equals(endNode)) {
                JOptionPane.showMessageDialog(null, "Please select different start and end nodes.");
            } else {
                List<Analysis> analysisList = new ArrayList<>();
                for (Vehicle aSelectedVehiclesList : selectedVehiclesList) {
                    Analysis generatedAnalysis = controller.analyzeTheoreticalEfficientPath(startNode, endNode, aSelectedVehiclesList, maxAcceleration, maxBraking, load);
                    analysisList.add(generatedAnalysis);
                }
                BestPathComparisonAllAnalysisUI comparisonResultsUI = new BestPathComparisonAllAnalysisUI(analysisList, selectedVehiclesList);
                comparisonResultsUI.setVisible(true);
                setVisible(false);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Please insert a valid load value to the selected vehicle");
        }
    }

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
    private javax.swing.JList<String> jListSelectedVehicles;
    */
    private javax.swing.JList<Vehicle> jListSelectedVehicles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextFieldLoad;
    private javax.swing.JTextField jTextFieldMaxAcceleration;
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
