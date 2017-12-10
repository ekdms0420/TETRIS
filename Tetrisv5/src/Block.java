import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;

public class Block extends JPanel {

	private BlockCube[][] bMatrix;
	private byte[][] bData;
	private int iCurTurn;
	private Point iOffset;
	private int nBlockType;

	private Point[] pBlockPos;

	Block() {
		this.setLayout(new GridLayout(4, 4));
		bMatrix = new BlockCube[4][4];
		iCurTurn = 0;
		iOffset = new Point(4, 0);
		this.setOpaque(false);
	}

	Block(int BlockType) {
		this();
		this.nBlockType = BlockType;
		bData = BlockConstants.getBlockArr(nBlockType);

		int pIdx = 0;
		pBlockPos = new Point[4];
		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);
					bMatrix[y][x] = new BlockCube(nBlockType, true);

				} else {
					bMatrix[y][x] = new BlockCube();
				}
				this.add(bMatrix[y][x]);
				mask = mask >> 1;
			}
		}
	}

	public void setEmpty() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				bMatrix[y][x] = new BlockCube();
				this.add(bMatrix[y][x]);
			}
		}
	}

	public int getBlockType() {
		return this.nBlockType;
	}

	public Point getOffset() {
		return this.iOffset;

	}

	public Point[] getBlockPos() {
		return this.pBlockPos;
	}

	public BlockCube[][] getBlockCube() {
		return this.bMatrix;
	}

	public void setiOffset(Point iOffset) {
		this.iOffset = iOffset;
	}

	public void setnBlockType(int nBlockType) {
		this.nBlockType = nBlockType;
	}

	public void setpBlockPos(Point[] pBlockPos) {
		this.pBlockPos = pBlockPos;
	}

	public void turn() {
		iCurTurn = (iCurTurn + 1) % bData.length;

		int pIdx = 0;

		// for (int y = 0; y < 4; y++) {
		// for (int x = 0; x < 4; x++) {
		// this.remove(bMatrix[y][x]);
		// }
		// }
		this.removeAll();

		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);

				} else {
				}
				mask = mask >> 1;
			}
		}
	}

	public void rTurn() {
		iCurTurn = (iCurTurn - 1) % bData.length;
		if (iCurTurn < 0)
			iCurTurn = bData.length + iCurTurn;
		int pIdx = 0;

		// for (int y = 0; y < 4; y++) {
		// for (int x = 0; x < 4; x++) {
		// this.remove(bMatrix[y][x]);
		// }
		// }

		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);

				} else {
				}
				mask = mask >> 1;
			}
		}

	}

	public void turnShape() {
		int pIdx = 0;

		this.setVisible(false);
		// for (int y = 0; y < 4; y++) {
		// for (int x = 0; x < 4; x++) {
		// this.remove(bMatrix[y][x]);
		// }
		// }
		this.removeAll();

		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);
					bMatrix[y][x] = new BlockCube(nBlockType, true);

				} else {
					bMatrix[y][x] = new BlockCube();
				}
				this.add(bMatrix[y][x]);
				mask = mask >> 1;
			}
		}
		this.setVisible(true);
		this.validate();

	}

	public void up() {
		this.iOffset.y--;
	}

	public void down() {
		this.iOffset.y++;
	}

	public void right() {
		this.iOffset.x++;
	}

	public void left() {
		this.iOffset.x--;
	}
}
