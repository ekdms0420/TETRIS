import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestBlock {
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setPreferredSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		Block b = new Block(BlockConstants.IMINO);

		panel.add(b);
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);

		b.turn();
	}

}
