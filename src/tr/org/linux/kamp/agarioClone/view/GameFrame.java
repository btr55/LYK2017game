package tr.org.linux.kamp.agarioClone.view;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public GameFrame() {	//constructor
		setTitle("Agario Clone");	//başlık
		setResizable(true);		//yeniden boyutlandırılabilir mi 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//kapandığında çık
		setSize(640,480);	//en boy pencerenin
		setLocationRelativeTo(null);	//ortada başlat.
		
		
	}
}
