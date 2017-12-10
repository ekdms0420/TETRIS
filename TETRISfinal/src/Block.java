import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * Class for showing and calculate blocks <br>
 * bMatrix parameter is consisted of <b>BlockCube</b> Array<br>
 * 
 * <style type="text/css"> .tg {border-collapse:collapse;border-spacing:0;} .tg
 * td{font-family:Arial, sans-serif;font-size:14px;padding:10px
 * 15px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
 * .tg th{font-family:Arial,
 * sans-serif;font-size:14px;font-weight:normal;padding:10px
 * 15px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
 * .tg .tg-yw4l{vertical-align:top} </style>
 * <table class="tg">
 * <tr>
 * <th class="tg-yw4l">0</th>
 * <th class="tg-yw4l">1</th>
 * <th class="tg-yw4l">2</th>
 * <th class="tg-yw4l">3</th>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">1</td>
 * <td class="tg-yw4l"></td>
 * <td class="tg-yw4l"></td>
 * <td class="tg-yw4l"></td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">2</td>
 * <td class="tg-yw4l"></td>
 * <td class="tg-yw4l"></td>
 * <td class="tg-yw4l"></td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">3</td>
 * <td class="tg-yw4l"></td>
 * <td class="tg-yw4l"></td>
 * <td class="tg-yw4l"></td>
 * </tr>
 * </table>
 * <br>
 * each cube is BlockCube class <br>
 * data of each block is in BlockConstants
 *
 * @author DongYeop Lee
 * @see BlockCube
 * @see BlockConstants
 * 
 * 
 */
public class Block extends JPanel {

	/**
	 * BlockCube matrix (4 x 4)
	 * 
	 * @see Block
	 */
	private BlockCube[][] bMatrix; // ��� ť�� �迭
	/**
	 * byte array saving current block's data
	 */
	private byte[][] bData; // ��� ������ �迭
	/**
	 * current turn count (used as 2nd index of bMatrix array)
	 */
	private int iCurTurn; // ���� ȸ�� Ƚ�� ����
	/**
	 * offset of current block in map
	 * 
	 * @see Point
	 */
	private Point iOffset; // ������ ����
	/**
	 * block type
	 * 
	 * @see BlockConstants
	 */
	private int nBlockType; // ��� Ÿ�� ����

	/**
	 * Point array, existing BlockCube Position of Block
	 * 
	 * @see Block
	 * @see BlockCube
	 * @see Point
	 */
	private Point[] pBlockPos; // ��� ��ġ ���� (Point)

	/**
	 * Default Constructor
	 */
	Block() {
		this.setLayout(new GridLayout(4, 4)); // �׸��� ���̾ƿ� 4by4
		bMatrix = new BlockCube[4][4]; // ��� ť�� ����
		iCurTurn = 0;
		iOffset = new Point(TetrisConstants.BLOCK_START_POS);
		this.setOpaque(false); // ���� �ʱ�ȭ , �����ϰ� ����
	}

	/**
	 * Constructor initialized with integer blockType
	 * 
	 * @param BlockType
	 * 
	 * @see BlockConstants
	 */
	Block(int BlockType) {
		this(); // �⺻ ������ ���
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
		} // BlockConstants �ȿ� ����� ��� ������ �迭�� ���� ��� ��ġ�� �������ش�.
	}

	/**
	 * Constructor makes same block
	 * 
	 * @param b
	 */
	Block(Block b) {
		this(b.getBlockType()); // Ÿ�� ���� �����ڿ� ���� ������ Ÿ���� �־��༭ �����Ѵ�.
		this.setiOffset(b.getOffset());
		this.setpBlockPos(b.getBlockPos());
		this.setBounds(b.getBounds()); // ��ϼӼ��� �����ؼ� �������ش�.

	}

	public void setPreview() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (bMatrix[i][j].isExist == true) {
					bMatrix[i][j].setIcon(new ImageIcon("./reseource/image/EMPTY.png"));
				}
			}
		}
	}

	/**
	 * set block empty
	 */
	public void setEmpty() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				bMatrix[y][x] = new BlockCube();
				this.add(bMatrix[y][x]);
			}
		} // ��� BlockCube ���� �⺻ �����ڷ� �ʱ�ȭ���ش�.
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

	/**
	 * turning block
	 */
	public void turn() {
		iCurTurn = (iCurTurn + 1) % bData.length;
		// ȸ������ 1 �ø��� �� ����� �ִ� ȸ������ �������� �ʱ�ȭ�Ѵ�.

		int pIdx = 0;

		this.removeAll(); // ���� �����Ѵ�.

		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);

				} else {
				}
				mask = mask >> 1;
			}
		} // ȸ���� ��ϵ����͸� ������� �籸���Ѵ�.
	}

	/**
	 * turn block backward
	 */
	public void rTurn() {
		iCurTurn = (iCurTurn - 1) % bData.length; // ȸ������ 1 ���̰� �������� �ʱ�ȭ���ش�.
		if (iCurTurn < 0)
			iCurTurn = bData.length + iCurTurn; // ȸ������ 0���� �۾����� �ִ�ũ�⿡ ���� ȸ������ �����ش�.
		int pIdx = 0;

		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);

				} else {
				}
				mask = mask >> 1;
			}
		} // turn �޼ҵ�� ����.

	}

	/**
	 * turn and shape the block
	 */
	public void turnShape() {
		int pIdx = 0;

		this.setVisible(false);
		removeAll();

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
		} // ȸ����Ű�� ����� �ʱ�ȭ���ش�.
		this.setVisible(true);
		this.validate();

	}

	/**
	 * move block up
	 */
	public void up() {
		this.iOffset.y--;
	}

	/**
	 * move block down
	 */
	public void down() {
		this.iOffset.y++;
	}

	/**
	 * move block right
	 */
	public void right() {
		this.iOffset.x++;
	}

	/**
	 * move block left
	 */
	public void left() {
		this.iOffset.x--;
	}
}
