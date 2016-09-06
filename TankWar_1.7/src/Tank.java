import java.awt.*;
import java.awt.event.*;


public class Tank {
	public static final int X_SPEED = 5;
	public static final int Y_SPEED = 5;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	
	private boolean live = true;
	
	private TankClient tc;
	private boolean enemy;
	
	private int x, y;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	// 8 directions
	enum Direction {L, LU, RU, R, U, D, LD, RD, STOP};
	
	private Direction dir = Direction.STOP;
	private Direction shooter = Direction.D;
	
	public Tank(int x, int y, boolean enemy) {
		this.x = x;
		this.y = y;
		this.enemy = enemy;
	}
	
	public Tank(int x, int y, boolean enemy, TankClient tc) {
		this(x,y, enemy);
		this.tc = tc;
	}
	
	public void draw (Graphics g) {
		if (!live) {
			if(enemy) {
				tc.enemies.remove(this);
			}
			return;
		}
		
		Color c = g.getColor();
		if(enemy) g.setColor(Color.BLUE);
		else g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		switch(shooter) {
		case L:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT/2);
			break;
		case LU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y);
			break;
		case LD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT);
			break;
		case R:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT/2);
			break;
		case RU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y);
			break;
		case RD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT);
			break;
		case U:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y);
			break;
		case D:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y + Tank.HEIGHT);
			break;
		}
			
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
		if (this.dir != Direction.STOP) {
			this.shooter = this.dir;
		}
		
		if (x < 0) x = 0;
		if (y < 22) y = 22;
		if (x + Tank.WIDTH > TankClient.SCREEN_WIDTH) x = TankClient.SCREEN_WIDTH - Tank.WIDTH;
		if (y + Tank.HEIGHT > TankClient.SCREEN_HEIGHT) y = TankClient.SCREEN_HEIGHT-Tank.HEIGHT;
	}
	
	public void keyPressed (KeyEvent e) { 
		
		int key = e.getKeyCode();
		switch (key) {
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
			// fire
			case KeyEvent.VK_CONTROL:
				fire();
				break;
			case KeyEvent.VK_SPACE:
				fire();
				break;
		
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
		Missile m = new Missile(x, y, shooter, this.tc);
		tc.missiles.add(m);
		return m;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}


	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	
}
