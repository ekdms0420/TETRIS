import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel display all the panel when game started
 * 
 * @author DongYeop Lee
 *
 */
public class GamePanel extends JPanel {
	/**
	 * player 1 panel
	 */
	private TetrisPanel P1;

	/**
	 * player 2 panel
	 */
	private TetrisPanel P2;
	/**
	 * Panel to show pausePanel
	 */
	private JPanel setOnPanel;
	/**
	 * panel to show the instruction
	 */
	private JPanel empty;
	private boolean isTwoPlayer;
	/**
	 * panel to display game
	 */
	private JPanel games;

	private MainPanel parent;

	/**
	 * menu displayed when paused
	 */
	private JLabel paused;
	/**
	 * check is pause hitted
	 */
	private boolean isHit;
	/**
	 * pause menu
	 */
	private JPanel pausePanel;

	/**
	 * background image (RGBA)
	 */
	private BufferedImage background;
	/**
	 * instruction of game start
	 */
	private JLabel ready;

	public GamePanel() {

		super();
		System.gc();
		this.setPreferredSize(TetrisConstants.res);
		this.setOpaque(false);
		this.setLayout(null);
		// 패널 속성 초기화
		setOnPanel = new JPanel();

		// 배경화면 표시
		try {
			background = ImageIO.read(new File("./resource/image/pauseMenu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pausePanel = new JPanel() {
			public void paintComponent(Graphics page) {
				page.drawImage(background, 0, 0, null);

				super.paintComponent(page);
			}
		};

		// 위에 올라가는 패널들 속성 초기화
		setOnPanel.setBounds(0, 0, TetrisConstants.res.width, TetrisConstants.res.height);
		setOnPanel.setPreferredSize(TetrisConstants.res);
		setOnPanel.setVisible(true);
		setOnPanel.setOpaque(false);
		this.add(setOnPanel);
		this.add(pausePanel);

		this.setComponentZOrder(setOnPanel, 1);
		this.setComponentZOrder(pausePanel, 0);
		setOnPanel.setLayout(new BorderLayout());

		games = new JPanel();
		games.setBackground(Color.black);
		games.setOpaque(false);
		games.setPreferredSize(new Dimension(TetrisConstants.res.width, TetrisConstants.BLOCKSIZE * 32));
		empty = new JPanel();
		empty.setPreferredSize(new Dimension(TetrisConstants.res.width, TetrisConstants.BLOCKSIZE));
		empty.setOpaque(false);
		setOnPanel.add(empty, BorderLayout.NORTH);
		setOnPanel.add(games, BorderLayout.CENTER);

		paused = new JLabel("PAUSE");
		paused.setHorizontalAlignment(SwingConstants.CENTER);
		paused.setVerticalAlignment(SwingConstants.CENTER);
		paused.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize));
		paused.setForeground(Color.white);
		paused.setBackground(Color.red);
		paused.setOpaque(true);
		paused.setVisible(false);

		ready = new JLabel("PRESS ENTER TO START");
		ready.setHorizontalAlignment(SwingConstants.CENTER);
		ready.setVerticalAlignment(SwingConstants.CENTER);
		ready.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize));
		ready.setForeground(Color.white);
		ready.setBackground(Color.cyan);
		ready.setOpaque(true);
		ready.setVisible(true);

		empty.add(paused);
		empty.add(ready);
		isHit = false;

		// pausePanel 초기화
		pausePanel.setVisible(false);
		pausePanel.setOpaque(false);
		pausePanel.setBounds((TetrisConstants.res.width - TetrisConstants.res.width / 4) / 2,
				(TetrisConstants.res.height - TetrisConstants.res.height / 2) / 2, TetrisConstants.res.width / 4,
				TetrisConstants.res.height / 2); // 중앙에 오도록 설정
		pausePanel.setLayout(new GridLayout(4, 1)); // 각 메뉴 위치 설정 위한 레이아웃 설정

		JButton[] btnArray = new JButton[4];
		String strButtonName[] = { "Resume", "Restart", "Back to Menu", "Exit" }; // 버튼에 들어갈 변수들 배열로 초기화

		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i] = new JButton(strButtonName[i]);
			btnArray[i].setOpaque(false);
			btnArray[i].setBorderPainted(false);
			btnArray[i].setContentAreaFilled(false);
			btnArray[i].setForeground(Color.white);
			btnArray[i].setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize));
			btnArray[i].addMouseListener(
					new BtnMouseListener(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize)));
			pausePanel.add(btnArray[i]);
		}

		btnArray[0].addActionListener(new ActionListener() {

			// 재개
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause();
				pausePanel.setVisible(false);

			}

		});

		btnArray[1].addActionListener(new ActionListener() {

			// 재시작
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isTwoPlayer) {
					setVisible(false);
					parent.removeAll();
					parent.P2();

				} else {
					setVisible(false);
					parent.removeAll();
					parent.P1();

				}
			}

		});

		btnArray[2].addActionListener(new ActionListener() {

			// 메뉴로 복귀
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tetris.main(null);
				parent.getParentFrame().stopTheme();
				parent.getParentFrame().dispose(); // 프로그램 재시작으로 대체

			}

		});

		btnArray[3].addActionListener(new ActionListener() {

			// 종료
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		this.setOpaque(false);
	}

	/**
	 * Constructor mainly used
	 * 
	 * @param mainP
	 * @param isTwoPlayer
	 */
	public GamePanel(MainPanel mainP, boolean isTwoPlayer) {
		this(); // 기본생성자 사용해서 공통사항 초기화

		this.parent = mainP;
		this.isTwoPlayer = isTwoPlayer;
		parent.getParentFrame().startTheme();

		// 1p인 경우와 2p인경우 각각 생성해서 추가
		if (!this.isTwoPlayer) {

			P1 = new TetrisPanel(this, 0);
			games.add(P1);
		} else {
			games.setLayout(new FlowLayout());
			P1 = new TetrisPanel(this, 1);
			P2 = new TetrisPanel(this, 2);

			games.add(P2);
			games.add(P1);
		}
		TetrisFrame parent = mainP.getParentFrame();

		parent.getContentPane().requestFocusInWindow(); // 최종적으로 모든 인스턴스가 생성되었으므로,
		parent.getContentPane().addKeyListener(new TetrisControl()); // 키리스너 포커스 JFrame으로 이동

	}

	/**
	 * @return player1 Panel
	 */
	public TetrisPanel getP1() {
		return P1;
	}

	/**
	 * return player2 panel
	 */
	public TetrisPanel getP2() {
		return P2;
	}

	/**
	 * show/hide pause panel
	 */
	public void viewPausePanel() {

		if (isHit) {
			pausePanel.setVisible(true);
		} else {
			pausePanel.setVisible(false);
		}
	}

	/**
	 * KeyListener innerclass
	 * 
	 * @author DongYeop Lee
	 *
	 */
	private class TetrisControl implements KeyListener {

		/**
		 * KeyListener method override
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			int key = e.getKeyCode();
			Object elem = e.getSource();
			// System.out.println(elem.toString());

			// ESC 의 경우 일시정지
			if (key == KeyEvent.VK_ESCAPE) {
				pause();
			} else if (!isHit) { // 그 외의 경우 일시정지가 안되어있는 경우에만 키를 받음
				if (!isTwoPlayer) { // 1PLAYER 모드일때
					Game uCon = getP1().getGame();

					switch (key) {
					case KeyEvent.VK_DOWN:
						uCon.move(TetrisConstants.down);
						// System.out.println("Down");
						break;
					case KeyEvent.VK_LEFT:
						uCon.move(TetrisConstants.left);
						// System.out.println("Left");
						break;
					case KeyEvent.VK_RIGHT:
						uCon.move(TetrisConstants.right);
						// System.out.println("Right");
						break;
					case KeyEvent.VK_UP:
						uCon.turn();
						// System.out.println("up");
						break;
					case KeyEvent.VK_ENTER:
						ready.setVisible(false);
						uCon.gameStart();
						// System.out.println("ENTER");
						break;
					case KeyEvent.VK_SPACE:
						uCon.fall();
						// System.out.println("SPACE");
						break;
					case KeyEvent.VK_SHIFT:
						uCon.hold();
						break;
					case KeyEvent.VK_Q:
						if (uCon.isGameFin())
							System.exit(0);
						break;
					case KeyEvent.VK_R:
						if (uCon.isGameFin())
							Tetris.main(null);
						parent.getParentFrame().dispose();
						break;
					}
				} else { // (2PLAYER 모드일때)
					Game uCon1 = getP1().getGame();
					Game uCon2 = getP2().getGame();
					switch (key) {
					case KeyEvent.VK_DOWN:
						uCon1.move(TetrisConstants.down);
						// System.out.println("Down");
						break;
					case KeyEvent.VK_LEFT:
						uCon1.move(TetrisConstants.left);
						// System.out.println("Left");
						break;
					case KeyEvent.VK_RIGHT:
						uCon1.move(TetrisConstants.right);
						// System.out.println("Right");
						break;
					case KeyEvent.VK_UP:
						uCon1.turn();
						// System.out.println("up");
						break;
					case KeyEvent.VK_SLASH:
						uCon1.fall();
						break;
					case KeyEvent.VK_QUOTE:
						uCon1.hold();
						break;
					case KeyEvent.VK_S:
						uCon2.move(TetrisConstants.down);
						// System.out.println("Down");
						break;
					case KeyEvent.VK_A:
						uCon2.move(TetrisConstants.left);
						// System.out.println("Left");
						break;
					case KeyEvent.VK_D:
						uCon2.move(TetrisConstants.right);
						// System.out.println("Right");
						break;
					case KeyEvent.VK_W:
						uCon2.turn();
						// System.out.println("up");
						break;
					case KeyEvent.VK_C:
						uCon2.fall();
						break;
					case KeyEvent.VK_V:
						uCon2.hold();
						break;
					case KeyEvent.VK_ENTER:
						ready.setVisible(false);
						uCon1.gameStart();
						uCon2.gameStart();
						break;
					case KeyEvent.VK_Q:
						if (uCon1.isGameFin() && uCon2.isGameFin())
							System.exit(0);
						break;
					case KeyEvent.VK_R:
						if (uCon1.isGameFin() && uCon2.isGameFin()) {
							Tetris.main(null);
							parent.getParentFrame().stopTheme();
							parent.getParentFrame().dispose();
						}

						break;
					}
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	public MainPanel getMainPanel() {
		return this.parent;
	}

	/**
	 * display win/lose on the game panel when 2player mode
	 */
	public void whoWin() {
		int p1score = P1.getLblScore().getScore();
		int p2score = P2.getLblScore().getScore();

		if (P1.getGame().isGameFin() && P2.getGame().isGameFin()) {
			P1.hideGameOver();
			P2.hideGameOver();

			if (p1score > p2score) {
				P1.Win();
				P2.lose();
			} else if (p1score < p2score) {
				P1.lose();
				P2.Win();
			} else {
				P1.draw();
				P2.draw();
			}
			P1.showGameOver();
			P2.showGameOver();
			// 게임 오버를 숨기고, 승자/패자 표시
		}
	}

	/**
	 * pause the game
	 */
	private void pause() {

		Game uCon1 = getP1().getGame();
		uCon1.pause();
		isHit = !isHit;
		paused.setVisible(isHit);
		viewPausePanel();
		if (isTwoPlayer) {
			Game uCon2 = getP2().getGame();
			uCon2.pause();
		}
	}

}
