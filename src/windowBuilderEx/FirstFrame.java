package windowBuilderEx;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FirstFrame extends JFrame {

	private FirstPanel contentPane;	//ismini FirstFrame olarak değişince burda da const. ismi olarak FirstFrame yapıyoruz.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	//thread vardı sildik.
		FirstFrame frame = new FirstFrame();

		frame.setVisible(true);
		frame.pack();//paketleyerek daha düzenli görüntü boşlukları alma.
	}

	/**
	 * Create the frame.
	 */
	public FirstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new FirstPanel();	//burda da contentPane i FirstPanelden türetmemiz gerekiyor.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//burdaki layout silindi.
		setContentPane(contentPane);
		setTitle("LYK JAVA");	//başlık
	}

}
