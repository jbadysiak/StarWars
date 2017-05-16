package Gra;

import java.awt.Rectangle;
/**
 * Klasa GameObject odpowiada za umiejscowienie obiektu
 * na ekranie
 */
public class GameObject {

	/** Wsp�rz�dne miejsca obiektu na ekranie*/
	public double x,y;
	
	/**Konstruktor klasy GameObject
	 * 
	 * @param x - wsp�rz�dna pozioma
	 * @param y - wsp�rz�dna pionowa
	 */
	public GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/** Odpowiada za obw�dk� obiektu*/
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x, (int)y, width, height);
	}
}
