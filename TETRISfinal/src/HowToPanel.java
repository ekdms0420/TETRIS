import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Explain manipulation method of this game
 * 
 * @see MainPanel
 * @author Changjo Kim
 *
 */

public class HowToPanel extends JPanel {

	/**
	 * JPanel to contain buttons
	 */
	private JPanel primary;
	/**
	 * panels that contain labels
	 * only p2Panel used to contain p2_1Panel and p2_2Panel
	 */
	private JPanel p1Panel, p2Panel, p2_1Panel, p2_2Panel;
	/**
	 * labels that show words by string
	 */
	private JLabel[] lbl1P, lbl2_1P, lbl2_2P;
	/**
	 * buttons to destroy or make panel
	 * only btnBack destroy all of HowToPanel and make MainPanel
	 */
	private JButton btn1P, btn2P, btnBack;
	/**
	 * strings to set text of labels
	 */
	private String[] str1P = { "Player 1", "←,→ : 좌우 이동", "↑ : 블록 회전", "↓ : 드랍", "Spacebar : 하드 드랍",
			"Shift : Hold에 블록 저장" },
			str2_1P = { "Player 1", "a,d : 좌우 이동", "w : 블록 회전", "s : 드랍", "c :  하드 드랍", "v : Hold에 블록 저장" },
			str2_2P = { "Player 2", "←,→ : 좌우 이동", "↑ : 블록 회전", "↓ : 드랍", "/ : 하드 도랍", "' : Hold에 블록 저장" };

	/**
	 * control the actions of buttons
	 * 
	 * @see BtnListener
	 */
	private BtnListener btnL;
	/**
	 * control the mouse actions of buttons
	 * 
	 * @see btnMouseListener
	 */
	private BtnMouseListener btnMouseL;

	/**
	 * for using upcall
	 * MainPanel make HowToPanel by HowTo()
	 * 
	 * @see MainPanel
	 */
	private MainPanel mainP;

	/**
	 * check which panel(p1Panel or p2Panel) now show on the screen
	 */
	private boolean bP1, bP2;
	/**
	 * the dimension size of screen
	 */
	private Dimension res;

	/**
	 * Constructor get MainPanel for using upcall
	 * set default of all data
	 * 
	 * @param parent
	 */
	public HowToPanel(MainPanel parent) {

		res = TetrisConstants.res; // 배경의 크기를 Dimension으로 받아서 data에 저장.

		this.setPreferredSize(res);
		this.setOpaque(false);
		this.setBounds(res.width / 2 - 200, res.height / 2 - 200, 400, 400);
		this.setLayout(null);
		// this.setLayout(new GridLayout(3, 1));

		mainP = parent; // MainPanel의 upcall을 통해 메소드를 받아 와 MainPanel을 재생성하기 위함.

		btnL = new BtnListener();
		btnMouseL = new BtnMouseListener(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));

		primary = new JPanel();
		primary.setBounds(res.width / 2 - 200, 100, 400, 100); // 배경 크기에 맞춰
		primary.setOpaque(false); // 배경 색을 투명하게 함.

		btn1P = new JButton("1 PLAYER");
		btn1P.setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));
		btn1P.setForeground(Color.white);
		btn1P.addActionListener(btnL); // button에 ActionListener 추가
		btn1P.addMouseListener(btnMouseL); // button에 MouseListener 추가
		btn1P.setContentAreaFilled(false); // 글자만 표시하기 위해 button의 배경 삭제
		btn1P.setBorderPainted(false); // 글자만 표시하기 위해 button의 경계 삭제
		btn1P.setOpaque(false); // 글자만 표시하기 위해 button의 배경 투명화
		primary.add(btn1P);

		btn2P = new JButton("2 PLAYER");
		btn2P.setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));
		btn2P.setForeground(Color.white);
		btn2P.addActionListener(btnL);
		btn2P.addMouseListener(btnMouseL);
		btn2P.setContentAreaFilled(false);
		btn2P.setBorderPainted(false);
		btn2P.setOpaque(false);
		primary.add(btn2P);

		btnBack = new JButton("BACK");
		btnBack.setBounds(res.width - 400, 800, 200, 100);
		btnBack.addActionListener(btnL);
		btnBack.addMouseListener(btnMouseL);
		btnBack.setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));
		btnBack.setForeground(Color.white);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setOpaque(false);

		bP1 = true;
		bP2 = false;

		this.add(primary);

		makeP1();
		this.p1Panel.setVisible(true); // 초기 Default 상태 시 p1Panel을 표시하고 있는다.
		makeP2();

		this.add(btnBack);
	} // HowToPanel()

	/**
	 * make p1Panel setting invisible
	 */
	public void makeP1() {

		p1Panel = new JPanel();
		p1Panel.setBounds(res.width / 2 - 200, res.height / 2 - 200, 600, 400);
		p1Panel.setLayout(new GridLayout(6, 1)); // 6줄에 6개의 label을 추가하기 위함.
		lbl1P = new JLabel[6];
		for (int i = 0; i < 6; ++i) {
			lbl1P[i] = new JLabel(str1P[i]);
			lbl1P[i].setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize));
			lbl1P[i].setForeground(Color.white);
			p1Panel.add(lbl1P[i]);
		} // 각 label에 들어갈 text를 string으로 만들어둔 배열을 불러와 저장.

		p1Panel.setOpaque(false); 

		p1Panel.setVisible(false); // 최초 Constructor에서 생성은 하지만 보이지 않게 하기 위해서 비가시로 설정.

		this.add(p1Panel);
	} // makeP1()

	/**
	 * make p2Panel setting invisible
	 */
	public void makeP2() {

		p2Panel = new JPanel();
		p2Panel.setBounds(res.width / 2 - 400, res.height / 2 - 200, 1000, 400);
		p2Panel.setLayout(new GridLayout(1, 2));

		p2_1Panel = new JPanel();
		p2_1Panel.setLayout(new GridLayout(6, 1));

		p2_2Panel = new JPanel();
		p2_2Panel.setLayout(new GridLayout(6, 1));

		lbl2_1P = new JLabel[6];
		for (int i = 0; i < 6; ++i) {
			lbl2_1P[i] = new JLabel(str2_1P[i]);
			lbl2_1P[i].setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize));
			lbl2_1P[i].setForeground(Color.white);
			p2_1Panel.add(lbl2_1P[i]);
		} // for

		lbl2_2P = new JLabel[6];
		for (int i = 0; i < 6; ++i) {
			lbl2_2P[i] = new JLabel(str2_2P[i]);
			lbl2_2P[i].setFont(new Font("A으라차차", Font.PLAIN, TetrisConstants.Menu_fontsize));
			lbl2_2P[i].setForeground(Color.white);
			p2_2Panel.add(lbl2_2P[i]);
		} // for

		p2_1Panel.setOpaque(false);
		p2_2Panel.setOpaque(false);

		p2Panel.setOpaque(false);

		p2Panel.add(p2_1Panel);
		p2Panel.add(p2_2Panel);

		p2Panel.setVisible(false); // 최초 Constructor에서 생성은 하지만 보이지 않게 하기 위해서 비가시로 설정.

		this.add(p2Panel);
	} // makeP2

	/**
	 * remove all parameter of HowToPanel
	 */
	public void destroy() {

		this.setVisible(false);
		this.removeAll();
		// System.out.println(this.listenerList.toString());
	} // destroy

	/**
	 * set the action of each button
	 */
	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			Object obj = event.getSource();

			if (obj == btn1P) {
				if (bP1 == false) {

					p2Panel.setVisible(false);
					p2Panel.removeAll();

					makeP1();
					p1Panel.setVisible(true);
				} // 화면에 현재 p1Panel이 아닌 p2Panel 표시 중일 시 p2Panel을 제거하고 p1Panel을 생성
				bP1 = true;
				bP2 = false;
			} else if (obj == btn2P) {
				if (bP2 == false) {

					p1Panel.setVisible(false);
					p1Panel.removeAll();

					makeP2();
					p2Panel.setVisible(true);
				} // 화면에 현재 p2Panel이 아닌 p1Panel 표시 중일 시 p1Panel을 제거하고 p2Panel을 생성
				bP2 = true;
				bP1 = false;
			} else if (obj == btnBack) {
				btn1P.setForeground(Color.white);
				btn2P.setForeground(Color.white);

				destroy();

				mainP.viewStartPanel(); // 파괴된 MainPanl 새로 생성

			} // MainPanel을 다시 표시하기 위해 현재 HowToPanel의 모든 것을 제거한다.

		} // actionPerformed()

	} // BtnListener class

} // HowToPanel class