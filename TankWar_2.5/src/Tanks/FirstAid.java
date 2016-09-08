package Tanks;
import java .awt.*;

public class FirstAid {
	int x, y, w, h;
	TankClient tc;
	private int step = 0;
	
	private boolean live;
	
	// the movement of the firstAid
	private int[][] pos= {{350, 300},{360, 500},{375, 400}};
	
	
	public FirstAid () {
		this.x = pos[0][0];
		this.y = pos[0][1];
		this.w = 15;
		this.h = 15;
		this.live = true;
	}
	
	public void draw (Graphics g) {
		if(live) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.fillRect(x, y, w, h);
			g.setColor(Color.WHITE);
			g.drawLine(x+w/2, y + 2, x+w/2, (y+h)-2);
			g.drawLine(x+2, y+h/2, x+w-2, y+h/2);
			g.setColor(c);
		}else {
			live = true;
			move();
		}
	}
	
	private void move() {
		step++;
		if(step == pos.length) {
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
		
	}
	
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
}
