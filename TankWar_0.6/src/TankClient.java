import java.awt.*;
import java.awt.event.*;


public class TankClient extends Frame{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	int x = 50;
	int y = 50;
	
	
	Image offScreenImage = null;
	
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	// double buffer, but not important actually
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0,0,SCREEN_WIDTH, SCREEN_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	// the window
	public void launchFrame () {
		this.setLocation(400, 300);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setTitle("World of Tanks");
		// close the window
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.addKeyListener(new KeyMonitor());
		
		setVisible(true);
		
		new Thread(new PaintThread()).start();
	}
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
		
	}
	
	private class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class KeyMonitor extends KeyAdapter {


		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
				// left
				case KeyEvent.VK_LEFT :
					x -= 5;
					break;
				case KeyEvent.VK_A :
					x -= 5;
					break;
				
				// right
				case KeyEvent.VK_RIGHT:
					x += 5;
					break;
				case KeyEvent.VK_D:
					x += 5;
					break;	
					
				// up
				case KeyEvent.VK_UP:
					y -= 5;
					break;
				case KeyEvent.VK_W:
					y -= 5;
					break;	
					
				// down	
				case KeyEvent.VK_DOWN:
					y += 5;
					break;
				case KeyEvent.VK_S:
					y += 5;
					break;
			}
		}
		
	}



}
