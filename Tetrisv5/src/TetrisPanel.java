import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {

	private BlockPanel next, hold;
	private Game game;
	private JPanel leftPanel, centerPanel, rightPanel;

	private GamePanel parentPanel;

	public TetrisPanel() {
		super();
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		game = new Game(this);
		game.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT));
		next = new BlockPanel("NEXT");
		hold = new BlockPanel("HOLD");

	}

	public TetrisPanel(GamePanel parent, int isTwoPlayer) {
		this();
		this.parentPanel = parent;

		if (isTwoPlayer == 0) {
			leftPanel = new JPanel();
			centerPanel = new JPanel();
			rightPanel = new JPanel();

			leftPanel.setLayout(new BorderLayout());
			leftPanel.setPreferredSize(
					new Dimension(TetrisConstants.BLOCKSIZE * 4 + 20, TetrisConstants.BLOCKSIZE * 20));
			rightPanel.setLayout(new BorderLayout());
			leftPanel.add(next, BorderLayout.NORTH);
			centerPanel.add(game);
			rightPanel.add(hold, BorderLayout.NORTH);
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

			leftPanel.setLayout(new BorderLayout());
			leftPanel.setPreferredSize(
					new Dimension(TetrisConstants.BLOCKSIZE * 4 + 20, TetrisConstants.BLOCKSIZE * 20));
			leftPanel.add(next, BorderLayout.NORTH);
			leftPanel.add(hold, BorderLayout.SOUTH);
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
}
