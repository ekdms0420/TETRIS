import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Main Panel that all the panel sat on
 * 
 * @author DongYeop Lee
 */
public class MainPanel extends JPanel {

	private StartPanel sPanel;
	private GamePanel gPanel;
	private TetrisFrame parentFrame;
	private HowToPanel hPanel;

	/**
	 * main constructor
	 * 
	 * @param frame
	 */
	public MainPanel(TetrisFrame frame) {
		super();

		parentFrame = frame;
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // ȭ�� ũ�⸸ŭ ũ�� ����
		this.setPreferredSize(res);
		this.viewStartPanel(); // ��ŸƮ �г� �����ָ鼭 ���α׷� ����
	}

	public TetrisFrame getParentFrame() {
		return this.parentFrame;
	}

	public void viewStartPanel() {
		sPanel = new StartPanel(this); // startpanel �ʱ�ȭ �ϰ� add
		this.add(sPanel);
	}

	public void P1() {
		gPanel = new GamePanel(this, false); // gamePanel 1�ο����� �ʱ�ȭ�ϰ� add
		this.add(gPanel);
	}

	public void P2() {
		gPanel = new GamePanel(this, true); // gamePanel 2�ο����� �ʱ�ȭ�ϰ� add
		this.add(gPanel);

	}

	public void HowTo() {
		hPanel = new HowToPanel(this); // howtoPanel �ʱ�ȭ�ϰ� add
		this.add(hPanel);
	}

	public void paintComponent(Graphics page) { // ��� �׸� �׸���
		super.paintComponent(page);

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

		page.drawImage(new ImageIcon("./resource/image/background.jpg").getImage(), 0, 0, res.width, res.height, null);
	}

	public GamePanel getGPanel() {
		return this.gPanel;
	}

}
