import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {

	private TetrisPanel tPanel;

	private int[][] mapArray;
	private GameThread gameRun;
	private Thread thread;
	private JPanel floatBlock;
	private JPanel backMap;
	private JPanel droppedBlock;

	private BufferedImage background;

	private Block b;
	private Point[] blockPos;
	private Point blockOffset;

	private BlockCube[][] map;

	private boolean isPause;

	public static int WALL = 10;
	public static int EMPTY = -1;
	public static int OBSTACLE = 20;
	public static int BLOCK = 30;

	private boolean isGameOver;

	public Game(TetrisPanel t) {
		this.tPanel = t;
		this.setLayout(null);
		this.setOpaque(false);
		mapArray = new int[TetrisConstants.MAP_ARRAY_YLEN][TetrisConstants.MAP_ARRAY_XLEN];

		try {
			background = ImageIO.read(new File("./resource/image/TL.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.backMap = new JPanel() {
			public void paintComponent(Graphics page) {
				page.drawImage(background, -1, 3, null);
				setOpaque(false);
				super.paintComponent(page);
			}
		};

		backMap.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH + 5, TetrisConstants.MAP_HEIGHT + 10));
		backMap.setBounds(0, 0, TetrisConstants.MAP_WIDTH + 5, TetrisConstants.MAP_HEIGHT + 30);

		backMap.setVisible(true);
		backMap.setOpaque(false);

		this.add(backMap);
		this.floatBlock = new JPanel();
		floatBlock.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT));
		floatBlock.setBounds(0, 0, TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT);

		floatBlock.setLayout(null);
		floatBlock.setOpaque(false);
		backMap.add(floatBlock);
		droppedBlock = new JPanel();
		this.add(droppedBlock);

		droppedBlock.setBounds(0, 0, TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT);
		droppedBlock.setLayout(new GridLayout(TetrisConstants.MAP_ARRAY_YLEN, TetrisConstants.MAP_ARRAY_XLEN));
		droppedBlock.setOpaque(false);
		map = new BlockCube[TetrisConstants.MAP_ARRAY_YLEN][TetrisConstants.MAP_ARRAY_XLEN];
		for (int i = 0; i < TetrisConstants.MAP_ARRAY_YLEN; i++) {
			for (int j = 0; j < TetrisConstants.MAP_ARRAY_XLEN; j++) {
				if (j == 0 || i == 0 || j == TetrisConstants.MAP_ARRAY_XLEN - 1
						|| i == TetrisConstants.MAP_ARRAY_YLEN - 1) {
					mapArray[i][j] = WALL;

				} else {
					mapArray[i][j] = EMPTY;
				}
				System.out.print(mapArray[i][j] + " ");
				map[i][j] = new BlockCube();
				droppedBlock.add(map[i][j]);

			}
			System.out.println();

		}

		gameRun = new GameThread(this);
		thread = new Thread(gameRun);

	}

	public void breakLine() {
		for (int i = 1; i < TetrisConstants.MAP_ARRAY_YLEN - 1; i++) {
			boolean flag = true;
			for (int j = 1; j < TetrisConstants.MAP_ARRAY_XLEN - 1; j++) {
				if (mapArray[i][j] < 0 || mapArray[i][j] > 7) {
					flag = false;
				}
			}

			if (flag) {
				for (int j = 1; j < TetrisConstants.MAP_ARRAY_XLEN - 1; j++) {
					mapArray[i][j] = EMPTY;

				}

				for (int j = i; j > 0; j--) {
					for (int k = 1; k < TetrisConstants.MAP_ARRAY_XLEN - 1; k++) {
						if (j == i) {
							mapArray[j][k] = -1;
						} else {
							mapArray[j + 1][k] = mapArray[j][k];
						}
					}
				}
				i--;
				System.out.println(i);

			}
		}
		this.printArray();

		resetMap();
	}

	public void resetMap() {

		for (int i = 1; i < TetrisConstants.MAP_ARRAY_YLEN - 1; i++) {
			for (int j = 1; j < TetrisConstants.MAP_ARRAY_XLEN - 1; j++) {
				map[i][j].setBlockColor(mapArray[i][j]);
				if (mapArray[i][j] == -1)
					map[i][j].setVisible(false);
			}
		}

		droppedBlock.validate();

	}

	public void spawnBlock(Block b) {
		this.b = b;

		blockOffset = b.getOffset();

		int bSize = TetrisConstants.BLOCKSIZE;
		b.setBounds(bSize * (blockOffset.x), bSize * (blockOffset.y), bSize * 4, bSize * 4);

		floatBlock.add(b);
		this.revalidate();
	}

	public void setNextBlock(int blk) {
		tPanel.setNext(blk);
	}

	public boolean gameOverCheck() {
		isGameOver = false;
		for (int i = 1; i < TetrisConstants.MAP_ARRAY_XLEN - 1; i++) {
			if (0 <= mapArray[1][i] && mapArray[1][i] <= 7) {
				isGameOver = true;
				thread.stop();

				break;
			}
		}
		return isGameOver;
	}

	public boolean isGameFin() {
		return this.isGameOver;
	}

	public void setHoldBlock(int blk) {
		tPanel.setHold(blk);
	}

	public boolean move(int dir) {
		boolean flag = true;

		switch (dir) {
		case TetrisConstants.down:
			down();
			break;
		case TetrisConstants.right:
			right();
			break;
		case TetrisConstants.left:
			left();
			break;
		}
		if (moveException()) {
			switch (dir) {
			case TetrisConstants.down:
				up();
				blockPos = b.getBlockPos();
				blockOffset = b.getOffset();

				for (int i = 0; i < blockPos.length; i++) {
					mapArray[blockPos[i].y + blockOffset.y][blockPos[i].x + blockOffset.x] = b.getBlockType();
					b.setVisible(false);
					floatBlock.remove(b);

					resetMap();
				}

				return false;
			case TetrisConstants.right:
				left();
				break;
			case TetrisConstants.left:
				right();
				break;
			}

		}

		blockOffset = b.getOffset();
		int bSize = TetrisConstants.BLOCKSIZE;
		b.setBounds(bSize * (blockOffset.x), bSize * (blockOffset.y), bSize * 4, bSize * 4);
		b.revalidate();
		return true;

	}

	public boolean moveException() {
		this.blockPos = b.getBlockPos();
		this.blockOffset = b.getOffset();
		for (int i = 0; i < blockPos.length; i++) {
			int data = mapArray[blockPos[i].y + blockOffset.y][blockPos[i].x + blockOffset.x];
			if (data == WALL || (data >= 0 && data <= 7)) {
				return true;
			}
		}
		return false;
	}

	public void up() {
		b.up();
	}

	public void down() {
		b.down();
	}

	public void right() {
		b.right();
	}

	public void left() {
		b.left();
	}

	public void gameStart() {
		thread.start();
	}

	public void hold() {
		this.gameRun.hold();
	}

	public void removeBlock() {
		b.setVisible(false);
		floatBlock.remove(b);
		floatBlock.revalidate();
	}

	public void setCurrentBlock(int blk) {
		thread.suspend();
		b.setVisible(false);
		blockOffset = b.getOffset();
		int bSize = TetrisConstants.BLOCKSIZE;
		floatBlock.remove(b);
		b = new Block(blk);
		b.setiOffset(blockOffset);
		b.setBounds(bSize * (blockOffset.x), bSize * (blockOffset.y), bSize * 4, bSize * 4);
		floatBlock.add(b);

		floatBlock.validate();

		thread.resume();

	}

	public void pause() {
		if (isPause) {
			thread.resume();
			isPause = false;
		} else {
			thread.suspend();
			isPause = true;
		}
	}

	public void turn() {
		b.turn();
		if (turnException()) {
			b.rTurn();
		}
		b.turnShape();
	}

	public boolean turnException() {
		this.blockPos = b.getBlockPos();
		this.blockOffset = b.getOffset();
		for (int i = 0; i < blockPos.length; i++) {
			int data = mapArray[blockPos[i].y + blockOffset.y][blockPos[i].x + blockOffset.x];
			if (data == WALL || (data >= 0 && data <= 7)) {
				return true;
			}
		}
		return false;
	}

	public void fall() {
		while (true) {
			if (!move(TetrisConstants.down))
				break;
		}
	}

	public void printArray() {
		for (int i = 0; i < this.mapArray.length; i++) {
			for (int j = 0; j < this.mapArray[i].length; j++) {
				System.out.print(mapArray[i][j] + " ");
			}
			System.out.println();
		}
	}
}
