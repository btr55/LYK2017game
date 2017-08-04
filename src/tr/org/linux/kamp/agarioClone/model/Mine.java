package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;

/**
 * Generates mines.
 * @author fatih
 *@version 1.0
 */
public class Mine extends GameObject {

	
	/**
	 * 
	 * @param x horizontal coordinate of mines.
	 * @param y vertical coorinates of mines.
	 * @param radius radius of mines.
	 * @param color color of mines.
	 */
	public Mine(int x, int y, int radius, Color color) { // ortada serbest dolaşan mayınlar için abstracttan extend
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
	}

}
