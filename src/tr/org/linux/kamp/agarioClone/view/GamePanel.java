package tr.org.linux.kamp.agarioClone.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import tr.org.linux.kamp.agarioClone.model.GameObject;

public class GamePanel extends JPanel {	//panel soyut birşey. sınır koymadık. çerçeve ile sınırlı. üzerine listelerden gameObject yarattık. 

	private ArrayList<GameObject> gameObjects; // gameObjects isimli bir Arraylist oluşturduk.

	public GamePanel(ArrayList<GameObject> gameObjects) {//constructor parametresi gameObjects isimli arraylist
		// TODO Auto-generated constructor stub

		this.gameObjects = gameObjects; //parametreden gelen gameObjects değerini arrayliste attık. getter gibi.ama tam değil.
	}

	@Override
	public void paintComponent(Graphics g) {	//gameComponents olmayacak. s çok önemli. paintComponent üst metodunu ovveride edip istediğimiz
												//2D şekilleri boyattmak için düzenledik.
		// TODO Auto-generated method stub

		super.paintComponent(g);	//paintComponent parametresine g nesnesi çizdirdik.

		Graphics2D g2d = (Graphics2D) g; // g nesnesini grafik 2Dye cast ediyor. o şekilde g2d nesnesine atıyor.

		for (GameObject gameObject : gameObjects) { // foreach yaz ctrl space. her bir arraylist elemanlarını sayacak.
			gameObject.draw(g2d); //bu satırın çalışması için abstarcta  bir draw metodu yaz. 
		}
	}
}
