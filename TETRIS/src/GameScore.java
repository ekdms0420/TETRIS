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
		// 각 변수 초기화, 생성
		setStrScore();

		this.add(lblText);
		this.add(lblScore);
		this.setOpaque(false); // 추가

		lblText.setForeground(Color.white);
		lblScore.setForeground(Color.white);
		lblText.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize * 3 / 4));
		lblScore.setFont(new Font("A으라차차", Font.BOLD, TetrisConstants.Menu_fontsize * 3 / 4)); // 폰트 지정
	}

	/**
	 * add score, calculate the score when 'combo' line broken
	 * 
	 * @param combo
	 */
	public void comboBreak(int combo) {
		addScore(10 * (int) (Math.pow(4, combo))); // combo줄이 부서졌을때 마다 4^combo * 10점 만큼 점수를 줌
		this.revalidate();// 새로고침
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
		strScore = String.format("%08d", score); // 8글자 빈공간 0으로 채우는 포맷으로 strScore 변경
		lblScore.setText(strScore); // 라벨에 지정
	}

}
