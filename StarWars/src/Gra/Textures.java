package Gra;
import java.awt.image.BufferedImage;
/** Klasa Textures odpowiada za stworzenie wzorców dla obiektów
 * 	aplikacji
 *
 */
public class Textures {

	/** Deklaracja tablicy dla zoobrazowania rysunku gracza 
	 * jako Sprite
	 */
	public BufferedImage [] player = new BufferedImage[1];
	/** Deklaracja tablicy dla zoobrazowania rysunku pocisku 
	 * jako Sprite
	 */
	public BufferedImage [] missile = new BufferedImage[1];
	/** Deklaracja tablicy dla zoobrazowania rysunku wrogiej 
	 * jednostki jako Sprite
	 */
	public BufferedImage [] enemy = new BufferedImage[3];
	
	/** Instancja klasy SpriteSheet */
	private SpriteSheet ss;
	
	/**Konstruktor klasy Textures odpowiada za pobaranie 
	 * odpowiednich obrazów
	 * @param game - gra
	 */
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());	
		getTextures();
	}

	/** Metoda pokazuj¹ca wszyskie obrazy z folderu res*/
	private void getTextures() {
		player[0] = ss.grabImage(1, 1, 32, 32);
		missile[0] = ss.grabImage(2, 1, 32, 32);
		enemy[0] = ss.grabImage(3, 1, 32, 32);
		enemy[1] = ss.grabImage(3, 2, 32, 32);
		enemy[2] = ss.grabImage(3, 3, 32, 32);
	}
}
