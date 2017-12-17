package lapr.project.ui;

import java.awt.*;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

	static final String SEGOE_FONT = "Segoe UI Semibold";
        static final Font FORTHYEIGHT_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 48);
	static final Font EIGHTEEN_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 18);
	static final Font FOURTEEN_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 14);
	static final Font TWELVE_SEGOE_FONT = new java.awt.Font(SEGOE_FONT, 0, 12);
	static final Font TV_POSTER_FONT = new java.awt.Font("SF Movie Poster", 0, 48);
	static final Color LIGHTEST_COLOR = new java.awt.Color(204,204,204);
	static final Color DARK_GREY = new java.awt.Color(45,46,45);
	static final Color DARK_BLUE = new java.awt.Color(97,122,133);
	static final Color DARK_ORANGE = new java.awt.Color(250,152,60);
	static final Color LIGHT_GRAY= new java.awt.Color(87,89,87);
	static final Color LIGHT_BLUE= new java.awt.Color(155,177,189);
	static final Color LIGHT_ORANGE= new java.awt.Color(2555,219,168);

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
		WelcomeUI.main(null);
	}

}
