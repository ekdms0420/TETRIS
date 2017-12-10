import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * class for test Block
 *
 */
public class TestBlock {
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setPreferredSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		Block b = new Block(BlockConstants.IMINO);
		b.setOpaque(true);
		panel.setBackground(Color.white);
		panel.add(b);
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);

	}

}
