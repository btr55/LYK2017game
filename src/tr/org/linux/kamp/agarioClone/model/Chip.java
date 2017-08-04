package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;


/**
 * Generates chips
 * @author fatih
 *@version 1.0
 */
public class Chip extends GameObject {

	
	/**
	 * 
	 * @param x horizontal coordinate of chips.
	 * @param y vertical coorinates of chips.
	 * @param radius radius of chips.
	 * @param color color of chips.
	 */
	public Chip(int x, int y, int radius, Color color) { // yemler için abstracttan ortak değerleri extend ettik
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
	}

}
