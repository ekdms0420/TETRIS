import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author DaEun Kim
 *
 */
public class ScoreBoard extends JFrame {

	private JButton btnRe, btnNo; // ���� �ٽ� ����, ���� ��ư
	private GameListener gameL;
	private JPanel scPanel = new JPanel();
	File grade;
	PrintWriter pWrite = null;
	FileReader fRead = null;
	private ArrayList<ScoreData> dataList; // ����� ������

	public ScoreBoard(int userScore) {
		this.setUndecorated(true);
		dataList = new ArrayList<ScoreData>();
		dataList.add(new ScoreData(1, "NONE", userScore));
		setSize(new Dimension(785, 800));
		setTitle("ScoreBoard");

		this.setLocation((TetrisConstants.res.width - 800) / 2, (TetrisConstants.res.height - 800) / 2);
		scPanel.setSize(700, 700);
		scPanel.setPreferredSize(new Dimension(800, 200 + 100 * dataList.size()));
		scPanel.setBackground(Color.black);
		scPanel.setLayout(null);
		add(scPanel);

		grade = new File("grade");
		if (!grade.exists()) {
			try {
				grade.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // ���� �������� ������ ���� ����
	}// ScoreBoard()

	public void gameScore() {
		String strnum;
		for (int i = 0; i < dataList.size(); i++) {
			strnum = JOptionPane.showInputDialog("SCORE : " + dataList.get(i).getScore());
			dataList.get(i).setName(strnum);
		} // ��ȭ���ڿ��� �̸� �Է��ϸ� �� �̸��� ����

		getInfo();
	}// gameScore()

	public void showRank() {

		for (int i = 0; i < dataList.size(); i++) {
			for (int j = i; j < dataList.size(); j++) {
				if (dataList.get(i).getScore() < dataList.get(j).getScore()) {
					ScoreData tmp = dataList.get(i);
					dataList.set(i, dataList.get(j));
					dataList.set(j, tmp);
				}
			}
		} // ���� ���� ������ ����

		for (int i = 0; i < dataList.size(); i++) {
			dataList.get(i).setRank(i + 1);
		} // ���� ���� ������ ��ŷ ����

		try {
			pWrite = new PrintWriter(new FileWriter(grade));
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < dataList.size(); i++) {
			pWrite.print(Integer.toString(dataList.get(i).getRank()));
			pWrite.print(" ");
			pWrite.print(dataList.get(i).getName());
			pWrite.print(" ");
			pWrite.print(Integer.toString(dataList.get(i).getScore()));
			pWrite.println();
		} // ���Ͽ� ��ũ �̸� ������ ���ʷ� ����
		pWrite.close();

		printScore();
	}// showRank()

	public void getInfo() {
		try {
			fRead = new FileReader(grade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(fRead);
		try {
			String test;
			while ((test = reader.readLine()) != null) {
				String[] line = new String[3];
				line = test.split(" ");
				dataList.add(new ScoreData(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // ���� �о�ͼ� ��ĭ �������� �߶� ���� ����

		try {
			fRead.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scPanel.setPreferredSize(new Dimension(800, 200 + 100 * dataList.size()));
		showRank();
	}// getInfo()

	public void printScore() {
		setVisible(true);
		int cnt = 0;
		String rank, name, score;

		JScrollPane scrollPane = new JScrollPane(scPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 785, 800);
		scrollPane.setViewportView(scPanel);
		add(scrollPane);

		JLabel header = new JLabel("Rank         Name        Score");

		header.setFont(new Font("Consolas", Font.BOLD, 30));
		header.setForeground(Color.white);
		header.setBounds(140, 75, 1000, 100);
		scPanel.add(header);

		for (int i = 0; i < dataList.size(); i++) {
			JLabel lblRank = new JLabel();
			JLabel lblName = new JLabel();
			JLabel lblScore = new JLabel();

			rank = Integer.toString(dataList.get(i).getRank());
			name = dataList.get(i).getName();
			score = Integer.toString(dataList.get(i).getScore());

			lblRank.setText(rank);
			lblRank.setBounds(170, 155 + cnt, 100, 100);
			lblRank.setFont(new Font("Consolas", Font.BOLD, 20));
			lblRank.setForeground(Color.white);
			scPanel.add(lblRank);

			lblName.setText(name);
			lblName.setBounds(368, 155 + cnt, 100, 100);
			lblName.setFont(new Font("Consolas", Font.BOLD, 20));
			lblName.setForeground(Color.white);
			scPanel.add(lblName);

			lblScore.setText(score);
			lblScore.setBounds(590, 155 + cnt, 100, 100);
			lblScore.setFont(new Font("Consolas", Font.BOLD, 20));
			lblScore.setForeground(Color.white);
			scPanel.add(lblScore);

			cnt = cnt + 100;

		} // �����͸� �о�ͼ� ȭ�鿡 ��ġ

		gameL = new GameListener();

		btnRe = new JButton("Restart");
		btnRe.setBounds(230, 36, 150, 60);
		btnRe.setFont(new Font("Consolas", Font.PLAIN, 20));
		btnRe.setForeground(Color.white);
		btnRe.setContentAreaFilled(false);
		btnRe.setBorderPainted(false);
		btnRe.addActionListener(gameL);
		btnRe.addMouseListener(new BtnMouseListener(new Font("A��������", Font.PLAIN, 20)));
		scPanel.add(btnRe);

		btnNo = new JButton("EXIT");
		btnNo.setBounds(400, 36, 150, 60);
		btnNo.setBackground(Color.white);
		btnNo.setFont(new Font("Consolas", Font.PLAIN, 20));
		btnNo.setForeground(Color.white);
		btnNo.setContentAreaFilled(false);
		btnNo.setBorderPainted(false);
		btnNo.addActionListener(gameL);
		btnNo.addMouseListener(new BtnMouseListener(new Font("A��������", Font.PLAIN, 20)));
		scPanel.add(btnNo);
		this.repaint();
	}// printScore()

	private class GameListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			// 4.event handling...

			Object obj = event.getSource();
			if (obj == btnRe) {
				System.out.println("RE");// Re?��ư ������ �� ���� �ٽ� ����
				Tetris.main(null);
				dispose();
			} else if (obj == btnNo) {
				System.out.println("NO");// No ��ư ������ �� ����
				System.exit(0);
			} // if..else if

		}// actionPerformed()
	}// GameListener class
}
