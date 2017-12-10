import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private StartPanel sPanel;
	private GamePanel gPanel;
	private TetrisFrame parentFrame;

	public MainPanel(TetrisFrame frame) {
		super();

		parentFrame = frame;
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(res);
		this.viewStartPanel();
	}

	public TetrisFrame getParentFrame() {
		return this.parentFrame;
	}

	public void viewStartPanel() {
		sPanel = new StartPanel(this);
		this.add(sPanel);
	}

	public void P1() {
		gPanel = new GamePanel(this, false);
		this.add(gPanel);

	}

	public void P2() {
		gPanel = new GamePanel(this, true);
		this.add(gPanel);

	}

	public void HowTo() {

	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

		page.drawImage(new ImageIcon("./resource/image/background.jpg").getImage(), 0, 0, res.width, res.height, null);
	}

	public GamePanel getGPanel() {
		return this.gPanel;
	}
}
