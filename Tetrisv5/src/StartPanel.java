import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {

	private JButton[] btnArray;
	private final String[] strButtonName = { "1 PLAYER", "2 PLAYER", "How To Play?", "EXIT" };
	private JPanel menuGridPanel, logoPanel;
	private JLabel lblLogo;
	private MainPanel mainLink;

	StartPanel(MainPanel mainP) {
		super();
		this.mainLink = mainP;
		this.setLayout(new GridLayout(2, 1));
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./resource/font/A으라차차.TTF")));
		} catch (Exception e) {

		}

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

		logoPanel = new JPanel();
		lblLogo = new JLabel("T E T R I S");
		lblLogo.setForeground(Color.cyan);

		lblLogo.setFont(new Font("A으라차차", Font.BOLD, 120));
		lblLogo.setPreferredSize(new Dimension(res.width, res.height / 2));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		logoPanel.add(lblLogo);
		logoPanel.setOpaque(false);
		logoPanel.setPreferredSize(new Dimension(res.width, res.height / 3));
		this.add(logoPanel);

		menuGridPanel = new JPanel();
		menuGridPanel.setLayout(new GridLayout(4, 1));
		menuGridPanel.setPreferredSize(new Dimension(res.width, res.height / 2));
		menuGridPanel.setOpaque(false);
		// menuGridPanel.setOpaque(false);

		this.add(menuGridPanel);
		btnArray = new JButton[4];
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i] = new JButton(strButtonName[i]);
			btnArray[i].setOpaque(false);
			btnArray[i].setBorderPainted(false);
			btnArray[i].setContentAreaFilled(false);
			btnArray[i].setMargin(new Insets(0, 0, 50, 0));
			btnArray[i].setForeground(Color.white);
			btnArray[i].setFont(new Font("A으라차차", Font.PLAIN, 60));
			btnArray[i].addMouseListener(new btnMouseListener());
			menuGridPanel.add(btnArray[i]);
		}

		btnArray[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroy();
				mainP.P1();
			}
		});

		btnArray[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroy();
				mainP.P2();
			}

		});

		btnArray[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroy();
				mainP.HowTo();
			}

		});

		btnArray[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		this.setOpaque(false);
	}

	private class btnMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			// TODO Auto-generated method stub
			JButton btnMenu = (JButton) event.getSource();
			btnMenu.setFont(new Font("A으라차차", Font.BOLD, 60));
			btnMenu.setForeground(Color.yellow);
		}

		@Override
		public void mouseExited(MouseEvent event) {
			// TODO Auto-generated method stub
			JButton btnMenu = (JButton) event.getSource();
			btnMenu.setFont(new Font("A으라차차", Font.PLAIN, 60));
			btnMenu.setForeground(Color.white);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO Auto-generated method stub

		}

	}

	public void destroy() {

		this.setVisible(false);
		this.removeAll();
		// System.out.println(this.listenerList.toString());
	}

}
