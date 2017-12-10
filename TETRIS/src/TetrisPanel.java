import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel for Tetris Game
 * 
 * @author DongYeop Lee
 *
 */
public class TetrisPanel extends JPanel {
	/** next block show panel */
	private BlockPanel next;
	/** hold block show panel */
	private BlockPanel hold;

	private Game game;

	private JPanel leftPanel, centerPanel, rightPanel;

	private GamePanel parentPanel;
	private GameScore lblScore;
	private int isTwoPlayer;
	private JPanel gameOverPanel;
	private JLabel lblGame;
	private JLabel over;
	private JLabel txt1;

	public TetrisPanel() {
		super();
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		game = new Game(this);
		game.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT));

		next = new BlockPanel("NEXT");
		hold = new BlockPanel("HOLD"); // 게임 전체 ui 초기화

	}

	public TetrisPanel(GamePanel parent, int isTwoPlayer) {
		this();
		this.parentPanel = parent;
		this.isTwoPlayer = isTwoPlayer;
		// 1player 2player 각각 초기화, (같은 클래스 사용, add 순서만 다름)
		if (isTwoPlayer == 0) {

			leftPanel = new JPanel();
			centerPanel = new JPanel();
			rightPanel = new JPanel();

			lblScore = new GameScore();

			leftPanel.setLayout(new BorderLayout());
			leftPanel.setPreferredSize(
					new Dimension(TetrisConstants.BLOCKSIZE * 4 + 20, TetrisConstants.BLOCKSIZE * 20));
			rightPanel.setLayout(new BorderLayout());
			leftPanel.add(next, BorderLayout.NORTH);
			centerPanel.add(game);
			rightPanel.add(hold, BorderLayout.NORTH);
			rightPanel.add(lblScore, BorderLayout.CENTER);
			rightPanel.setPreferredSize(
					new Dimension(TetrisConstants.BLOCKSIZE * 4 + 20, TetrisConstants.BLOCKSIZE * 20));

			leftPanel.setOpaque(false);
			centerPanel.setOpaque(false);
			rightPanel.setOpaque(false);

			this.add(leftPanel);
			this.add(centerPanel);
			this.add(rightPanel);
		} else {
			leftPanel = new JPanel();
			centerPanel = new JPanel();

			lblScore = new GameScore();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.setPreferredSize(
					new Dimension(TetrisConstants.BLOCKSIZE * 4 + 20, TetrisConstants.BLOCKSIZE * 20));
			leftPanel.add(next, BorderLayout.NORTH);
			leftPanel.add(hold, BorderLayout.SOUTH);

			leftPanel.add(lblScore, BorderLayout.CENTER);
			centerPanel.add(game);

			leftPanel.setOpaque(false);
			centerPanel.setOpaque(false);

			if (isTwoPlayer == 1) {
				this.add(leftPanel);
				this.add(centerPanel);

			} else {
				this.add(centerPanel);
				this.add(leftPanel);

			}
		}
	}

	public GamePanel getParentPanel() {
		return this.parentPanel;
	}

	public Game getGame() {
		return game;
	}

	public void setNext(int blockType) {
		next.setBlock(blockType);
		this.validate();
	}

	public void setHold(int blockType) {
		hold.setBlock(blockType);
		this.validate();
	}

	public int isTwo() {
		return this.isTwoPlayer;
	}

	public void hideGameOver() {
		gameOverPanel.setVisible(false);
	}

	public void showGameOver() {
		gameOverPanel.setVisible(true);
	}

	public void gameOver() { // 게임 오버인 경우, gameOverPanel 표시
		System.out.println("finish");

		// gameOverPanel 내용 하드코딩
		game.setVisible(false);
		gameOverPanel = new JPanel();

		gameOverPanel.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT));
		gameOverPanel.setBackground(new Color(100, 100, 100, 200));
		gameOverPanel.setOpaque(true);

		gameOverPanel.setLayout(new GridLayout(3, 1));
		lblGame = new JLabel("G A M E");
		over = new JLabel("O V E R");
		txt1 = new JLabel("Q : 종료    R : 재시작");

		lblGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblGame.setVerticalAlignment(SwingConstants.CENTER);
		lblGame.setVerticalTextPosition(SwingConstants.CENTER);

		over.setHorizontalAlignment(SwingConstants.CENTER);
		over.setVerticalAlignment(SwingConstants.CENTER);
		over.setVerticalTextPosition(SwingConstants.CENTER);

		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setVerticalAlignment(SwingConstants.CENTER);
		txt1.setVerticalTextPosition(SwingConstants.CENTER);

		lblGame.setForeground(Color.white);
		over.setForeground(Color.white);
		txt1.setForeground(Color.white);

		lblGame.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize * 10 / 4));
		over.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize * 10 / 4));
		txt1.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize));
		gameOverPanel.add(lblGame);
		gameOverPanel.add(over);
		gameOverPanel.add(txt1);

		centerPanel.add(gameOverPanel);

		gameOverPanel.setVisible(true);
		lblGame.setVisible(true);
		over.setVisible(true);

		this.revalidate();
	}

	public void Win() {
		lblGame.setText("Y O U");
		over.setText("W I N");
	}

	public void lose() {
		lblGame.setText("Y O U");
		over.setText("L O S E");
	}

	public void draw() {
		lblGame.setText("D R A W");
		over.setText("");
	}

	public GameScore getLblScore() {
		return this.lblScore;
	}

	public void resume() {
		game.setVisible(true);
	}
}
