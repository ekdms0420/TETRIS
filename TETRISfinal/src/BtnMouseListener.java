import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * Button Listener for every button in game
 * 
 * @author DongYeop Lee
 *
 */
class BtnMouseListener implements MouseListener {

	/**
	 * name of font used in button
	 */
	String name;
	/**
	 * style of font used in button
	 */
	int style;
	/**
	 * size of font used in button
	 */
	int size;

	/**
	 * Constructor, copy the attribute of button font
	 * 
	 * @param current
	 */
	public BtnMouseListener(Font current) {
		name = current.getName();
		style = current.getStyle();
		size = current.getSize();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		JButton btnMenu = (JButton) event.getSource();

		btnMenu.setFont(new Font(name, Font.BOLD, size));

		btnMenu.setForeground(Color.yellow); // 버튼위에 마우스 올리면 노란색, 굵게 설정
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		JButton btnMenu = (JButton) event.getSource();
		btnMenu.setFont(new Font(name, Font.PLAIN, size));
		btnMenu.setForeground(Color.white); // 버튼위에서 마우스 벗어나면 흰색, 보통으로 설정
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