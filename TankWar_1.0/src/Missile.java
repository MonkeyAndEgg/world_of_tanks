import java.awt.Color;
import java.awt.Graphics;

public class Missile {
	public static final int X_SPEED = 10;
	public static final int Y_SPEED = 10;
	
	int x, y;
	Tank.Direction dir;
	
	public Missile(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void draw (Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 10, 10);
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
	}
	
}
