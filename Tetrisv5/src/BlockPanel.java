import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BlockPanel extends JPanel {

	private JLabel lblStr;

	private Block block;

	public BlockPanel() {
		super();

		block = new Block();
		block.setEmpty();
	}

	public BlockPanel(String str) {
		this();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		lblStr = new JLabel(str);
		lblStr.setFont(new Font("A으라차차", Font.BOLD, 30));
		lblStr.setForeground(Color.white);
		lblStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblStr.setVisible(true);
		this.add(lblStr, BorderLayout.NORTH);

		block.setVisible(true);
		this.add(block, SwingConstants.CENTER);
		block.setOpaque(false);
	}

	public BlockPanel(String str, Block block) {
		super();
		this.block = block;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		lblStr = new JLabel(str);
		lblStr.setFont(new Font("A으라차차", Font.BOLD, 30));
		lblStr.setForeground(Color.white);
		lblStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblStr.setVisible(true);
		this.add(lblStr, BorderLayout.NORTH);

		this.add(lblStr);
		this.add(block, SwingConstants.CENTER);
	}

	public void setBlock(int BlockType) {
		this.remove(block);

		this.block = new Block(BlockType);

		this.add(block, SwingConstants.CENTER);
		this.validate();
	}

}
