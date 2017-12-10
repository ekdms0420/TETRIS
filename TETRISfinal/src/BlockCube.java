import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class of each cube of tetris block <br>
 * 
 * 
 * 
 * <img alt ="CANT READ IMAGE FILE" src="../resource/image/BLUE_d.png" width
 * ="40" height = "40" style = />
 * 
 * @see Block
 * @author DongYeop Lee
 *
 */
public class BlockCube extends JLabel {

	/**
	 * Blocktype saved in String used for load image file
	 * 
	 * @see BlockConstants
	 */
	String strBlockType;
	/**
	 * boolean data for check if is exist
	 */
	boolean isExist;

	/**
	 * Default Constructor
	 * 
	 */
	BlockCube() {
		strBlockType = "TRANSPARENT";
		isExist = false;

		this.setPreferredSize(new Dimension(TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE)); // ��� ũ�� ����

		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png")); // ������ ����
		this.setVisible(isExist); // �����ϸ� ���̰� ����
	}// BlockCube()

	/**
	 * Constructor of BlockCube
	 * 
	 * @param blockType
	 *            type of block derived from TetrisConstants
	 * @param isExist
	 */
	BlockCube(int blockType, boolean isExist) {

		switch (blockType) {
		case BlockConstants.IMINO:
			strBlockType = "CYAN";
			break;
		case BlockConstants.JMINO:
			strBlockType = "BLUE";
			break;
		case BlockConstants.LMINO:
			strBlockType = "GREEN";
			break;
		case BlockConstants.OMINO:
			strBlockType = "YELLOW";
			break;
		case BlockConstants.SMINO:
			strBlockType = "RED";
			break;
		case BlockConstants.TMINO:
			strBlockType = "ORANGE";
			break;
		case BlockConstants.ZMINO:
			strBlockType = "PURPLE";
			break;
		} // BlockConstants ���� ������ ���� ������ ��� Ÿ�Կ� ���� �� �������ش�.
		this.isExist = isExist;

		this.setPreferredSize(new Dimension(TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE));
		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png")); // ũ�� ����, �̹��� ����
		this.setVisible(true);
	}

	public String getStrColor() {
		return strBlockType;
	}

	/**
	 * method for set blocks color
	 * 
	 * @param blockType
	 *            integer from BlockConstants
	 * 
	 * @see BlockConstants
	 */
	public void setBlockColor(int blockType) {
		switch (blockType) {
		case -1:
			strBlockType = "TRANSPARENT";
			isExist = false;
		case BlockConstants.IMINO:
			strBlockType = "CYAN";
			break;
		case BlockConstants.JMINO:
			strBlockType = "BLUE";
			break;
		case BlockConstants.LMINO:
			strBlockType = "GREEN";
			break;
		case BlockConstants.OMINO:
			strBlockType = "YELLOW";
			break;
		case BlockConstants.SMINO:
			strBlockType = "RED";
			break;
		case BlockConstants.TMINO:
			strBlockType = "ORANGE";
			break;
		case BlockConstants.ZMINO:
			strBlockType = "PURPLE";
			break;
		case 100:
			strBlockType = "EMPTY";
			break;
		} // ��� ������ ���� �� ����

		if (blockType != -1)
			isExist = true; // ��� Ÿ���� -1�� �ƴϸ� �����ϰ� ����
		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png")); // ������ ����
		this.setVisible(isExist);
		this.validate(); // ���ΰ�ħ

	}

	/**
	 * getter of isExist
	 * 
	 * @return isExist
	 */
	public boolean isExist() {
		return isExist;
	}

	/**
	 * setter of isExist
	 * 
	 * @param isExist
	 */
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

}
