package Sudoku;

import java.awt.Toolkit;

/**
 * !!! DO NOT FUCKING TOUCH UNLESS YOU KNOW WHAT YOU ARE DOING !!!
 * THIS IS NOT A FUCKING GAME...
 * 
 * @author Julian
 * @version 0.1
 */

public class CONSTANTS {
	// Frame Constants
	public static String VERSION 			= "0.1";
	public static int HEIGHT 				= 400;
	public static int WIDTH 				= 300;
	
	public static int LOCATION_HEIGHT 		= (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (HEIGHT / 2); 	// Trust me it's working... DO NOT TOUCH!!!
	public static int LOCATION_WIDTH 		= (Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (WIDTH  / 2);	// Trust me it's working... DO NOT TOUCH!!!
	
	// Element Texts
	public static String START_BTN 			= "Starte neues Spiel";
	public static String DARW_BTN 			= "Sudoku entwerfen";
	public static String SETTINGS_BTN 		= "Einstellungen";
	public static String EXIT_BTN 			= "Beenden";
	
	public static String getTitle(){
		return "Sudoku-Master - " + VERSION;
	}
}
