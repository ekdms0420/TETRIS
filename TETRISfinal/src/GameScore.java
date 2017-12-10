import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Score Panel, calculate score and display score
 * 
 * @author DongYeop Lee
 *
 */
public class GameScore extends JPanel {

	private int score;
	private String strScore;
	private JLabel lblText;
	private JLabel lblScore;

	public GameScore() {
		score = 0;
		strScore = "";
		lblText = new JLabel("SCORE");
		lblScore = new JLabel(strScore);
		// �� ���� �ʱ�ȭ, ����
		setStrScore();

		this.add(lblText);
		this.add(lblScore);
		this.setOpaque(false); // �߰�

		lblText.setForeground(Color.white);
		lblScore.setForeground(Color.white);
		lblText.setFont(new Font("A��������", Font.BOLD, TetrisConstants.Menu_fontsize * 3 / 4));
		lblScore.setFont(new Font("A��������", Font.BOLD, TetrisConstants.Menu_fontsize * 3 / 4)); // ��Ʈ ����
	}

	/**
	 * add score, calculate the score when 'combo' line broken
	 * 
	 * @param combo
	 */
	public void comboBreak(int combo) {
		addScore(10 * (int) (Math.pow(4, combo))); // combo���� �μ������� ���� 4^combo * 10�� ��ŭ ������ ��
		this.revalidate();// ���ΰ�ħ
	}

	/**
	 * add addition to score
	 * 
	 * @param addition
	 */
	public void addScore(int addition) {
		score += addition;
		setStrScore();
	}

	public int getScore() {
		return this.score;
	}

	/**
	 * set strScore with 8 leading zeros
	 */
	public void setStrScore() {
		strScore = String.format("%08d", score); // 8���� ����� 0���� ä��� �������� strScore ����
		lblScore.setText(strScore); // �󺧿� ����
	}

}
