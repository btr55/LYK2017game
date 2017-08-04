package tr.org.linux.kamp.agarioClone.model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * This class determines the player properties like speed, name, color etc. Inherited from GameObject class.
 * @author fatih
 *@version 1.0
 */

public class Player extends GameObject {

	private int speed; // Player ve enemy için abstract tan gelmeyen spesifik bir hız değeri gerek.
	private String playerName; //formda textfield dan isim almak için son gün ekledik.
	private Color selectedColor;
	
	
	/**
	 * 
	 * @param x horizontal coordinate of player.
	 * @param y vertical coorinates of player.
	 * @param radius radius of player.
	 * @param color color of player.
	 * @param speed speed of player
	 * @param playerName name of player.
	 */
	public Player(int x, int y, int radius, Color color,int speed,String playerName/*son gün bunu ekledik*/) { // parametrelere speed ekledik
		super(x, y, radius, color);
		this.playerName=playerName;	//son gün bunu ekledik
		// TODO Auto-generated constructor stub
		 // parametre speed değerini alıp private olan speed değerinee attık.
		this.speed=speed;
		
	}
	
	/**
	 * this method can change the radius.
	 */

	//ZORLUK AYARI İÇİN BÜYÜKLÜK
	@Override
	public void setRadius(int radius) {
		// TODO Auto-generated method stub
		super.setRadius(radius);
		
		//son eklenen
		if(getRadius()<5) {	//eğer oyuncumuz 5 ten küçük olursa 5 e çek.
			setRadius(5);
		}
		else if(getRadius()>250) {	//eğer oyuncumuz 250 den büyük olursa 250 ye düşür.
			setRadius(250);
		}
		
		
	}
	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) { // hız değişikliği için getter ve setter yaptık. player nesnesi çağırılınca, sonradan çağırılır.
		this.speed = speed;
	}
	/**
	 * draws player with given properties.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		
		super.draw(g2d);
		FontMetrics fontMetrics = g2d.getFontMetrics(g2d.getFont());
		int width =fontMetrics.stringWidth(playerName);
		int nameX=getX()+(getRadius()-width)/2;		
		int nameY=getY()-fontMetrics.getHeight();
		g2d.drawString(playerName, nameX, nameY);
	}

}
