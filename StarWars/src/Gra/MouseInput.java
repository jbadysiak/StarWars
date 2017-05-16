package Gra;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/** Klasa MouseInput ma zaimplementowane metody za³¹czone w MouseListener,
 *  odpowiada za obs³ugê myszki 
 *
 */
public class MouseInput implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	/** Metoda pobiera wspó³rzêdne miejsca klikniêcia oraz
	 * na tej podstawie rozpoznaje jaka opcja zosta³a wybrana
	 */
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		

		/**
		 * Przycisk Play na ekranie
		 */
		if(mx >= Game.WIDTH/2 + 120 && mx <= Game.WIDTH/2 +220){
			if(my >= 150 && my <= 200){
				Game.State = Game.STATE.GAME;
			}
		}
		/**
		 * Przycisk Quit na ekranie
		 */
		if(mx >= Game.WIDTH/2 + 120 && mx <= Game.WIDTH/2 +220){
			if(my >= 350 && my <= 400){
				Game.State = Game.STATE.GAME;
				System.exit(1);
			}
		}
	}

	public void mouseReleased(MouseEvent arg0) {}
	
}
