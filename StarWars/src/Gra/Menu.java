package Gra;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/** Klasa Menu odpowiada za poprawne zbudowanie okna Menu 
 * 
 */
public class Menu {
	
	/** Deklaracja przycisku "Play" */
	public Rectangle playButton = new Rectangle(Game.WIDTH/2 +120,150,100,50);
	///** Deklaracja przycisku "Help" */
	//public Rectangle helpButton = new Rectangle(Game.WIDTH/2 +120,250,100,50); 
	/** Deklaracja przycisku "Quit" */
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 +120,250,100,50); 
	
	/** Rysowanie okienek menu */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Space Game", Game.WIDTH / 2, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 19, playButton.y+30);
		g2d.draw(playButton);
		//g.drawString("Help", helpButton.x + 19, helpButton.y+30);
		//g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y+30);
		g2d.draw(quitButton);
	}
}
