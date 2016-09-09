package Sudoku;

import java.awt.Point;
import java.awt.Toolkit;

/**
 * !!! DO NOT FUCKING TOUCH UNLESS YOU KNOW WHAT YOU ARE DOING !!!
 * THIS IS NOT A FUCKING GAME...
 * 
 * @author Julian
 * @version 0.2
 */

public class CONSTANTS {
	// Project Constants
	public static String VERSION 			= "0.2";
	public static int HALF_SCREEN_HEIGHT	= (Toolkit.getDefaultToolkit().getScreenSize().height / 2);
	public static int HALF_SCREEN_WIDTH 	= (Toolkit.getDefaultToolkit().getScreenSize().width  / 2);
	
	// Menu Frame Constants
	public static int MENU_HEIGHT			= 400;
	public static int MENU_WIDTH 			= 300;
	
	// Game Frame Constants
	public static int GAME_HEIGHT			= 900;
	public static int GAME_WIDTH			= 900;
	
	// Element Texts
	public static String START_BTN 			= "Starte neues Spiel";
	public static String DARW_BTN 			= "Sudoku entwerfen";
	public static String SETTINGS_BTN 		= "Einstellungen";
	public static String EXIT_BTN 			= "Beenden";
	
	// Method Constants - sounds stupid, I know... But who the fuck cares ???
	public static String getTitle(){
		return "Sudoku-Master - " + VERSION;
	}
	public static Point getMenuLocation(){
		return new Point(
				HALF_SCREEN_WIDTH  - (MENU_WIDTH  / 2), 
				HALF_SCREEN_HEIGHT - (MENU_HEIGHT / 2)
		);
	}
	public static Point getGameLocation(){
		return new Point(
				HALF_SCREEN_WIDTH  - (GAME_WIDTH  / 2), 
				HALF_SCREEN_HEIGHT - (GAME_HEIGHT / 2)
		);
	}
}
