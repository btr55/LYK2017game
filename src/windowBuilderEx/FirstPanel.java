package windowBuilderEx;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import tr.org.linux.kamp.agarioClone.logic.GameLogic;
import tr.org.linux.kamp.agarioClone.model.Difficulty;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class FirstPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	
	private ButtonGroup buttonGroup;	//radio buttonların grup halinde uyumlu çalışması için buttongrup nesnesi yarattık.

	/**
	 * Create the panel.
	 */
	public FirstPanel() {
		
		JLabel lblUserName = new JLabel("User Name:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		JLabel lblSelectColor = new JLabel("Select Color:");
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setSelected(true);  //default olarak easy seçili gelir.
		
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		
		buttonGroup=new ButtonGroup();	//yeni bir buttonGroup nesnesi yarattık. constructor için.
		
		buttonGroup.add(rdbtnEasy);
		buttonGroup.add(rdbtnNormal);
		buttonGroup.add(rdbtnHard);	//3 radiobutton u da buttonGroup a ekledik. böylece aynı anda tek seçili. group tan sadece tek true çıkar.
		
		textField = new JTextField();	//userName textfield burası.
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();//comboBox burada yaratıldı.
		//karşılaştırma için == değil EQUALS kullan.
		comboBox.addItem("BLUE");	//combobox a içerik eklemek için.
		comboBox.addItem("RED");
		comboBox.addItem("GREEN");
		
//		comboBox.addItem(Color.BLUE);
//		comboBox.addItem(Color.RED);
//		comboBox.addItem(Color.GREEN);//bu şekilde yaparsak görünür kısımda garip kodlar halinde görünüyor.

		
		//BUTTON START
		
		//buton kodu   //start butona çift tıkladığımızda bu kod geliyor. 
		JButton btnStart = new JButton("Start");//btnsTart isimli nesne JButton sınıfından oluştu.
		btnStart.addActionListener(new ActionListener() {	//btnStart nesnesi ile JButton sınıfından addActionListener hazır metodu çağırıldı.
			public void actionPerformed(ActionEvent e) {	//basıldığında ne olacağı actionPerformed metoduna yazılacak.
				
				//BU KISIM COMBOBOX TAN RENK SEÇİMİ İÇİN:
				Color selectedColor = Color.BLACK;	//selectedcolor isimli renk nesnesi yarattık. 
				Color playerColor = Color.BLACK;
			switch(comboBox.getSelectedItem().toString())  {	//combobox tan gelen seçimi string e çevir ve aşağıdaki case'ler ile kıyasla.
				case "RED":
					playerColor=Color.RED;
					break;									//combobox tan seçilen menu itemi ile swtch case.
				case "BLUE":
					playerColor=Color.BLUE;
					break;
				case "GREEN":
					playerColor=Color.GREEN;
					break;

				default:
					break;
				}
				//BU KISIM RADIO BUTTON SEÇİMİ
			
			Difficulty difficulty=Difficulty.EASY;	//Difficulty enum sınıftan difficulty isimli nesne oluşturduk.
			
			if (rdbtnEasy.isSelected()) {  		//radiobutton grupta easy seçili ise
				
		difficulty=Difficulty.EASY;

			}
			else if(rdbtnNormal.isSelected()) {
				difficulty=Difficulty.EASY;
			}
				
			else if(rdbtnHard.isSelected()) {
				difficulty=Difficulty.HARD;
			}
			
			
				
				
				GameLogic gameLogic= new GameLogic(textField.getText(), playerColor,difficulty);	//GamLogic ten gameLogic isimli nesne oluşturduk.
				gameLogic.startApplication();			//nesnenin startApplication metodunu çağırdık.(startApp metodunu GameLogic te yazdık)
				
			
			}
		});
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {//ABOUT BUTONU
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(FirstPanel.this, "Bu yazılım GPL lisansı altındadır.", "About", JOptionPane.DEFAULT_OPTION);
											//First panel this: first panel üstünde açılsın. 
											//showConfirmDialog:Onaylanınca kapanan bir mesaj.
											//Başlık
											//JOption.DEFaçılınca çıkacak butona basınca olacak olay.
			}
		});
		
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAbout))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(8)
								.addComponent(lblDifficulty, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(8)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(rdbtnNormal)
									.addComponent(rdbtnEasy, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnHard)))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(8)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblSelectColor)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox, 0, 172, Short.MAX_VALUE))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))))
					.addGap(168))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectColor)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblDifficulty))
						.addComponent(rdbtnEasy))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnNormal)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnHard))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(btnStart)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAbout)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
