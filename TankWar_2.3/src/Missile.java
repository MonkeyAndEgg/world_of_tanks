import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Missile {
	public static final int X_SPEED = 10;
	public static final int Y_SPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	int x, y;
	private Tank.Direction dir;
	private TankClient tc;
	
	private boolean live = true;
	private boolean mate;
	
	public Missile(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile(int x, int y, boolean mate, Tank.Direction dir, TankClient tc) {
		this.x = x;
		this.y = y;
		this.mate = mate;
		this.dir = dir;
		this.tc = tc;
	}
	
	public void draw (Graphics g) {
		if (!live) {
			tc.missiles.remove(this);
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.BLACK);
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
		}
		if (x < 0 || y < 0 || x > TankClient.SCREEN_WIDTH || y > TankClient.SCREEN_HEIGHT) {
			live = false;
		}
		
	}

	public boolean isLive() {
		return live;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean hitTank(Tank t) {
		if(this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.mate != t.isEnemy()) {
			if(!t.isEnemy()){
				t.setHp(t.getHp() - 20);
				if(t.getHp() <= 0) t.setLive(false);
			} else {
				t.setLive(false);
			}
			
			this.live = false;
			Explode e = new Explode(x, y - 10, tc);
			tc.explodes.add(e);
			return true;
		}
		return false;
	}

	public boolean hitTanks(List<Tank> tanks) {
		for(int i = 0; i < tanks.size(); i++) {
			if(hitTank(tanks.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			return true;
		}
		return false;
	}
	
}
