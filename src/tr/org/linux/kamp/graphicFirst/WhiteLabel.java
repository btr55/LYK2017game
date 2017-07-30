package tr.org.linux.kamp.graphicFirst;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WhiteLabel extends JLabel{	//beyaz etiket için sınıf yaptık. buna javanın JLabel sınıfını extend ettik.
	
	public WhiteLabel (String text) {
		
		super (text);	//zaten text isimli JLabel da var. super ile onu getirdik.
		
		setForeground(Color.WHITE);		//yazı beyaz
		
		setBorder(BorderFactory.createLineBorder(Color.WHITE,1));	//çerçeve Fab classtan çizgi metoduyla
																	//beyaz renkli 1 birimlik çizgi çiz
		
		setVerticalAlignment(SwingConstants.CENTER);	//düşey hizalama 
		
		setHorizontalTextPosition(SwingConstants.CENTER);	//yatayda hizalama
		
		
		
		
		
		
	}

}
