package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;

/**
 * This abstract class contains common properties of game objects like player,chips etc.
 *@author fatih
 *@version 1.0
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject { // Oyun objelerinin ortak özelliklerinden bir abstract class yaptık

	private int x; // oyun objelerinin ortak özellikleri
	private int y;
	private int radius;
	private Color color;

	/**
	 * 
	 * @param x horizontal coordinate of objects.
	 * @param y vertical coorinates of objects.
	 * @param radius radius of objects.
	 * @param color color of objects.
	 */
	public GameObject(int x, int y, int radius, Color color) { // class ın constructor kısmı.(kapsülleme için)
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	
	/**
	 * this method draws an oval object and sets its color.
	 * @param g2d object has inherited from Graphics 2D class.
	 */
	public void draw(Graphics2D g2d) {	//panelde çizim yapmak için bu metod yazıldı.

		g2d.setColor(getColor());
		g2d.fillOval(getX(), getY(), getRadius(), getRadius()); // iki kere getradius x ve y düzleminde iki ayrı yarıçap
	}
	
	/**
	 * 
	 * @return a rectangle which coordinates and radius given.
	 */
	public Rectangle getRectangle() {
		Rectangle rect= new Rectangle (getX(),getY(),getRadius(),getRadius());
		return rect;
	}

	public int getX() { // getter ve setter kısmı.
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
