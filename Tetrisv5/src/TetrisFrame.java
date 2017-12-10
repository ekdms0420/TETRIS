import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class TetrisFrame extends JFrame {

	private MainPanel panel;

	TetrisFrame() {
		super();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		this.setUndecorated(true);
		gd.setFullScreenWindow(this);

		panel = new MainPanel(this);

		this.pack();
		this.getContentPane().add(panel);

		this.setVisible(true);
		this.getContentPane().setFocusable(true);
	}

}
