package lapr.project.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

	/**
	 * Logger class.
	 */
	private static final Logger LOGGER = Logger.getLogger("MainLog");

	/**
	 * Private constructor to hide implicit public one.
	 */
	private Main() {

	}

	/**
	 * Application main method.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		setLookAndFeel();
		new WelcomeUI().setVisible(true);
	}

	/**
	 * Sets the proper visuals to the UIManager
	 * The contents of this method were copied from the automatically generated code created by Java's Swing tools.
	 */
	private static void setLookAndFeel(){
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
	}
}
