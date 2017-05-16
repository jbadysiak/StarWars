package Gra;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animations.Animation;
import classes.EntityA;
import classes.EntityB;
/**
 * Klasa Player rozszerza klas� GameObject oraz wprowadza interface
 * EntityA, odpowiada za obs�ug� pocisku gracza
 */
public class Player extends GameObject implements EntityA{
	
	/** Warto�� zmiennej poziomej */
	private double velX = 0;
	/** Warto�� zmiennej poziomej */
	private double velY = 0;
	/** Struktura obiektu */
	private Textures tex;
	/** Instancja klasy game */
	Game game;
	/** Instancja klasy Animation */
	Animation anim;
	/** Instancja klasy Controller */
	Controller controller;
	
	/** Konstruktor klasy Player 
	 * 
	 * @param x - wsp�rz�dna pozioma
	 * @param y - wsp�rz�dna pionowa
	 * @param tex - struktura
	 * @param game - gra
	 * @param controller - kontroler poprawno�ci 
	 */
	public Player(double x, double y, Textures tex, Game game, Controller controller){
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		
		anim = new Animation(5, tex.player[0], tex.player[0]);
	}
	
	/** Wykonuje operacje budowania slucji dla gracza */
	public void tick(){
		x += velX;
		y += velY;
		
		if(x <= 0)
			x = 0;
		if(x >= 640-20)
			x = 640-20;
		if(y <= 0)
			y = 0;
		if(y >=480-32)
			y=480-32;
		
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				controller.removeEntity(tempEnt);
				Game.HEALTH -=20;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				if(Game.HEALTH == 0){
					System.exit(1);
				}
			}
		}
		anim.runAnimation();
	}
	/** Rysowanie animacji */
	public void render(Graphics g){
		anim.drawAnimation(g,x,y,10);
	}
	
	/** Pobiera warto�� wsp�rz�dnej x */
	public double getY(){
		return y;
	}
	/** Pobiera warto�� wsp�rz�dnej y */
	public double getX(){
		return x;
	}
	/** Ustawienie warto�ci wsp�rz�dnej y */
	public void setY(double y){
		this.y = y;
	}
	/** Ustawienie warto�ci wsp�rz�dnej x */
	public void setX(double x){
		this.x = x;
	}
	/** Ustawienie warto�ci wsp�rz�dnej velX */
	public void setVelX(double velX){
		this.velX = velX;
	}
	/** Ustawienie warto�ci wsp�rz�dnej velY */
	public void setVelY(double velY){
		this.velY = velY;
	}
	/** Uzyskanie dost�pu do obw�dki obrazka */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
