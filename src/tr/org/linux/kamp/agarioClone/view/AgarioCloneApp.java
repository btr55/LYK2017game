package tr.org.linux.kamp.agarioClone.view;

import java.awt.Color;

import tr.org.linux.kamp.agarioClone.logic.GameLogic;
import tr.org.linux.kamp.agarioClone.model.Difficulty;

/**
 * starts the application.
 * @author fatih
 *@version 1.0
 */
public class AgarioCloneApp {

	public static void main(String[] args) {

		GameLogic gameLogic = new GameLogic("Güray",Color.BLACK,Difficulty.EASY);	//bunu da son gün ekledik. gamelogic te paratmre ekledik boş kalmasın hata verir.
		gameLogic.startApplication();
	}
}
