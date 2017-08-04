package tr.org.linux.kamp.agarioClone.logic;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import tr.org.linux.kamp.agarioClone.model.Chip;
import tr.org.linux.kamp.agarioClone.model.Difficulty;
import tr.org.linux.kamp.agarioClone.model.Enemy;
import tr.org.linux.kamp.agarioClone.model.GameObject;
import tr.org.linux.kamp.agarioClone.model.Mine;
import tr.org.linux.kamp.agarioClone.model.Player;
import tr.org.linux.kamp.agarioClone.view.GameFrame;
import tr.org.linux.kamp.agarioClone.view.GamePanel;


/**
 * Game mechanics.
 * @author fatih
 *version 1.0
 */
public class GameLogic {

	private Player player;
	private ArrayList<GameObject> gameObjects;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Random random;
	private boolean isGameRunning;
	private ArrayList<GameObject> chipsToRemove;// silinecek yemler .//objectsToMove dan minesToMove ve chipsToMove adlı
												// iki ayrı listeye döndük.
	private ArrayList<GameObject> minesToRemove;// silinecek mine'lar mayınlar.
	private ArrayList<GameObject> enemiesToRemove;// silinecek enemy
	
	private int xTarget; // x düzleminde gidilecek nokta. MouseListener da kullanılacak.
	private int yTarget; // y düzleminde gidilecek nokta.

	
	/**
	 * Generates all the game objects with name, color. Looks the difficulty for the number of objects.
	 * @param playerName player name from GameMenu.
	 * @param selectedColor color of player from GameMenu.
	 * @param difficulty difficulty of game from GameMenu.
	 */
	public GameLogic(String playerName,Color selectedColor, Difficulty difficulty) {// elle constructor
		player = new Player(10, 10, 10, selectedColor, 3,playerName);// tüm fieldlar için hepsini new yapmak gerek.
										//burda color.blue vardı. menuden seçilen renk için parametreyi selected color yaptık.
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(player);
		gameFrame = new GameFrame();
		gamePanel = new GamePanel(gameObjects);
		minesToRemove = new ArrayList<GameObject>(); // objectsToMove dan minesToMove ve chipsToMove adlı iki ayrı
														// listeye döndük.
		chipsToRemove = new ArrayList<GameObject>();
		enemiesToRemove = new ArrayList<GameObject>();

		random = new Random();
//ZORLUK EKLEME
		switch (difficulty) {//zorluk seçimine bak
		case EASY:
			fillChips(15); // kolay modda türetilecek materyaller

			fillMines(5);

			fillEnemies(3);

			addMouseEvents(); // fare dinleyici ekledik.

			break;
		case NORMAL:
			
			fillChips(20); // normal modda türetilecek materyaller

			fillMines(10);

			fillEnemies(5);

			addMouseEvents(); // fare dinleyici ekledik.

			break;
		case HARD:
			fillChips(10); //zor modda türetilecek materyaller

			fillMines(20);

			fillEnemies(10);

			addMouseEvents(); // fare dinleyici ekledik.

	
			break;

		default:
			break;
		}
		
//		fillChips(20); // ilk etapta 10 adet üret. yenen yemin yenisi için tekrar bu metodu th
//
//		fillMines(10);
//
//		fillEnemies(5);
//
//		addMouseEvents(); // fare dinleyici ekledik.

	}

	
	/**
	 * Checks the collisions between two objects and decides what will happen 
	 */
	private void checkCollisions() { // for içinde gameObjects listesinin elemanlarını döndürürken içinden eleman
										// eksilttiğimiz için hata verdi.
										// çözüm: silinecekler listesi yap. döngü bitince sil.
										// bir metod bir iş yapmalı. burda çarpışma kontrolü ismini vermişiz.

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

					// gameObjects.remove(gameObject); //check metodunun başındaki açıklamadan ötürü
					// bunu sildik.

					chipsToRemove.add(gameObject);
				}

				// MAYINLAR İÇİN
				if (gameObject instanceof Mine) {// çakışma mayınla ise

					player.setRadius((int) player.getRadius() / 2); // player yarıçapının yarısına indir yeni yarıçap
																	// yap. integera cast et.

					minesToRemove.add(gameObject); // mayınları silinecekler listesine ekle

				}
				if (gameObject instanceof Enemy) {
					if (player.getRadius() > gameObject.getRadius()) {
						player.setRadius( player.getRadius() + gameObject.getRadius());
						enemiesToRemove.add(gameObject);
						
					} else if (player.getRadius() < gameObject.getRadius()) {

						gameObject.setRadius(gameObject.getRadius() + player.getRadius());
						// Game OVER
						isGameRunning=false;

					}
				}

			}

			// enemy ler için chiple büyüme

			if (gameObject instanceof Enemy) { // çarpılan cisim

				Enemy enemy = (Enemy) gameObject;

				for (GameObject gameObject2 : gameObjects) {

					if (enemy.getRectangle().intersects(gameObject2.getRectangle())) {
						if (gameObject2 instanceof Chip) {
							enemy.setRadius(enemy.getRadius() + gameObject2.getRadius());
							chipsToRemove.add(gameObject2);
						}
						if (gameObject2 instanceof Mine) {
							enemy.setRadius((int) enemy.getRadius() / 2);
							minesToRemove.add(gameObject2);
						}

					}
				}

			}

		}

		gameObjects.removeAll(chipsToRemove);// gameObjects içinden chipssToRemove listesinin tümünü sil.
		gameObjects.removeAll(minesToRemove);// gameObjects içinden minesToRemove listesinin tümünü sil.
		gameObjects.removeAll(enemiesToRemove);	//gameObjects içinden enemiesToRemove listesinin tümümü sil.

		// fillChips(objectsToRemove.size()); //fillChips yeni yem üretir.parametre
		// olarak
		// silinecek yem sayısı yani yenen yem arraylistinin uzunluğu kadar.

		// yukarıdaki metodu aşağıda addNewObjects içine aldık. obje çeşidi arttığında
		// objectsToRemove aynı anda birden fazla
		// yerden çağırılabilir ve çakışma olabilirdi. aşağıdaki metodla bunu da
		// aşıyoruz.

	}

	
	/**
	 * Generate a new object when another one deleted.
	 */
	private synchronized void addNewObjects() { // yok olan objelerin yerine yenisi getirilirken çakışma olmasın.
												// çakışma:aynı anda farklı threadlerden objectsToremove verisi üzerinde
												// işlem
												// yapmak istediğinde synchronize deyimi ile çakışma engellenir.

		fillChips(chipsToRemove.size()); // yeni yem ekleme metodunu çağırdık.

		fillMines(minesToRemove.size()); // yeni mayın ekleme metodunu çağırdık.

		fillEnemies(enemiesToRemove.size());

		chipsToRemove.clear(); // iş bittikten sonra boştaki referansları temizlemek için. listedekileri
								// silince liste referansı boşta kalır.
		minesToRemove.clear(); // listeyi temizlemeyip birakirsak liste son müdahale eklemiş halde kalir.
								// ikinci çarpışmada iki nesne üretir.
		enemiesToRemove.clear();

	}
	
	/**
	 * calls the movement methods, checking method,adding objects and repaint method to start game.
	 */

	public void startGame() { // yeni iş kolu açılması ve oyunun başlaması için.

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isGameRunning) {
					movePlayer(); // cismin fareyle hareket etmesi için yapılan movePlayer() metodunu çağırdık.

					moveEnemies();

					addNewObjects(); // yukarda tanımladığımız synchrozed metodu thread de çağırdık.

					checkCollisions(); // kesişmeleri kontrol eden ve kesişirse yemi yiyip büyüyen metodu thread de
										// çağırdık.

					gamePanel.repaint(); // iş kolu içinde görüntüyü yeniden çizmesi için repaint metodunu çağırıyoruz.
					// bu hazır metod.

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

	/**
	 * Generates chips 
	 * @param n number of chips
	 */
	public void fillChips(int n) {
		for (int i = 0; i < n; i++) { // 10 tane üretiyorduk. onu iptal ettik. çünkü yenen yemin yerine yenisi gelsin
										// istiyoruz. bunun için n tane dedik.
										// fillChips metodunu çağırdığımız yerde yenen yemin yerine yenen sayı kadar
										// üret diyeceğiz.
			// for (int i = 0; i < 10; i++) { 10 a kadar saydırmaktan n'e kadar saydırmaya
			// döndük. bu satır iptal.
			gameObjects.add(new Chip(random.nextInt(640), random.nextInt(480), 10, Color.ORANGE)); // 10 tane 640a 480
																									// piksel sınırları
																									// içinde 5 r yem
																									// üret.

		}
	}

	/**
	 * Generates mines 
	 * @param n number of mines
	 */
	public void fillMines(int n) { //

		for (int i = 0; i < n; i++) {

			Mine mine = new Mine(random.nextInt(640), random.nextInt(480), 10, Color.GREEN);

			while (player.getRectangle().intersects(mine.getRectangle())) { // player ve mine kesişirse

				mine.setX(random.nextInt(640));
				mine.setY(random.nextInt(480));

			}
			gameObjects.add(mine);
		}

	}

	
	/**
	 * Generates enemies 
	 * @param n number of enemies
	 */
	private void fillEnemies(int n) { // ENEMY PARAMETRE VE CIKACAĞI YER.

		for (int i = 0; i < n; i++) {

			Enemy enemy = new Enemy(random.nextInt(640), random.nextInt(480), random.nextInt((10) + 10), 1, Color.RED); // Enemy
																														// sınıfından
																														// enemy
																														// nesnesi
																														// yaptık.
			// bu nesnenin random ekranın herhangi bir yerinde çıkmasını sağladık.
			// büyüklüğünü 5 ile 15 arası random yaptık. renk kırmızı
			while (player.getRectangle().intersects(enemy.getRectangle())) {
				enemy.setX(random.nextInt(640));
				enemy.setY(random.nextInt(480));
			}

			gameObjects.add(enemy);

		}

	}

	/**
	 * Game Panel settings.
	 */
	public void startApplication() {
		gameFrame.setContentPane(gamePanel);
		gameFrame.setVisible(true);
		isGameRunning = true;
		startGame(); // thread i başlatmak için burada startGame metodunu çağırdık. bunu yapmazsak
						// arka planda değerler değişir ama ekrana çizmez.

	}

	
	/**
	 * settings of player movements.
	 */
	private synchronized/* çakışma engellemek için synchronized */ void movePlayer() { // mouseMoved içinde yapılan
		// if else leri buraya aldık. çünkü orada sadece fare
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

	
	/**
	 * settings of enemy movements.
	 */
	private synchronized void moveEnemies() {

		for (GameObject gameObject : gameObjects) {// GameObject arraylistesinden türeliten gameObject nesnesinin içinde

			if (gameObject instanceof Enemy) { // gameObject listesi içinde Enemy sınıf türüne cast et. Bunu cast etmesi
												// için yukarıdaki if ten geçmesi gerek
												// diğer türlü cast etmezdi zaten. bunu cast etmemizin sebebi Enemy
												// türüne dönsün ki getSpeed yapabilelim.

				Enemy enemy = (Enemy) /* cast */ gameObject;// gameObject if ten geçtiği için zaten Enemy sınıfına ait.
															// ama parametreleri bir üst sınıftan. ondan cast ettik.
															// artık Enemy class tüm parametrelerini
															// bu nesne üzerinden çağırabiliriz.

				if (enemy.getRadius() < player.getRadius()) {// kaçacak
					// enemy boyutunu player boyutu ile kıyasla ve player büyükse

					int distance = (int) Point.distance(player.getX(), player.getY(), enemy.getX(), enemy.getY());// Point.distance
																													// iki
																													// nokta
																													// arası
																													// uzaklık
																													// bulur
					// distance değişkenine player ve enemy arasındaki mesafeyi ata.

					int newX = enemy.getX() + enemy.getSpeed(); // burada düşmanın x durumu ve hızını(hızına adım atması
																// için
																// bakıyoruz) toplayıp yeniX değişkenine at. hız kaçsa o
																// kadar adım atar.
					int newY = enemy.getY() + enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) { // player enemy arası yeni mesafe
																					// eskisinden büyükse

						continue; // ????
					}
					newX = enemy.getX() + enemy.getSpeed(); // yeniX değişkenini enemynin x durumu ve hızıyla doldur. x
															// durumu yaklaştı mı uzaklaştı mı
															// bilgisi için.

					newY = enemy.getY() - enemy.getSpeed(); // yeniY değişkenini enemynin y durumu ve hızıyla doldur.
															// hızı adım atması için

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}
					newX = enemy.getX() - enemy.getSpeed(); // burada düşmanın x durumu ve hızını(hızına yönü için
															// bakıyoruz) topladık.
					newY = enemy.getY() + enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}
					newX = enemy.getX() - enemy.getSpeed(); // burada düşmanın x durumu ve hızını(hızına yönü için
															// bakıyoruz) topladık.
					newY = enemy.getY() - enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}
					newX = enemy.getX() + enemy.getSpeed(); // burada düşmanın x durumu ve hızını(hızına yönü için
															// bakıyoruz) topladık.
					newY = enemy.getY() + enemy.getSpeed();

					if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {

						continue;
					}

				} else { // movePlayer metodunu revize ettik.

					if (player.getX() > enemy.getX()) {
						enemy.setX(enemy.getX() + enemy.getSpeed()); // eğer player x'i hedeften küçükse player in x ini
																		// gelen x
																		// değeri+ hız yap.
					} else if (player.getX() < enemy.getX()) {
						enemy.setX(enemy.getX() - enemy.getSpeed()); // eğer farenin x'i player in x'inden gerideyse
																		// player ın x
																		// ini x değeri-hiz yap.
					}

					if (player.getY() > enemy.getY()) {
						enemy.setY(enemy.getY() + enemy.getSpeed()); // eğer player x'i hedeften küçükse player in x ini
																		// gelen x
																		// değeri+ hız yap.
					} else if (player.getY() < enemy.getY()) {
						enemy.setY(enemy.getY() - enemy.getSpeed()); // eğer farenin x'i player in x'inden gerideyse
																		// player ın x
																		// ini x değeri-hiz yap.
					}

				}
			}

		}

	}

	/**
	 * calculates the distance with enemy and player.
	 * @param enemy enemy object with coordinates
	 * @param distance distance between player and enemy which calculated with Point class.
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 * @return	returns true if distance increased.
	 */
	private boolean calculateNewDistanceToEnemy(Enemy enemy, int distance, int x, int y) {// burada enemy nesnesi mesafe
																							// x ye y bilgileri
																							// parametre alındı
		int newDistance = (int) Point.distance(player.getX(), player.getY(), x, y); // Yeni mesafeyi ölç ve newDistance
																					// içine at.
		if (newDistance > distance) { // eğer yeni mesafe eskisinden daha büyükse
			enemy.setX(x); // enemy nin x ini parametre x ile değiştir.
			enemy.setY(y);
			return true; // true döndür.
		}
		return false;
	}

	/**
	 * Listenes mouse movements 
	 */
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

		gamePanel.addMouseMotionListener(new MouseMotionListener() { // fare hareketi izleme.gamePanel yerine gameFrame
																		// olunca panel değil çerçeve

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
