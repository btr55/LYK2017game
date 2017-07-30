package tr.org.linux.kamp.threadExample;

public class ThreadExample {

	public static void main(String[] args) {

		new Thread(new Runnable() {		//yeni bir iş parçacığı oluştur.

			@Override
			public void run() {		//Runnable class tan run metodu override ediyor.
				System.out.println("0 çalıştı");	//metodu ekran çıktısı verecek şekilde değiştik.
			}
		}).start();		//iş parçacığını başlat. bunu demezsen bi iş yapmaz.

		System.out.println("1.çalıştı");		//metod dışına main içine yazdık. önce bu yazar.
												//start görünce ikinci iş kolunu açar. ama yarım saniye bekle
												//dendiği için 1. daha önce çalışır.

		try {
			Thread.sleep(1500);	//buradan önceki iş parçacığını yarım saniye beklet.

		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		System.out.println("2.çalıştı");
		System.out.println("3.çalıştı");

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("4 çalıştı");

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("5 çalıştı");

			}
		}).start();
	}

}
