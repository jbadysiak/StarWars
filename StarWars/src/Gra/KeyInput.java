package Gra;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Klasa KeyInput rozszerza klasê KeyAdapter, odpowiada za 
 * zbieranie informacji podawanych na klawiaturê
 */
public class KeyInput extends KeyAdapter {
	/** Instancja klasy Game */
	Game game;
	
	/** Konstruktor klasy KeyTnput */
	public KeyInput(Game game){
		this.game = game;
	}
	
	/** Zebranie informacji jaki klawisz zosta³ naciœniêty */
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}
	/** Otrzymanie komunikatu o puszczeniu przez gracza przycisku */
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}
}
