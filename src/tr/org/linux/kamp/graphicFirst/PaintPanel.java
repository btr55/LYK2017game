package tr.org.linux.kamp.graphicFirst;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	static boolean isTrue=true;
	
	private Box box;
	
	private Circle circle;
	
	
	public PaintPanel() {
		
		box= new Box(Color.BLACK);
		circle = new Circle(Color.GREEN);
		System.out.println("kutu yaratıldı");
		startMovement();
		//startMovementWithoutThread();
		
	}
	@Override
	protected void paintComponent(Graphics g) {		//ekrana çizme standart metodunu override edip
													//kendi istediğimiz özelliklerle repaint etmesini sağladık.
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;	//Grapics2D kütüphanesinden g2d isimli nesne.
		
		g2d.setColor(Color.CYAN);		//rengi color sınıfından cyan olan g2d nesnesi çiz.
		
		//g2d.fillRect(10, 10, 250, 100);  //yukarda yaptığımız değişiklikleri çiz doldur komutu. bunu silersen yukarısı gerçekleşmez
		
										//Rect rectangular dikdortgen
		
		//g2d.fillOval(200, 200, 100, 100);	//200e200 noktası merkezli 100 e 100 çember çiz.
		
		g2d.fillRect(box.getX(), box.getY(), box.getHeight(), box.getWidth());
		
		g2d.setColor(circle.getColor());
		
		g2d.fillOval(circle.getX(), circle.getY(), circle.getRadius(), circle.getRadius());
		}
	
	public void startMovementWithoutThread() {

		while(true) {	//sonsuz döngüde
			
			System.out.println("kutu şu anda"+box.getX()+"pozisyonunda");
			
		}
		
	}
	private void startMovement() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(isTrue) {
					box.setX(box.getX()+5);	//kutunun x pozisyonunu beşer artır.
					repaint();		//bunu iptal edersek kutunun x poziyonu yine 5er ilerler. ama ekranda görüntüsü olmaz.
					try {
						Thread.sleep(200);	//1 sn. bekleterek iş parçacığını çalıştır.
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
		
			
				
			}
		}).start();
	}
	}
