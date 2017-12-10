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
		super(); // JPanel 초기화

		block = new Block(); // 블록 생성
		block.setEmpty(); // 텅빈 블록으로 설정
	}

	/**
	 * @param str
	 *            label String
	 */
	public BlockPanel(String str) {
		this(); // 기본 생성자 호출
		this.setOpaque(false); // 투명하게 설정
		this.setLayout(new BorderLayout()); // 보더 레이아웃으로 설정
		lblStr = new JLabel(str);
		lblStr.setFont(new Font("A으라차차", Font.BOLD, 30));
		lblStr.setForeground(Color.white);
		lblStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblStr.setVisible(true);
		this.add(lblStr, BorderLayout.NORTH); // 라벨 초기화및 위치 설정

		block.setVisible(true);
		this.add(block, SwingConstants.CENTER);
		block.setOpaque(false); // 블록 위치 설정
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
		lblStr.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize * 3 / 4));
		lblStr.setForeground(Color.white);
		lblStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblStr.setVisible(true);
		this.add(lblStr, BorderLayout.NORTH);

		this.add(lblStr);
		this.add(block, SwingConstants.CENTER); // 위 생성자에서 보여줄 블록도 추가
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
		this.validate(); // 보여줄 블록 변경
	}

}
