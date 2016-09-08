package Tanks;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/*
 *  This is the main frame or the main manager of the whole game
 * 
 */

public class TankClient extends Frame{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	Tank myTank = new Tank (50 ,50, false, Tank.Direction.STOP, this);
	
	// to store effect, missiles and enemy tanks
	List<Explode> explodes = new ArrayList<Explode>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> enemies = new ArrayList<Tank>();
	
	Missile m = null;
	Image offScreenImage = null;
	
	// the wall on the screen, can add more 
	Wall w1 = new Wall (100, 200, 20, 150, this);
	Wall w2 = new Wall(300, 100, 300, 20, this);
	
	FirstAid aid = new FirstAid();
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawString("Missiles count:" + missiles.size(), 10, 50);
		g.drawString("Explodes count:" + explodes.size(), 10, 70);
		g.drawString("Enemies left:" + enemies.size(), 10, 90);
		g.drawString("Your HP:" + myTank.getHp(), 10, 110);
		
		// set the previous color back
		g.setColor(c);
		
		// if enemies die out, re-generate them
		if (enemies.size() <= 0) {
			for(int i= 0; i < 10; i++) {
				enemies.add(new Tank(50 + 40 *(i + 1), 50, true, Tank.Direction.D, this));
			}
		}
		
		// draw missiles
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(enemies);
			m.hitTank(myTank);
			m.hitWall(w1);
			m.hitWall(w2);
			m.draw(g);
		}
		
		for(int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		// generate enemies
		for(int i = 0; i <enemies.size(); i++) {
			Tank t = enemies.get(i);
			t.colideWithWall(w1);
			t.colideWithWall(w2);
			t.colideWithTanks(enemies);
			t.draw(g);
		}
		
		myTank.draw(g);
		myTank.consume(aid);
		myTank.colideWithWall(w1);
		myTank.colideWithWall(w2);
		myTank.colideWithTanks(enemies);
		
		w1.draw(g);
		w2.draw(g);
		aid.draw(g);
	}
	
	// double buffer, but not important actually
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0,0,SCREEN_WIDTH, SCREEN_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	// the window
	public void launchFrame () {
		for(int i = 0; i < 10; i++) {
			enemies.add(new Tank(50 + 40 *(i + 1), 50, true, Tank.Direction.D, this));
		}
		
		
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
	
	// thread for painting
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
