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
	private BlockCube[][] bMatrix; // 블록 큐브 배열
	/**
	 * byte array saving current block's data
	 */
	private byte[][] bData; // 블록 데이터 배열
	/**
	 * current turn count (used as 2nd index of bMatrix array)
	 */
	private int iCurTurn; // 현재 회전 횟수 변수
	/**
	 * offset of current block in map
	 * 
	 * @see Point
	 */
	private Point iOffset; // 오프셋 변수
	/**
	 * block type
	 * 
	 * @see BlockConstants
	 */
	private int nBlockType; // 블록 타입 변수

	/**
	 * Point array, existing BlockCube Position of Block
	 * 
	 * @see Block
	 * @see BlockCube
	 * @see Point
	 */
	private Point[] pBlockPos; // 블록 위치 변수 (Point)

	/**
	 * Default Constructor
	 */
	Block() {
		this.setLayout(new GridLayout(4, 4)); // 그리드 레이아웃 4by4
		bMatrix = new BlockCube[4][4]; // 블록 큐브 생성
		iCurTurn = 0;
		iOffset = new Point(TetrisConstants.BLOCK_START_POS);
		this.setOpaque(false); // 변수 초기화 , 투명하게 지정
	}

	/**
	 * Constructor initialized with integer blockType
	 * 
	 * @param BlockType
	 * 
	 * @see BlockConstants
	 */
	Block(int BlockType) {
		this(); // 기본 생성자 사용
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
		} // BlockConstants 안에 저장된 블록 데이터 배열에 따라서 블록 위치를 지정해준다.
	}

	/**
	 * Constructor makes same block
	 * 
	 * @param b
	 */
	Block(Block b) {
		this(b.getBlockType()); // 타입 지정 생성자에 받은 인자의 타입을 넣어줘서 생성한다.
		this.setiOffset(b.getOffset());
		this.setpBlockPos(b.getBlockPos());
		this.setBounds(b.getBounds()); // 블록속성을 복사해서 지정해준다.

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
		} // 모든 BlockCube 들을 기본 생성자로 초기화해준다.
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
		// 회전수를 1 늘리고 각 블록의 최대 회전수의 나머지로 초기화한다.

		int pIdx = 0;

		this.removeAll(); // 전부 삭제한다.

		for (int y = 0; y < 4; y++) {
			int mask = Integer.parseInt("1000", 2);
			for (int x = 0; x < 4; x++) {
				if ((mask & bData[iCurTurn][y]) > 0 && pIdx < 4) {
					pBlockPos[pIdx++] = new Point(x, y);

				} else {
				}
				mask = mask >> 1;
			}
		} // 회전된 블록데이터를 기반으로 재구성한다.
	}

	/**
	 * turn block backward
	 */
	public void rTurn() {
		iCurTurn = (iCurTurn - 1) % bData.length; // 회전수를 1 줄이고 나머지로 초기화해준다.
		if (iCurTurn < 0)
			iCurTurn = bData.length + iCurTurn; // 회전수가 0보다 작아지면 최대크기에 현재 회전수를 더해준다.
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
		} // turn 메소드와 동일.

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
		} // 회전시키고 모양을 초기화해준다.
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
