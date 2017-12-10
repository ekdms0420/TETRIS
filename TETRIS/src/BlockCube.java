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

		this.setPreferredSize(new Dimension(TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE)); // 블록 크기 지정

		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png")); // 아이콘 지정
		this.setVisible(isExist); // 존재하면 보이게 지정
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
		} // BlockConstants 에서 정의한 정수 변수로 블록 타입에 따라서 색 지정해준다.
		this.isExist = isExist;

		this.setPreferredSize(new Dimension(TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE));
		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png")); // 크기 지정, 이미지 지정
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
		} // 블록 종류에 따라 색 지정

		if (blockType != -1)
			isExist = true; // 블록 타입이 -1이 아니면 존재하게 지정
		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png")); // 아이콘 지정
		this.setVisible(isExist);
		this.validate(); // 새로고침

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
