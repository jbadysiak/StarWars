package Gra;
import java.awt.image.BufferedImage;
/** Klasa SpriteSheet odpowiada za pobranie obrazków do aplikacji */
public class SpriteSheet {
	
	/** Instancja klasy BufferedImage */
	private BufferedImage image;
	
	/** Konstruktor klasy SpriteSheet
	 * 
	 * @param image - obraz animacji
	 */
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	/** Metoda pobrania obrazu z tabeli
	 * 
	 * @param col - kolumna
	 * @param row - wiersz
	 * @param width - szerokoœæ
	 * @param height - wysokoœæ
	 * @return
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		
		BufferedImage img = image.getSubimage((col *32) - 32, (row *32) - 32, width, height);
		return img;
	}
}
