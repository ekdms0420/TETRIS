import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * start menu panel
 * 
 * @author DongYeop Lee
 *
 */
public class StartPanel extends JPanel {

	/**
	 * array of buttons in start menu
	 */
	private JButton[] btnArray;
	/**
	 * final String array for button names
	 */
	private final String[] strButtonName = { "1 PLAYER", "2 PLAYER", "How To Play?", "EXIT" };

	private JPanel menuGridPanel, logoPanel;
	private JLabel lblLogo;
	private MainPanel mainLink;

	StartPanel(MainPanel mainP) {
		super();
		this.mainLink = mainP;
		this.setLayout(new GridLayout(2, 1)); // 메인 메뉴 2부분으로 나눈다 (로고, 메뉴)
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./resource/font/A으라차차.TTF"))); // GraphicsEnvironment에
																											// 폰트 추가
		} catch (Exception e) {

		}

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

		// 모든 메뉴 데이터와, 로고 해상도를 이용한 비율로 하드코딩
		logoPanel = new JPanel();
		lblLogo = new JLabel("T E T R I S");
		lblLogo.setForeground(Color.cyan);

		lblLogo.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize * 12 / 4));
		lblLogo.setPreferredSize(new Dimension(res.width, res.height / 2));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		logoPanel.add(lblLogo);
		logoPanel.setOpaque(false);
		logoPanel.setPreferredSize(new Dimension(res.width, res.height / 3));
		this.add(logoPanel);

		menuGridPanel = new JPanel();
		menuGridPanel.setLayout(new GridLayout(4, 1));
		menuGridPanel.setPreferredSize(new Dimension(res.width, res.height / 2));
		menuGridPanel.setOpaque(false);
		// menuGridPanel.setOpaque(false);

		this.add(menuGridPanel);
		// 버튼 초기화
		btnArray = new JButton[4];
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i] = new JButton(strButtonName[i]);
			btnArray[i].setOpaque(false);
			btnArray[i].setBorderPainted(false);
			btnArray[i].setContentAreaFilled(false);
			btnArray[i].setMargin(new Insets(0, 0, 50, 0));
			btnArray[i].setForeground(Color.white);
			btnArray[i].setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize * 6 / 4));
			btnArray[i].addMouseListener(
					new BtnMouseListener(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize * 6 / 4)));
			menuGridPanel.add(btnArray[i]);
		}

		btnArray[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroy();
				mainP.P1(); // 1player mode 실행
			}
		});

		btnArray[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroy();
				mainP.P2(); // 2player mode 실행
			}

		});

		btnArray[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroy();
				mainP.HowTo(); // howto 실행
			}

		});

		btnArray[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0); // 종료
			}

		});
		this.setOpaque(false);
	}

	public void destroy() {
		this.setVisible(false); // mainPanel에 위치한 모든 오브젝트 제거
		this.removeAll();
		// System.out.println(this.listenerList.toString());
	}

}
