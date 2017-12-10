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
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // 화면 크기만큼 크기 지정
		this.setPreferredSize(res);
		this.viewStartPanel(); // 스타트 패널 보여주면서 프로그램 시작
	}

	public TetrisFrame getParentFrame() {
		return this.parentFrame;
	}

	public void viewStartPanel() {
		sPanel = new StartPanel(this); // startpanel 초기화 하고 add
		this.add(sPanel);
	}

	public void P1() {
		gPanel = new GamePanel(this, false); // gamePanel 1인용으로 초기화하고 add
		this.add(gPanel);
	}

	public void P2() {
		gPanel = new GamePanel(this, true); // gamePanel 2인용으로 초기화하고 add
		this.add(gPanel);

	}

	public void HowTo() {
		hPanel = new HowToPanel(this); // howtoPanel 초기화하고 add
		this.add(hPanel);
	}

	public void paintComponent(Graphics page) { // 배경 그림 그리기
		super.paintComponent(page);

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

		page.drawImage(new ImageIcon("./resource/image/background.jpg").getImage(), 0, 0, res.width, res.height, null);
	}

	public GamePanel getGPanel() {
		return this.gPanel;
	}

}
