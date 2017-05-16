package classes;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * Klasa EntityA jest klas¹ 
 * opisuj¹c¹ obiekty wrogiej jednostki 
 *
 */
public interface EntityB {
	
	/** Odpowiada za sprawdzanie kolizji */
	public void tick();
	/** Odpowiada za wykonanie rysowania */
	public void render(Graphics g);
	/** Odpowiada za obwódkê ka¿dego obiektu*/
	public Rectangle getBounds();
	
	/** Pobiera wartoœæ wspó³rzêdnej x */
	public double getX();
	/** Pobiera wartoœæ wspó³rzêdnej y */
	public double getY();
}
