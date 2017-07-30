package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;

public class Enemy extends GameObject {

	private int speed;

	public Enemy(int x, int y, int radius, Color color, int speed) {
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		this.speed = speed;
	}

	public int getSpeed() { // getter ve setter. speed için
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
