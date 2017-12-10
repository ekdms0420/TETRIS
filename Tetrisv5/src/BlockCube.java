import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BlockCube extends JLabel {

	String strBlockType;
	boolean isExist;

	BlockCube() {
		strBlockType = "TRANSPARENT";
		isExist = false;

		this.setPreferredSize(new Dimension(TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE));

		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png"));
		this.setVisible(isExist);
	}

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
		}
		this.isExist = isExist;

		this.setPreferredSize(new Dimension(TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE));
		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png"));
		this.setVisible(true);
	}

	public String getStrColor() {
		return strBlockType;
	}

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
		}

		if (blockType != -1)
			isExist = true;
		this.setIcon(new ImageIcon("./resource/image/" + strBlockType + ".png"));
		this.setVisible(isExist);
		this.validate();

	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

}
