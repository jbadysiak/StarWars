package Gra;

import java.awt.Rectangle;
/**
 * Klasa GameObject odpowiada za umiejscowienie obiektu
 * na ekranie
 */
public class GameObject {

	/** Wspó³rzêdne miejsca obiektu na ekranie*/
	public double x,y;
	
	/**Konstruktor klasy GameObject
	 * 
	 * @param x - wspó³rzêdna pozioma
	 * @param y - wspó³rzêdna pionowa
	 */
	public GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/** Odpowiada za obwódkê obiektu*/
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x, (int)y, width, height);
	}
}
