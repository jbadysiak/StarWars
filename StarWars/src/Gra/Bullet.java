package Gra;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animations.Animation;
import classes.EntityA;
import classes.EntityB;
/**
 * Klasa Bullet rozszerza klasê GameObject oraz wprowadza interface
 * EntityA, odpowiada za obs³ugê pocisku gracza
 */
public class Bullet extends GameObject implements EntityA {
	
	/** Struktura obiektu */
	private Textures tex;
	/** Obiekt gry */
	private Game game;
	/** Element animacji */ 
	Animation anim;
	
	/** Konstruktor klasy Bullet
	 * @param x - wspó³rzêdna pozioma
	 * @param y - wspó³rzêdna pionowa
	 * @param tex - struktura obiektu
	 * @param game - obiekt gry
	 */
	public Bullet (double x, double y, Textures tex, Game game){
		super(x-10, y+30);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5, tex.missile[0], tex.missile[0]);
	}
	
	/** Wykonuje operacje budowania slucji dla pocisku */
	public void tick(){
		y-=10;
		anim.runAnimation();
	}
	
	/** Metoda odpowiada za rysowanie animacji */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	/** Uzyskanie dostêpu do obwódki obrazka */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	/** Pobiera wartoœæ wspó³rzêdnej x */
	public double getY(){
		return y;
	}
	/** Pobiera wartoœæ wspó³rzêdnej y */
	public double getX(){
		return x;
	}
}
