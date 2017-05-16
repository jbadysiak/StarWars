package Gra;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;
import classes.EntityA;
import classes.EntityB;
/**
 * Klasa Game rozszerza klasê Canvas oraz zawiera implementacjê klasy Runnable
 * odpowiada za budowê g³ównej czêœci gry
 * 
 */
public class Game extends Canvas implements Runnable{
	
	/** Wresja szeregowa UID */
	private static final long serialVersionUID = 1L;
	/** Zmienna deklaruje szerokoœæ ekranu */
	public static final int WIDTH = 320;
	/** Zmienna deklaruje wysokoœæ ekranu */
	public static final int HEIGHT = WIDTH / 12 * 9;
	/** Zmienna deklaruje skalê ekranu */
	public static final int SCALE = 2;
	/** Zmienna deklaruje Tytu³ okna */
	public final String TITLE = "2D Space Game";
	
	/** Zmienna deklaruje, i¿ system jest zatrzymany w danym momencie*/
	private boolean running = false;
	/** Instancja w¹tka */
	private Thread thread;
	
	/** Deklaracja zmiennej obrazu */
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	/** Zmienna deklaruje Sprite'y */
	private BufferedImage spriteSheet = null;
	/** Zmienna deklaruje t³o ekranu */
	private BufferedImage background = null;
	
	/** Zmienna deklaruje czy w danej chwili s¹ oddawane strza³y */
	private boolean is_shooting = false;
	
	/** Zmienna deklaruje pocz¹tkow¹ liczbê wrogów */
	private int enemy_count = 5;
	/** Zmienna deklaruje liczbe zabitych wrogow */ 
	private int enemy_killed = 0;
	
	/** Instancja gracza */
	private Player p;
	/** Instancja Controllera */
	private Controller c;
	/** Instancja struktur */
	private Textures tex;
	/** Instancja menu */
	private Menu menu;
	
	/**Zwi¹zana lista jest zbiorem liniowych elementów danych */
	public LinkedList<EntityA> ea;
	/**Zwi¹zana lista jest zbiorem liniowych elementów danych */
	public LinkedList<EntityB> eb;
	
	/** Zmienna deklaruje wartoœæ pocz¹tkow¹ pasku ¿ycia */ 
	public static int HEALTH = 100 * 2;
	
	/** Zmienne odpowiadaj¹ce za wyœwietlenie menu */
	public static enum STATE{
		MENU,
		GAME;
	};
	
	/** Instancja odpowiadaj¹ca za budowê MENU */
	public static STATE State = STATE.MENU;
	
	/** Metoda tworzy okno, obrazy gry, dodaje obs³ugê z klawiatury i myszki */
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("/kuba.png");
			background = loader.loadImage("/background.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player (300,400,tex, this, c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		this.addMouseListener(new MouseInput());
		this.addKeyListener(new KeyInput(this));
		
		c.createEnemy(enemy_count);
	}
	
	/** Metoda rozpoczêcia dzia³ania */
	private synchronized void start(){
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/** Metoda zatrzymania dzia³ania */
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try{
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	/** Metoda odpowiada za uruchomienie metody "init()" oraz oblicza fps */
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates+" Ticks, Fps "+frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	/** Stworzenie metody odpowiadaj¹cej za rozpoczêcie gry 
	 * oraz liczbê wrogów  
	 */
	private void tick() {
		if(State == STATE.GAME){
			p.tick();
			c.tick();
		}
		if(enemy_killed >= enemy_count){
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
	}
	
	/** Budowa solucji */
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
		
		g.drawImage(background, 0, 0, null);
		if(State == STATE.GAME){
			p.render(g);
			c.render(g);
			
			g.setColor(Color.RED);
			g.fillRect(5, 5, 200, 30);
			
			g.setColor(Color.GREEN);
			g.fillRect(5, 5, HEALTH, 30);
			
			g.setColor(Color.WHITE);
			g.drawRect(5, 5, 200, 30);
			
		}else if(State == STATE.MENU){
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	/** Metoda opisuj¹ca jaki przycisk zosta³ wciœniêty */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(State == STATE.GAME){
			if (key == KeyEvent.VK_RIGHT){
				p.setVelX(5);
			}else if (key == KeyEvent.VK_LEFT){
				p.setVelX(-5);
			}else if (key == KeyEvent.VK_UP){
				p.setVelY(-5);
			}else if (key == KeyEvent.VK_DOWN){
				p.setVelY(5);
			}else if (key == KeyEvent.VK_SPACE  && !is_shooting){
				c.addEntity(new Bullet(p.getX(),p.getY(),tex, this));
				is_shooting = true;
			}
		}
	}
	
	/** Metoda opisuj¹ca jaki klawisz zosta³ puszczony */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
		}else if (key == KeyEvent.VK_LEFT){
			p.setVelX(0);
		}else if (key == KeyEvent.VK_UP){
			p.setVelY(0);
		}else if (key == KeyEvent.VK_DOWN){
			p.setVelY(0);
		}else if (key == KeyEvent.VK_SPACE){
			is_shooting = false;
		}
	}

	/** G³ówna czêœæ programu */
	public static void main(String args[]){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	/** Pobranie Sprite */
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}

	/** Pobranie liczby wrogów */
	public int getEnemy_count() {
		return enemy_count;
	}

	/** Ustawienie liczby wrogów */
	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	/** Pobranie liczby wrogów zastrzelonych */
	public int getEnemy_killed() {
		return enemy_killed;
	}

	/** Ustawienie liczby wrogów zastrzeonych*/
	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
}
