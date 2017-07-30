package tr.org.linux.kamp.graphicFirst;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelExample extends JPanel{
	
	private JLabel centerLabel;
	
	private JLabel northLabel;
	
	private JLabel southLabel;
	
	private JLabel eastLabel;
	
	private JLabel westLabel;
	

	private JLabel label;	//JLabel sınıfından bir label değişken yaptık.
	
	public JPanelExample() {	//constructor yazıyoruz.
		
		setBackground(Color.DARK_GRAY);	//arka plan rengi hazır metodunu cagırıp parametre olarak Color hazır sınıfından BLUE cagirdik.
		
		setLayout(new BorderLayout());   	//BorderLaoyut default ayarlarını çağırdık.
		
		label= new JLabel("Hello World");	//label değişkeni içini doldurduk
		
		//FlowLayout  ortaya yazdırıyor. default bu geliyor.
		
		label.setForeground(Color.WHITE);	//rengini renk sınıfından beyaz çağırıp yaptık.
		
		label.setBounds(5,5,200,200);		//ilk iki beş içteki çerçevenin sol üst koordinat x y si 200 ler dıştaki çerçevenin yük.gen.
		
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//kenarlık oluşturuldu. 
		
		//help menusu MarketPlace te hazır layout var. burdan window builder kuruyoruz.
	
		centerLabel=new WhiteLabel("Center");
		
		northLabel=new WhiteLabel("North Remembers");
		
		southLabel= new WhiteLabel("South");
		
		eastLabel=new WhiteLabel("East");
		
		westLabel=new WhiteLabel("west");
		
		
		add(centerLabel,BorderLayout.CENTER);
		
		add(northLabel,BorderLayout.NORTH);
		
		add(southLabel,BorderLayout.SOUTH);
		
		add(eastLabel,BorderLayout.EAST);
		
		add(westLabel,BorderLayout.WEST);
		
		
		

	}
	
}
