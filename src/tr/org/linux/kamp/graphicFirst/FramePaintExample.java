package tr.org.linux.kamp.graphicFirst;

import javax.swing.JFrame;

public class FramePaintExample {
	
	public static void main(String[] args) {
		
		JFrame frame= new JFrame();
		
		frame.setTitle("Paint Example");
		
		frame.setSize(600, 480);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		PaintPanel paintPanel = new PaintPanel();
		
		frame.setContentPane(paintPanel);		//frame nesnesini PaintpanelClasstan oluşturduğumuz paintPanel nesnesini çizdik.
		
	}

}
