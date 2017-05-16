package Gra;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animations.Animation;
import classes.EntityA;
import classes.EntityB;
/**
 * Klasa Player rozszerza klasê GameObject oraz wprowadza interface
 * EntityA, odpowiada za obs³ugê pocisku gracza
 */
public class Player extends GameObject implements EntityA{
	
	/** Wartoœæ zmiennej poziomej */
	private double velX = 0;
	/** Wartoœæ zmiennej poziomej */
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
	 * @param x - wspó³rzêdna pozioma
	 * @param y - wspó³rzêdna pionowa
	 * @param tex - struktura
	 * @param game - gra
	 * @param controller - kontroler poprawnoœci 
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
	
	/** Pobiera wartoœæ wspó³rzêdnej x */
	public double getY(){
		return y;
	}
	/** Pobiera wartoœæ wspó³rzêdnej y */
	public double getX(){
		return x;
	}
	/** Ustawienie wartoœci wspó³rzêdnej y */
	public void setY(double y){
		this.y = y;
	}
	/** Ustawienie wartoœci wspó³rzêdnej x */
	public void setX(double x){
		this.x = x;
	}
	/** Ustawienie wartoœci wspó³rzêdnej velX */
	public void setVelX(double velX){
		this.velX = velX;
	}
	/** Ustawienie wartoœci wspó³rzêdnej velY */
	public void setVelY(double velY){
		this.velY = velY;
	}
	/** Uzyskanie dostêpu do obwódki obrazka */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
