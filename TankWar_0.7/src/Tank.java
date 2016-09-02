import java.awt.*;
import java.awt.event.*;


public class Tank {
	int x, y;

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw (Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	public void keyPressed (KeyEvent e) { 
		
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
