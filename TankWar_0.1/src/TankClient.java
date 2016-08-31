import java.awt.*;


public class TankClient extends Frame{
	
	// the window
	public void launchFrame () {
		this.setLocation(400, 300);
		this.setSize(800, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
		
	}

}
