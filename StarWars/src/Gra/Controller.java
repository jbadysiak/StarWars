package Gra;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import classes.EntityA;
import classes.EntityB;
/**
 * Klasa Controller odpowiada za kontorlê nad danymi obiektami
 * w aplikacji 
 */
public class Controller {

	/**Zwi¹zana lista jest zbiorem liniowych elementów danych */
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	/**Zwi¹zana lista jest zbiorem liniowych elementów danych */
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();

	/** Instancja klasy EntityA */
	EntityA enta;
	/** Instancja klasy EntityB */
	EntityB entb;
	/** Instancja klasy Textures */
	private Textures tex;
	/** Zmienna losowa */
	Random r = new Random();
	private Game game;
	
	/** Konstruktor klasy Controller
	 * 
	 * @param tex - struktura 
	 * @param game - gra
	 */
	public Controller(Textures tex, Game game){
		this.tex = tex;
		this.game = game;
	}
	
	/** Metoda createEnemy tworzy nowego wroga na polu walki */
	public void createEnemy(int enemy_count){
		for(int i = 0; i<enemy_count;i++ ){
			addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
		}
	}
	
	/** Metoda tick odpowiada za poprawne pobranie elementów */ 
	public void tick(){
		
		//A Class
		for(int i = 0; i<ea.size();i++){
			enta = ea.get(i);
			enta.tick();
		}
		
		//B Class
		for(int i = 0; i<eb.size();i++){
			entb = eb.get(i);
			entb.tick();
		}
	}
	
	/** Metoda render odpowida za poprawne rysowanie elementów */
	public void render(Graphics g){
		//A Class
		for(int i = 0; i<ea.size();i++){
			enta = ea.get(i);
			enta.render(g);
		}
		//B Class
		for(int i = 0; i<eb.size();i++){
			entb = eb.get(i);
			entb.render(g);
		}
	}
	
	/** Metoda addEntity, dodaje nowy blok klasy EntityA
	 * 	
	 */
	public void addEntity(EntityA block){
	 
		ea.add(block);
	}
	
	/** Metoda addEntity, usuwa blok klasy EntityA
	 * 	
	 */
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	/** Metoda addEntity, dodaje nowy blok klasy EntityB
	 * 	
	 */
	public void addEntity(EntityB block){
		eb.add(block);
	}
	
	/** Metoda addEntity, usuwa blok klasy EntityA
	 * 	
	 */
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	/** Metoda LinkedList zwraca wartoœæ po³¹czonej listy 
	 * 	dla EntityA
	 */
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	/** Metoda LinkedList zwraca wartoœæ po³¹czonej listy 
	 * 	dla EntityB
	 */
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
}