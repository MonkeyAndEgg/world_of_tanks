import java.awt.*;
import java.awt.event.*;


public class TankClient extends Frame{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	Tank myTank = new Tank (50 ,50);
	Missile m = new Missile(50, 50, Tank.Direction.R);
	Image offScreenImage = null;
	
	public void paint(Graphics g) {
		m.draw(g);
		myTank.draw(g);
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
			myTank.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}
		
	}



}
