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
	private String[] str1P = { "Player 1", "��,�� : �¿� �̵�", "�� : ��� ȸ��", "�� : ���", "Spacebar : �ϵ� ���",
			"Shift : Hold�� ��� ����" },
			str2_1P = { "Player 1", "a,d : �¿� �̵�", "w : ��� ȸ��", "s : ���", "c :  �ϵ� ���", "v : Hold�� ��� ����" },
			str2_2P = { "Player 2", "��,�� : �¿� �̵�", "�� : ��� ȸ��", "�� : ���", "/ : �ϵ� ����", "' : Hold�� ��� ����" };

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

		res = TetrisConstants.res; // ����� ũ�⸦ Dimension���� �޾Ƽ� data�� ����.

		this.setPreferredSize(res);
		this.setOpaque(false);
		this.setBounds(res.width / 2 - 200, res.height / 2 - 200, 400, 400);
		this.setLayout(null);
		// this.setLayout(new GridLayout(3, 1));

		mainP = parent; // MainPanel�� upcall�� ���� �޼ҵ带 �޾� �� MainPanel�� ������ϱ� ����.

		btnL = new BtnListener();
		btnMouseL = new BtnMouseListener(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));

		primary = new JPanel();
		primary.setBounds(res.width / 2 - 200, 100, 400, 100); // ��� ũ�⿡ ����
		primary.setOpaque(false); // ��� ���� �����ϰ� ��.

		btn1P = new JButton("1 PLAYER");
		btn1P.setFont(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));
		btn1P.setForeground(Color.white);
		btn1P.addActionListener(btnL); // button�� ActionListener �߰�
		btn1P.addMouseListener(btnMouseL); // button�� MouseListener �߰�
		btn1P.setContentAreaFilled(false); // ���ڸ� ǥ���ϱ� ���� button�� ��� ����
		btn1P.setBorderPainted(false); // ���ڸ� ǥ���ϱ� ���� button�� ��� ����
		btn1P.setOpaque(false); // ���ڸ� ǥ���ϱ� ���� button�� ��� ����ȭ
		primary.add(btn1P);

		btn2P = new JButton("2 PLAYER");
		btn2P.setFont(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));
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
		btnBack.setFont(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize * 3 / 4));
		btnBack.setForeground(Color.white);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setOpaque(false);

		bP1 = true;
		bP2 = false;

		this.add(primary);

		makeP1();
		this.p1Panel.setVisible(true); // �ʱ� Default ���� �� p1Panel�� ǥ���ϰ� �ִ´�.
		makeP2();

		this.add(btnBack);
	} // HowToPanel()

	/**
	 * make p1Panel setting invisible
	 */
	public void makeP1() {

		p1Panel = new JPanel();
		p1Panel.setBounds(res.width / 2 - 200, res.height / 2 - 200, 600, 400);
		p1Panel.setLayout(new GridLayout(6, 1)); // 6�ٿ� 6���� label�� �߰��ϱ� ����.
		lbl1P = new JLabel[6];
		for (int i = 0; i < 6; ++i) {
			lbl1P[i] = new JLabel(str1P[i]);
			lbl1P[i].setFont(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize));
			lbl1P[i].setForeground(Color.white);
			p1Panel.add(lbl1P[i]);
		} // �� label�� �� text�� string���� ������ �迭�� �ҷ��� ����.

		p1Panel.setOpaque(false); 

		p1Panel.setVisible(false); // ���� Constructor���� ������ ������ ������ �ʰ� �ϱ� ���ؼ� �񰡽÷� ����.

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
			lbl2_1P[i].setFont(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize));
			lbl2_1P[i].setForeground(Color.white);
			p2_1Panel.add(lbl2_1P[i]);
		} // for

		lbl2_2P = new JLabel[6];
		for (int i = 0; i < 6; ++i) {
			lbl2_2P[i] = new JLabel(str2_2P[i]);
			lbl2_2P[i].setFont(new Font("A��������", Font.PLAIN, TetrisConstants.Menu_fontsize));
			lbl2_2P[i].setForeground(Color.white);
			p2_2Panel.add(lbl2_2P[i]);
		} // for

		p2_1Panel.setOpaque(false);
		p2_2Panel.setOpaque(false);

		p2Panel.setOpaque(false);

		p2Panel.add(p2_1Panel);
		p2Panel.add(p2_2Panel);

		p2Panel.setVisible(false); // ���� Constructor���� ������ ������ ������ �ʰ� �ϱ� ���ؼ� �񰡽÷� ����.

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
				} // ȭ�鿡 ���� p1Panel�� �ƴ� p2Panel ǥ�� ���� �� p2Panel�� �����ϰ� p1Panel�� ����
				bP1 = true;
				bP2 = false;
			} else if (obj == btn2P) {
				if (bP2 == false) {

					p1Panel.setVisible(false);
					p1Panel.removeAll();

					makeP2();
					p2Panel.setVisible(true);
				} // ȭ�鿡 ���� p2Panel�� �ƴ� p1Panel ǥ�� ���� �� p1Panel�� �����ϰ� p2Panel�� ����
				bP2 = true;
				bP1 = false;
			} else if (obj == btnBack) {
				btn1P.setForeground(Color.white);
				btn2P.setForeground(Color.white);

				destroy();

				mainP.viewStartPanel(); // �ı��� MainPanl ���� ����

			} // MainPanel�� �ٽ� ǥ���ϱ� ���� ���� HowToPanel�� ��� ���� �����Ѵ�.

		} // actionPerformed()

	} // BtnListener class

} // HowToPanel class