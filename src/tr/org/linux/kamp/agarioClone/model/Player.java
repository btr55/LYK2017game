package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;

public class Player extends GameObject {

	private int speed; // Player ve enemy için abstract tan gelmeyen spesifik bir hız değeri gerek.

	public Player(int x, int y, int radius, Color color,int speed) { // parametrelere speed ekledik
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		 // parametre speed değerini alıp private olan speed değerinee attık.
		this.speed=speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) { // hız değişikliği için getter ve setter yaptık. player nesnesi çağırılınca, sonradan çağırılır.
		this.speed = speed;
	}

}
