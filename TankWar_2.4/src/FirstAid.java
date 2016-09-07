import java .awt.*;

public class FirstAid {
	int x, y, w, h;
	TankClient tc;
	int step = 0;
	private int[][] pos= {{350, 300},{360, 300},{375, 300},{400, 200},{360, 270},{365, 290},{350, 340}};
	
	
	public FirstAid () {
		this.x = pos[0][0];
		this.y = pos[0][1];
		this.w = 15;
		this.h = 15;
	}
	
	public void draw (Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
		move();
	}
	
	private void move(){
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
	
}
