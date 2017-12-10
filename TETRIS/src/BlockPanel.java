import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel to show Next, Hold block
 * 
 * @author DongYeop Lee
 */
public class BlockPanel extends JPanel {

	/**
	 * label showed below block
	 */
	private JLabel lblStr;

	/**
	 * block to show
	 */
	private Block block;

	/**
	 * Default Constructor
	 */
	public BlockPanel() {
		super(); // JPanel �ʱ�ȭ

		block = new Block(); // ��� ����
		block.setEmpty(); // �ֺ� ������� ����
	}

	/**
	 * @param str
	 *            label String
	 */
	public BlockPanel(String str) {
		this(); // �⺻ ������ ȣ��
		this.setOpaque(false); // �����ϰ� ����
		this.setLayout(new BorderLayout()); // ���� ���̾ƿ����� ����
		lblStr = new JLabel(str);
		lblStr.setFont(new Font("A��������", Font.BOLD, 30));
		lblStr.setForeground(Color.white);
		lblStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblStr.setVisible(true);
		this.add(lblStr, BorderLayout.NORTH); // �� �ʱ�ȭ�� ��ġ ����

		block.setVisible(true);
		this.add(block, SwingConstants.CENTER);
		block.setOpaque(false); // ��� ��ġ ����
	}

	/**
	 * @param str
	 *            label String
	 * @param block
	 *            block to show
	 */
	public BlockPanel(String str, Block block) {
		super();
		this.block = block;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		lblStr = new JLabel(str);
		lblStr.setFont(new Font("A��������", Font.BOLD, TetrisConstants.Menu_fontsize * 3 / 4));
		lblStr.setForeground(Color.white);
		lblStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblStr.setVisible(true);
		this.add(lblStr, BorderLayout.NORTH);

		this.add(lblStr);
		this.add(block, SwingConstants.CENTER); // �� �����ڿ��� ������ ��ϵ� �߰�
	}

	/**
	 * change the block to show
	 * 
	 * @param BlockType
	 */
	public void setBlock(int BlockType) {
		this.remove(block);

		this.block = new Block(BlockType);

		this.add(block, SwingConstants.CENTER);
		this.validate(); // ������ ��� ����
	}

}
