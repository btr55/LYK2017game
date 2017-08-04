package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;

/**
 * Generates enemies. Inherited from GameObjects class and has been added a speed property.
 * @author fatih
 *@version 1.0
 */
public class Enemy extends GameObject {

	private int speed;

	/**
	 * 
	 * @param x horizontal coordinate of enemies.
	 * @param y vertical coorinates of enemies.
	 * @param radius radius of enemies.
	 * @param color color of enemies.
	 * @param speed
	 */
	public Enemy(int x, int y, int radius,int speed, Color color) {
		super(x, y, radius, color);
		// TODO Auto-generated constructor stub
		this.speed = speed;
	}
	/**
	 * this method can change the radius for difficulty settings.
	 */
	//ZORLUK AYARI İÇİN BÜYÜKLÜK
		@Override
		public void setRadius(int radius) {
			// TODO Auto-generated method stub
			super.setRadius(radius);
			
			//son eklenen
			if(getRadius()<5) {	//eğer düşman 5 ten küçük olursa 5 e çek.
				setRadius(5);
			}
			else if(getRadius()>250) {	//eğer düşman 250 den büyük olursa 250 ye düşür.
				setRadius(250);
			}
			
			
		}

	public int getSpeed() { // getter ve setter. speed için
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
