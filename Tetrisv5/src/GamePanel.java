import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {

	private TetrisPanel P1, P2;
	private JPanel empty;
	private boolean isTwoPlayer;
	private JPanel games;
	private MainPanel parent;

	private JLabel paused;
	private boolean isHit;

	public GamePanel() {
		super();
		System.gc();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		games = new JPanel();
		games.setBackground(Color.black);
		games.setOpaque(false);
		games.setPreferredSize(new Dimension(TetrisConstants.res.width, TetrisConstants.BLOCKSIZE * 32));
		empty = new JPanel();
		empty.setPreferredSize(new Dimension(TetrisConstants.res.width, TetrisConstants.BLOCKSIZE));
		empty.setOpaque(false);
		this.add(empty, BorderLayout.NORTH);
		this.add(games, BorderLayout.CENTER);

		paused = new JLabel("PAUSE");
		paused.setHorizontalAlignment(SwingConstants.CENTER);
		paused.setVerticalAlignment(SwingConstants.CENTER);
		paused.setFont(new Font("A으라차차", Font.BOLD, 30));
		paused.setForeground(Color.white);
		paused.setBackground(Color.red);
		paused.setOpaque(true);
		paused.setVisible(false);
		empty.add(paused);
		isHit = false;

	}

	public GamePanel(MainPanel mainP, boolean isTwoPlayer) {
		this();

		this.parent = mainP;
		this.isTwoPlayer = isTwoPlayer;
		if (!this.isTwoPlayer) {

			P1 = new TetrisPanel(this, 0);
			games.add(P1);
		} else {
			games.setLayout(new FlowLayout());
			P1 = new TetrisPanel(this, 1);
			P2 = new TetrisPanel(this, 2);

			games.add(P1);
			games.add(P2);
		}
		TetrisFrame parent = mainP.getParentFrame();

		parent.getContentPane().requestFocusInWindow();
		parent.getContentPane().addKeyListener(new TetrisControl());

	}

	public TetrisPanel getP1() {
		return P1;
	}

	public TetrisPanel getP2() {
		return P2;
	}

	private class TetrisControl implements KeyListener {

		@Override

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			int key = e.getKeyCode();
			Object elem = e.getSource();
			// System.out.println(elem.toString());

			if (!isTwoPlayer) {
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
				case KeyEvent.VK_ESCAPE:
					if (!isHit) {
						paused.setVisible(true);
					} else {
						paused.setVisible(false);
					}
					isHit = !isHit;
					uCon.pause();
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
			} else {
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
					uCon1.gameStart();
					uCon2.gameStart();
					break;
				case KeyEvent.VK_ESCAPE:

					paused.setVisible(isHit);
					isHit = !isHit;
					uCon1.pause();
					uCon2.pause();
					break;
				case KeyEvent.VK_Q:
					if (uCon1.isGameFin() && uCon2.isGameFin())
						System.exit(0);
					break;
				case KeyEvent.VK_R:
					if (uCon1.isGameFin() && uCon2.isGameFin()) {
						Tetris.main(null);
						parent.getParentFrame().dispose();
					}

					break;
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

}
