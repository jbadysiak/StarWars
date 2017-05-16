package Gra;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Klasa BufferedImageLoader odpowiada 
 * za za�adowanie obraz�w do aplikacji
 *
 */
public class BufferedImageLoader {
	/** Instancja klasy BufferedImageLoader */
	private BufferedImage image;
	
	/** Metoda �aduj�ca obrazy */
	public BufferedImage loadImage (String path)throws IOException{
		
		image = ImageIO.read(getClass().getResourceAsStream(path));
		return image;
	}
}
