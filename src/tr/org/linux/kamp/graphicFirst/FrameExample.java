package tr.org.linux.kamp.graphicFirst;

import javax.swing.JFrame;

public class FrameExample {
	
	

	public static void main(String[] args) {
		
		JPanelExample jPanel =new JPanelExample();
		
		
		JFrame frame= new JFrame();  //JFrame class ından frame isimli nesne yaptık.
		
		frame.setVisible(true); //görülebilir kıl. true evet false hayır.
		
		frame.setSize(640,480);	//ekran büyüklüğünü piksel en boy oranıyla belirle.
		
		frame.setLocation(400, 400); //açılacağı ekran konumu. piksel cinsinden.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kapandığında varsayılan olarak programdan çık.
		
		frame.setResizable(false); //sayfa büyütüp küçültme. false ise büyümez küçülmez.
		
		frame.setContentPane(jPanel);	//JPanelExample class tan bir jPanel nesne oluşturup onu contentPane yaptık.
		
		frame.setTitle("Java Rocks");
		
		frame.setContentPane(jPanel);	//tek başına anlamı yok. paneli çizmek için frame olması gerek.
		
		jPanel.repaint();
		
		
		
		
		
		
	}

}
