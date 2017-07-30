package tr.org.linux.kamp.agarioClone.logic;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import tr.org.linux.kamp.agarioClone.model.Chip;
import tr.org.linux.kamp.agarioClone.model.GameObject;
import tr.org.linux.kamp.agarioClone.model.Player;
import tr.org.linux.kamp.agarioClone.view.GameFrame;
import tr.org.linux.kamp.agarioClone.view.GamePanel;

public class GameLogic {

	private Player player;
	private ArrayList<GameObject> gameObjects;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Random random;
	private boolean isGameRunning;

	private int xTarget; // x düzleminde gidilecek nokta. MouseListener da kullanılacak.
	private int yTarget; // y düzleminde gidilecek nokta.

	public GameLogic() { // elle constructor
		player = new Player(10, 10, 10, Color.BLUE, 3); // tüm fieldlar için hepsini new yapmak gerek.
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(player);
		gameFrame = new GameFrame();
		gamePanel = new GamePanel(gameObjects);

		random = new Random();
		fillChips(10);	//ilk etapta 10 adet üret. yenen yemin yenisi için tekrar bu metodu th
		addMouseEvents();

	}

	private void checkCollisions() {	//for içinde gameObjects listesinin elemanlarını döndürürken içinden eleman eksilttiğimiz için hata verdi.
										//çözüm: silinecekler listesi yap. döngü bitince sil. 
		
		ArrayList<GameObject> objectsToRemove = new ArrayList<GameObject>();
		
		for (GameObject gameObject : gameObjects) {// GameObject abstactından türeyen tüm gameObjectleri tara.
			if (player.getRectangle().intersects(gameObject.getRectangle())) { // player ın kare davranan hali
																				// gameObjectin kare davranan haliyle
																				// kesişirse
				if (gameObject instanceof Chip) { // instance bir nesnenin yaratılmış hali. gameObject Chip sınıfından
													// yaratılmış ise
					player.setRadius(player.getRadius() + gameObject.getRadius());// gameObjectin yarıçapı ve playerin
																					// yarıçapı toplamını playerin yarı
																					// çapı yap
																					// yani yediği yem kadar büyüt.
					//gameObjects.remove(gameObject); //check metodunun başındaki açıklamadan ötürü bunu sildik.
					
					objectsToRemove.add(gameObject);
				}
			}
		}gameObjects.removeAll(objectsToRemove);
		fillChips(objectsToRemove.size());  	//fillChips yeni yem üretir.parametre olarak 
												//silinecek yem sayısı yani yenen yem arraylistinin uzunluğu kadar.

	}

	public void startGame() { // yeni iş kolu açılması ve oyunun başlaması için.

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isGameRunning) {
					movePlayer(); // cismin fareyle hareket etmesi için yapılan movePlayer() metodunu çağırdık.
					gamePanel.repaint(); // iş kolu içinde görüntüyü yeniden çizmesi için repaint metodunu çağırıyoruz.
											// bu hazır metod.
					
					checkCollisions();	//kesişmeleri kontrol eden ve kesişirse yemi yiyip büyüyen metodu thread de çağırdık.
					
					try {
						Thread.sleep(30);

					} catch (InterruptedException e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}

			}
		}).start();
	}

	public void fillChips(int n) {
		for (int i = 0; i < n; i++) { // 10 tane üretiyorduk. nou iptal ettik. çünkü yenen yemin yerine yenisi gelsin istiyoruz. bunun için n tane dedik.
										//fillChips metodunu çağırdığımız yerde yenen yemin yerine yenen sayı kadar üret diyeceğiz.
		//for (int i = 0; i < 10; i++) {
			gameObjects.add(new Chip(random.nextInt(640), random.nextInt(480), 5, Color.ORANGE)); //10 tane 640a 480 piksel sınırları içinde 5 r yem üret.

		}
	}

	public void startApplication() {
		gameFrame.setContentPane(gamePanel);
		gameFrame.setVisible(true);
		isGameRunning = true;
		startGame(); // thread i başlatmak için burada startGame metodunu çağırdık. bunu yapmazsak
						// arka planda değerler değişir ama ekrana çizmez.

	}

	public void movePlayer() { // mouseMoved içinde yapılan if else leri buraya aldık. çünkü orada sadece fare
								// hareket edince player geziyordu.
								// şimdi bu metodu thread içinde çağıracağız.
								// xTarget ve yTarget aşağıda public void mouseMoved(MouseEvent e) metodundan
								// değer alıyor.

		if (xTarget > player.getX()) {
			player.setX(player.getX() + player.getSpeed()); // eğer player x'i hedeften küçükse player in x ini gelen x
															// değeri+ hız yap.
		} else if (xTarget < player.getX()) {
			player.setX(player.getX() - player.getSpeed()); // eğer farenin x'i player in x'inden gerideyse player ın x
															// ini x değeri-hiz yap.
		}

		if (yTarget > player.getY()) {
			player.setY(player.getY() + player.getSpeed()); // eğer player x'i hedeften küçükse player in x ini gelen x
															// değeri+ hız yap.
		} else if (yTarget < player.getY()) {
			player.setY(player.getY() - player.getSpeed()); // eğer farenin x'i player in x'inden gerideyse player ın x
															// ini x değeri-hiz yap.
		}
	}

	public void addMouseEvents() { // fare hareketleri için metod.
		gameFrame.addMouseListener(new MouseListener() { // gameFrame nesnesine fare dinleme

			@Override
			public void mouseReleased(MouseEvent arg0) { // tuşu bırakınca
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {// tuş basılıykenn
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) { // alandan çıktığında
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) { // frame alanına girdiğinde
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) { // tıklanınca
				// TODO Auto-generated method stub

			}
		});

		gamePanel.addMouseMotionListener(new MouseMotionListener() { // fare hareketi izleme.gamePanel yerine gameFrame olunca panel değil çerçeve  

			@Override
			public void mouseMoved(MouseEvent e) { // fare hareket ettiğinde
				// TODO Auto-generated method stub
				// bu bizim sorunumuza çözüm için kullanılacak en uygun method.

				xTarget = e.getX(); // yukarda tanımlanan xTarget içine fareden gelen x yatay düzlem bilgisini at.
				yTarget = e.getY(); // yukarda tanımlanan yTarget içine fareden gelen y düşey düzlem bilgisini at.
				/*
				 * if (xTarget>player.getX()) { //bu kısmı iptal ettik çünkü sadece fare hareket
				 * ettiğinde player hareket etti. bunun yerine //yukarda movePlayer() metoduna
				 * xTarget ve yTarget değerleriyle aynı if bloğunu yazdık. ve thread //içinde
				 * çağırdık. player.setX(player.getX()+player.getSpeed()); //eğer player x'i
				 * hedeften küçükse player in x ini gelen x değeri+ hız yap. } else
				 * if(xTarget<player.getX()) { player.setX(player.getX()-player.getSpeed());
				 * //eğer farenin x'i player in x'inden gerideyse player ın x ini x değeri-hiz
				 * yap. }
				 * 
				 * 
				 * if (yTarget>player.getY()) { player.setY(player.getY()+player.getSpeed());
				 * //eğer player x'i hedeften küçükse player in x ini gelen x değeri+ hız yap. }
				 * else if(yTarget<player.getY()) {
				 * player.setY(player.getY()-player.getSpeed()); //eğer farenin x'i player in
				 * x'inden gerideyse player ın x ini x değeri-hiz yap. } }
				 */
			}

			@Override
			public void mouseDragged(MouseEvent arg0) { // sürüklendiğinde
				// TODO Auto-generated method stub

			}
		});
	}

}
