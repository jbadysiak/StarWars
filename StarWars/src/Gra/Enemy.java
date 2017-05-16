package Gra;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import animations.Animation;
import classes.EntityA;
import classes.EntityB;
/**
 * Klasa Enemy rozszerza klas� GameObject oraz wprowadza interface
 * EntityB, odpowiada za obs�ug� wrog�w gracza
 */
public class Enemy extends GameObject implements EntityB{

	/** Losowanie danych */
	Random r = new Random();
	/** Struktura obiektu */
	private Textures tex;
	/** Warto�� szybko�ci spadania obiektu */
	private int speed = r.nextInt(3)+1;
	/** Instancja klasy game */
	private Game game;
	/** Instancja klasy Controller */
	private Controller c;

	/** Instancja klasy Animation */
	Animation anim;
	
	/**Konstruktor klasy Enemy
	 * 
	 * @param x - wsp�rz�dna pozioma
	 * @param y - wsp�rz�dna pionowa
	 * @param tex - struktura obiektu
	 * @param c - kontolowanie kolizji
	 * @param game - obiekt gry
	 */
	public Enemy (double x, double y, Textures tex, Controller c, Game game){
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}
	/** Wykonuje operacje budowania slucji dla wrogiej jednostki*/
	public void tick(){
		y += speed;
		
		if(y > (Game.HEIGHT * Game.SCALE)){
			speed = r.nextInt(3)+1;
			y = -10;
			x = r.nextInt(640);
		}
		for(int i = 0; i < game.ea.size();i++){	
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed()+1);
			}
		}
		anim.runAnimation();
	}
	
	/** Rysowanie animacji */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	/** Uzyskanie dost�pu do obw�dki obrazka */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
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
}
