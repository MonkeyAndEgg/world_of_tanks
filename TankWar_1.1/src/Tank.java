import java.awt.*;
import java.awt.event.*;


public class Tank {
	public static final int X_SPEED = 5;
	public static final int Y_SPEED = 5;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	
	TankClient tc;
	
	private int x, y;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	// 8 directions
	enum Direction {L, LU, RU, R, U, D, LD, RD, STOP};
	
	private Direction dir = Direction.STOP;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y, TankClient tc) {
		this(x,y);
		this.tc = tc;
	}
	
	public void draw (Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		move();
	}
	
	private void move() {
		switch(dir) {
			case L:
				x -= X_SPEED;
				break;
			case LU:
				x -= X_SPEED;
				y -= Y_SPEED;
				break;
			case LD:
				x -= X_SPEED;
				y += Y_SPEED;
				break;
			case R:
				x += X_SPEED;
				break;
			case RU:
				x += X_SPEED;
				y -= Y_SPEED;
				break;
			case RD:
				x += X_SPEED;
				y += Y_SPEED;
				break;
			case U:
				y -= Y_SPEED;
				break;
			case D:
				y += Y_SPEED;
				break;
			case STOP:
				break;
				
		}
	}
	
	public void keyPressed (KeyEvent e) { 
		
		int key = e.getKeyCode();
		switch (key) {
			// fire
			case KeyEvent.VK_CONTROL:
				tc.m = fire();
				break;
			// left
			case KeyEvent.VK_LEFT :
				left = true;
				break;
			case KeyEvent.VK_A :
				left = true;
				break;
			
			// right
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_D:
				right = true;
				break;	
				
			// up
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_W:
				up =true;
				break;	
				
			// down	
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_S:
				down = true;
				break;
		}
		locateDirection();
	}	
	
	private void locateDirection () {
		if (left && !up && !right && !down) dir = Direction.L;
		else if (!left && up && !right && !down) dir = Direction.U;
		else if (!left && !up && right && !down) dir = Direction.R;
		else if (!left && !up && !right && down) dir = Direction.D;
		else if (left && up && !right && !down) dir = Direction.LU;
		else if (left && !up && !right && down) dir = Direction.LD;
		else if (!left && up && right && !down) dir = Direction.RU;
		else if (!left && !up && right && down) dir = Direction.RD;
		else if (!left && !up && !right && !down) dir = Direction.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			// left
			case KeyEvent.VK_LEFT :
				left = false;
				break;
			case KeyEvent.VK_A :
				left = false;
				break;
			
			// right
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_D:
				right = false;
				break;	
				
			// up
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_W:
				up =false;
				break;	
				
			// down	
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_S:
				down = false;
				break;
		}
		locateDirection();
	}
	
	public Missile fire() {
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.WIDTH/2 - Missile.WIDTH/2;
		Missile m = new Missile(x, y, dir);
		return m;
	}
	
}
