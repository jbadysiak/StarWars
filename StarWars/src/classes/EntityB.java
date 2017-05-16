package classes;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * Klasa EntityA jest klas� 
 * opisuj�c� obiekty wrogiej jednostki 
 *
 */
public interface EntityB {
	
	/** Odpowiada za sprawdzanie kolizji */
	public void tick();
	/** Odpowiada za wykonanie rysowania */
	public void render(Graphics g);
	/** Odpowiada za obw�dk� ka�dego obiektu*/
	public Rectangle getBounds();
	
	/** Pobiera warto�� wsp�rz�dnej x */
	public double getX();
	/** Pobiera warto�� wsp�rz�dnej y */
	public double getY();
}
