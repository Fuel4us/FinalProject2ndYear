/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import lapr.project.controller.SelectProjectController;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.*;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

/**
 *
 * @author anily, antelo
 */
public class SelectProjectUI extends javax.swing.JFrame {

    InitializeUIElements initializer = new InitializeUIElements();
    private static final long serialVersionUID = -5188965937946662366L;
    private SelectProjectController spc;
    private Project project;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLateral;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCompForm;
    private javax.swing.JButton jButtonPathForm;
    /**
    private javax.swing.JComboBox<String> jComboBox1;
    */
    private javax.swing.JComboBox<Project> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel orangeBorder;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form SelectProjectUI
     * @param controller
     */
    public SelectProjectUI(SelectProjectController controller) {
        this.spc = controller;
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
        jComboBox1 = new javax.swing.JComboBox<Project>();

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
        jButtonCompForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompFormActionPerformed(evt);
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

        jButtonPathForm.setBackground(new java.awt.Color(45, 46, 45));
        jButtonPathForm.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButtonPathForm.setForeground(new java.awt.Color(45, 46, 45));
        jButtonPathForm.setText("Road Network Path Form");
        jButtonPathForm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 152, 60), 4, true));
        jButtonPathForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathFormActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 177, 189));
        jLabel2.setText("Select");

        jLabel3.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 177, 189));
        jLabel3.setText("a");

        jLabel4.setFont(new java.awt.Font("SF Movie Poster", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 177, 189));
        jLabel4.setText("project");

        List<Project> projectsList = spc.fetchProjectsList();
        DefaultComboBoxModel<Project> projectsModel = new DefaultComboBoxModel<>();
        for (Project obj : projectsList) {
            projectsModel.addElement(obj);
        }
        jComboBox1.setModel(projectsModel);
        jComboBox1.setBackground(new java.awt.Color(204, 204, 204));
        /**
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        */
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButtonCompFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCompFormActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        WelcomeUI.main(null);
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonPathFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPathFormActionPerformed

        //ToDo Test only; Replace when 'select existing project' is available

        RoadNetwork roadNetworkTest = new RoadNetwork("1", "the road network");

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");
        Node nodeTest3 = new Node("n03");
        Node nodeTest4 = new Node("n04");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);
        roadNetworkTest.addNode(nodeTest3);
        roadNetworkTest.addNode(nodeTest4);

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Road roadTest1 = new Road("A01", "A01", "gantry toll highway");
        Road roadTest2 = new Road("A02", "A02", "toll highway", tollFaresRoadTest);
        Road roadTest3 = new Road("E01", "E01", "regular road");

        Segment segmentTest1 = new Segment(0, 250, 50, 2.5, 10, 2.5, 120, 50);
        Segment segmentTest3 = new Segment(0, 50, 250, 25, -30, 1.75, 90, 50);
        Segment segmentTest5 = new Segment(0, 250, 50, 50, 10, 2.5, 90, 0);

        List<Segment> segmentsTest1 = new ArrayList<>();
        segmentsTest1.add(segmentTest1);

        List<Segment> segmentsTest2 = new ArrayList<>();
        segmentsTest2.add(segmentTest3);

        List<Segment> segmentsTest3 = new ArrayList<>();
        segmentsTest3.add(segmentTest5);

        List<Double> tollFaresSectionTest = new ArrayList<>();
        tollFaresSectionTest.add(0.25);
        tollFaresSectionTest.add(0.35);
        tollFaresSectionTest.add(0.40);

        Section sectionTest1 = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest1, roadTest1, tollFaresSectionTest);
        Section sectionTest2 = new Section(nodeTest2, nodeTest3, Direction.BIDIRECTIONAL, segmentsTest2, roadTest2, new ArrayList<>());
        Section sectionTest3 = new Section(nodeTest3, nodeTest4, Direction.BIDIRECTIONAL, segmentsTest3, roadTest3, new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest1);
        roadNetworkTest.addSection(nodeTest2, nodeTest3, sectionTest2);
        roadNetworkTest.addSection(nodeTest3, nodeTest4, sectionTest3);

        List<VelocityLimit> velocityLimitListTest = new ArrayList<>();
        velocityLimitListTest.add(new VelocityLimit("highway", new Measurable(110, Unit.KILOMETERS_PER_HOUR)));
        velocityLimitListTest.add(new VelocityLimit("road", new Measurable(80, Unit.KILOMETERS_PER_HOUR)));

        List<Gears> gearsTest = new ArrayList<>();
        gearsTest.add(new Gears(1, 4.5f));
        gearsTest.add(new Gears(2, 3.5f));
        gearsTest.add(new Gears(3, 2.7f));
        gearsTest.add(new Gears(4, 1.6f));
        gearsTest.add(new Gears(5, 1.2f));
        gearsTest.add(new Gears(6, 0.9f));

        List<Regime> regimes25Test = new ArrayList<>();
        regimes25Test.add(new Regime(115, 125, 900, 1499, 500));
        regimes25Test.add(new Regime(125, 120, 1500, 2499, 450));
        regimes25Test.add(new Regime(120, 105, 2500, 3499, 520));
        regimes25Test.add(new Regime(105, 90, 3500, 4499, 550));
        regimes25Test.add(new Regime(90, 80, 4500, 5500, 650));

        List<Regime> regimes50Test = new ArrayList<>();
        regimes50Test.add(new Regime(185, 195, 900, 1499, 380));
        regimes50Test.add(new Regime(195, 190, 1500, 2499, 350));
        regimes50Test.add(new Regime(190, 180, 2500, 3499, 360));
        regimes50Test.add(new Regime(180, 150, 3500, 4499, 400));
        regimes50Test.add(new Regime(150, 135, 4500, 5500, 520));

        List<Regime> regimes100Test = new ArrayList<>();
        regimes100Test.add(new Regime(305, 325, 900, 1499, 380));
        regimes100Test.add(new Regime(325, 315, 1500, 2499, 350));
        regimes100Test.add(new Regime(315, 290, 2500, 3499, 360));
        regimes100Test.add(new Regime(290, 220, 3500, 4499, 400));
        regimes100Test.add(new Regime(220, 205, 4500, 5500, 520));

        List<Throttle> throttlesTest = new ArrayList<>();
        throttlesTest.add(new Throttle(25, regimes25Test));
        throttlesTest.add(new Throttle(50, regimes50Test));
        throttlesTest.add(new Throttle(100, regimes100Test));

        Vehicle vehicle1 = new Vehicle("Toyota", "Vehicle 1", VehicleType.Car, 1, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(2000, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0.320f, new Measurable(1.9, Unit.METER_SQUARED), 0.01f, new Measurable(0.6, Unit.METER),
                velocityLimitListTest, new Energy(900, 5500, 4f, gearsTest, throttlesTest));

        Vehicle vehicle2 = new Vehicle("BMW", "Vehicle 2", VehicleType.Car, 1, Vehicle.MotorType.NONCOMBUSTION, Fuel.Electric, new Measurable(2000, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0.320f, new Measurable(1.9, Unit.METER_SQUARED), 0.01f, new Measurable(0.6, Unit.METER),
                velocityLimitListTest, new Energy(900, 5500, 4f, gearsTest, throttlesTest));

        List<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicle1);
        vehiclesTest.add(vehicle2);

        Project project = new Project("New Project", "Add a description", roadNetworkTest, vehiclesTest);

        new BestPathUI(project);
        setVisible(false);
    }//GEN-LAST:event_jButtonPathFormActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        PopUp1 pop = new PopUp1((Project)(jComboBox1.getSelectedItem()));
        pop.setVisible(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(SelectProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
//       java.awt.EventQueue.invokeLater(() -> new SelectProjectUI(new SelectProjectController(null)).setVisible(true));
    }


}
