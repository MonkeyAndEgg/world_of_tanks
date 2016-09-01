import java.awt.*;
import java.awt.event.*;


public class TankClient extends Frame{
	
	// the window
	public void launchFrame () {
		this.setLocation(400, 300);
		this.setSize(800, 600);
		this.setTitle("World of Tanks");
		// close the window
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
		
	}

}
