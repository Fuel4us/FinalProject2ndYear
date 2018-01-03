/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.BestPathController;
import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import lapr.project.utils.pathAlgorithm.PathAlgorithm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anily
 */
public class BestPathUI extends JFrame {

    private BestPathController controller;
    private static final long serialVersionUID = -8320152827152597623L;
    private Project project;
    private Analysis generatedAnalysis;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAlgorithm1;
    private javax.swing.JLabel jLabelLoad;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextFieldLoad;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form RoadNetworkPathFormUI
     */
    BestPathUI(Project project) {
        super("Best Path");
        this.controller = new BestPathController(project);
        initComponents();
        this.project = project;
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
        DefaultListModel<Vehicle> vehicleModel = new DefaultListModel<>();
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
        jLabel2.setText("Road Network Path Form");

        jButton1.setText("N10 - Fastest Path");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 152, 60), 3));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeAlgorithmN10(evt);
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

        jLabelLoad.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabelLoad.setForeground(new java.awt.Color(97, 122, 133));
        jLabelLoad.setText("LOAD:");

        jTextFieldLoad.setBackground(new java.awt.Color(97, 122, 133));
        jTextFieldLoad.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldLoad.setText("«sample number»");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imgLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orangeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNode1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabelAlgorithm1)
                        .addGap(352, 352, 352))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jLabelNode2)
                                    .addComponent(jLabelLoad)
                                    .addComponent(jTextFieldLoad, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane4)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButtonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orangeBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imgLateral)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlgorithm1)
                    .addComponent(jLabelNode1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNode2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLoad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButtonBackActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        SelectProjectUI.main(null);
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void executeAlgorithmN10(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Algorithm N10
//ToDo  Analyis generatedAnalysis = controller.executeAlgorithm(N10);
        Node startNode = jListNodes1.getSelectedValue();
        Node endNode = jListNodes2.getSelectedValue();

        Measurable load = new Measurable(Integer.parseInt(jTextFieldLoad.getText()), Unit.valueOf("km"));
        Vehicle selectedVehicle = jListVehicles.getSelectedValue();
        if (startNode != null
                && endNode != null
                && selectedVehicle != null) {

            new PathAlgorithm().fastestPath(project, startNode, endNode, selectedVehicle, load);

            //TEST ONLY
            List<Section> bestPath = new ArrayList<>();
            Node n1 = new Node("n1");
            Node n2 = new Node("n2");
            List<Segment> segments = new ArrayList<>();
            List<Double> tollFare = new ArrayList<>();
            tollFare.add(30d);
            Road road = new Road("3", "road1", "highway", tollFare);
            List<Double> tollFare1 = new ArrayList<>();
            tollFare1.add(50d);
            bestPath.add(new Section(n1, n2, Direction.BIDIRECTIONAL, segments, road, tollFare1));
            Analysis generatedAnalysis = new Analysis(project, "N10", bestPath, new Measurable(300, Unit.KILOJOULE), new Measurable(3, Unit.HOUR), new Measurable(50, Unit.EUROS));

            new StoreNetworkAnalysisUI(project, generatedAnalysis);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "You must first select starting and ending nodes, as well as a vehicle.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

}
