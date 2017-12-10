import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Main game logic
 * 
 * @author DongYeop Lee
 *
 */
public class Game extends JPanel {

	/** parent Panel to upCall */
	private TetrisPanel tPanel;
	/** integer array for map */
	private int[][] mapArray;
	/** runnable */
	private GameThread gameRun;
	/** Thread to control gameRun */
	private Thread thread;
	/** Panel display current active block */
	private JPanel floatBlock;
	/** panel display back Map */
	private JPanel backMap;
	/** panel display block dropped */
	private JPanel droppedBlock;

	/** Buffered Image for background image (RGBA) */
	private BufferedImage background;

	/** current active block */
	private Block b;

	/** position's of BlockCube */
	private Point[] blockPos;
	/** Position of current active block */
	private Point blockOffset;

	/** BlockCube array for dropped block */
	private BlockCube[][] map;

	/** flag to see the game is paused */
	private boolean isPause;

	private int WALL = 10;
	private int EMPTY = -1;
	private int OBSTACLE = 20;
	private int BLOCK = 30; // 배열에서 사용할 블록 상태표시를 위한 변수

	/** flag to check gameOver */
	private boolean isGameOver;

	/**
	 * Constructor
	 * 
	 * @param t
	 *            parent panel
	 */
	public Game(TetrisPanel t) {
		this.tPanel = t;
		this.setLayout(null);
		this.setOpaque(false);

		// mapArray 생성
		mapArray = new int[TetrisConstants.MAP_ARRAY_YLEN][TetrisConstants.MAP_ARRAY_XLEN];

		// 배경화면 draw
		try {
			background = ImageIO.read(new File("./resource/image/TL.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.backMap = new JPanel() {
			public void paintComponent(Graphics page) {
				page.drawImage(background, -1, 3, null);

				super.paintComponent(page);
			}
		};

		// 모든 panel 크기 및 바운드 지정
		backMap.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH + 5, TetrisConstants.MAP_HEIGHT + 10));
		backMap.setBounds(0, 0, TetrisConstants.MAP_WIDTH + 5, TetrisConstants.MAP_HEIGHT + 30);

		backMap.setVisible(true);
		backMap.setOpaque(false);

		this.add(backMap);
		this.floatBlock = new JPanel();
		floatBlock.setPreferredSize(new Dimension(TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT));
		floatBlock.setBounds(0, 0, TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT);
		floatBlock.setMinimumSize(new Dimension(TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT));

		floatBlock.setLayout(null);
		floatBlock.setOpaque(false);
		backMap.add(floatBlock);
		droppedBlock = new JPanel();
		this.add(droppedBlock);

		droppedBlock.setBounds(0, 0, TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT);
		droppedBlock.setLayout(new GridLayout(TetrisConstants.MAP_ARRAY_YLEN, TetrisConstants.MAP_ARRAY_XLEN));
		droppedBlock.setOpaque(false);

		// map 배열 생성 및 초기화
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
		// runnable 클래스 생성하고 스레드 연결
		gameRun = new GameThread(this);
		thread = new Thread(gameRun);

	}

	/**
	 * method for check line break
	 */
	public void breakLine() {
		int cnt = 0;
		for (int i = 1; i < TetrisConstants.MAP_ARRAY_YLEN - 1; i++) {
			boolean flag = true;
			for (int j = 1; j < TetrisConstants.MAP_ARRAY_XLEN - 1; j++) {
				if (mapArray[i][j] < 0 || mapArray[i][j] > 7) {
					flag = false;

				}
			} // 한줄이 되어있는지 확인

			// 되어있다면 삭제
			if (flag) {
				cnt++;
				for (int j = 1; j < TetrisConstants.MAP_ARRAY_XLEN - 1; j++) {
					mapArray[i][j] = EMPTY;
				}

				for (int j = i; j > 0; j--) {
					for (int k = 1; k < TetrisConstants.MAP_ARRAY_XLEN - 1; k++) {
						if (j == i) {
							mapArray[j][k] = EMPTY;
						} else {
							mapArray[j + 1][k] = mapArray[j][k];
						}
					}
				}
				i--;
				System.out.println(i);

			}
		}
		if (cnt != 0)
			tPanel.getLblScore().comboBreak(cnt); // 점수 반영

		this.printArray();

		resetMap(); // 맵 새로고침
	}

	/**
	 * refresh the map
	 */
	public void resetMap() {

		for (int i = 1; i < TetrisConstants.MAP_ARRAY_YLEN - 1; i++) {
			for (int j = 1; j < TetrisConstants.MAP_ARRAY_XLEN - 1; j++) {
				map[i][j].setBlockColor(mapArray[i][j]);

				if (mapArray[i][j] == -1)
					map[i][j].setVisible(false);
			}
		} // mapArray의 데이터에 따라서 map 배열 초기화해준다.

		droppedBlock.validate(); // droppedBlock 새로고침

	}

	public void spawnBlock(Block b) {
		this.b = b;

		blockOffset = b.getOffset();

		int bSize = TetrisConstants.BLOCKSIZE;
		b.setBounds(bSize * (blockOffset.x) - 4, bSize * (blockOffset.y) - 4, bSize * 4, bSize * 4); // 블록 위치 지정

		floatBlock.add(b); // floatBlock 패널에 추가
		this.revalidate(); // 새로고침
	}

	/**
	 * change block displayed in next BlockPanel
	 * 
	 * @param blk
	 */
	public void setNextBlock(int blk) {
		tPanel.setNext(blk);
	}

	/**
	 * check if the game is over and return the boolean data
	 * 
	 * @return isGameOver
	 */
	public boolean gameOverCheck() {
		isGameOver = false;
		for (int i = 1; i < TetrisConstants.MAP_ARRAY_XLEN - 1; i++) {
			if (0 <= mapArray[1][i] && mapArray[1][i] <= 7) {
				tPanel.gameOver(); // 맨 윗줄에 블록이 있으면 게임 오버
				isGameOver = true; // 게임오버 메소드 호출하고, 게임 오버 true로 변경
				if (tPanel.isTwo() == 0) {
					tPanel.getParentPanel().getMainPanel().getParentFrame().dispose(); // 창 종료
					ScoreBoard scoreBoard = new ScoreBoard(tPanel.getLblScore().getScore()); // 스코어 반영
					scoreBoard.gameScore(); // 게임스코어 메소드 실행
				} else {
					tPanel.getParentPanel().whoWin(); // 2player의 경우 이긴사람 출력
				}
				thread.stop(); // 스레드 종료
				break;
			}
		}
		return isGameOver; // 게임 오버 여부 리턴
	}

	/**
	 * 
	 * @return isGameOver
	 */
	public boolean isGameFin() {
		return this.isGameOver;
	}

	public void setHoldBlock(int blk) {
		tPanel.setHold(blk);

	}

	/**
	 * get the parent Panel
	 * 
	 * @return tPanel
	 */
	public TetrisPanel getFather() {
		return this.tPanel;
	}

	/**
	 * move block determined by dir parameter
	 * 
	 * @param dir
	 * @return is exception occured when moving
	 */
	public boolean move(int dir) {
		boolean flag = true;

		switch (dir) { // 각각 dir에 해당하는 메소드 호출하고,
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
		if (moveException()) { // 예외가 있는경우 각각 처리
			switch (dir) {
			case TetrisConstants.down:
				// 아래로 가는데 예외가 있는경우, 블록 제거하고, mapArray에 반영하고 새로고침, false리턴
				up();
				tPanel.getLblScore().addScore(20);
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
			// 오른쪽 왼쪽으로 갔을때 exception 발생한 경우 반대쪽으로 한번 다시 움직여주는 것으로 움직임 상쇄
			}

		}

		blockOffset = b.getOffset();
		int bSize = TetrisConstants.BLOCKSIZE;
		b.setBounds(bSize * (blockOffset.x) - 4, bSize * (blockOffset.y) - 4, bSize * 4, bSize * 4); // 움직이는데 성공했다면 위치
																										// 설정

		b.revalidate();
		return true; // true 반환

	}

	/**
	 * check if there is exception when moving or turning
	 * 
	 * @return is Exception occured
	 */
	public boolean moveException() {
		this.blockPos = b.getBlockPos();
		this.blockOffset = b.getOffset();
		for (int i = 0; i < blockPos.length; i++) {
			int data = mapArray[blockPos[i].y + blockOffset.y][blockPos[i].x + blockOffset.x];
			if (data == WALL || (data >= 0 && data <= 7)) {
				return true;
			} // 위치를 받아서 벽이거나 active블럭이 아닌경우 true 리턴
		}
		return false; // 문제가 없을경우 false 리턴
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

	/**
	 * start the thread connected to runnable class
	 */
	public void gameStart() {
		thread.start();
	}

	/**
	 * method to hold block
	 */
	public void hold() {
		this.thread.suspend(); // 스레드 일시정지
		this.gameRun.hold(); // runnable 클래스에서 hold 실행
		if (holdException()) { // 예외가 발생한경우
			Point temp = b.getOffset();
			temp.x = 4;
			b.setiOffset(temp); // 블럭을 해당 줄에서 가운데로 옮겨준다.
		}
		this.thread.resume(); // 스레드 재개

	}

	/**
	 * check if there is exception when holding the block
	 * 
	 * @return is Exception occured when hold the block
	 */
	public boolean holdException() {
		this.blockPos = b.getBlockPos();
		this.blockOffset = b.getOffset();
		for (int i = 0; i < blockPos.length; i++) {
			int data = mapArray[blockPos[i].y + blockOffset.y][blockPos[i].x + blockOffset.x];
			if (data == WALL || (data >= 0 && data <= 7)) {
				return true;
			} // moveException과 동일한 처리
		}
		return false;
	}

	/**
	 * remove block from floatBlock
	 */
	public void removeBlock() {
		b.setVisible(false);
		floatBlock.remove(b);
		floatBlock.revalidate();
	}

	/**
	 * set current active block to blocktype of blk
	 * 
	 * @param blk
	 */
	public void setCurrentBlock(int blk) {
		thread.suspend();
		b.setVisible(false);
		blockOffset = b.getOffset();
		int bSize = TetrisConstants.BLOCKSIZE;
		floatBlock.remove(b);
		b = new Block(blk);
		b.setiOffset(blockOffset);
		floatBlock.add(b);

		floatBlock.validate();

		thread.resume();
	}

	/**
	 * pause the game by suspend the thread
	 */
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

		if (moveException()) {
			b.rTurn();
		}

		b.turnShape();

	}

	/**
	 * Hard drop method
	 */
	public void fall() {
		while (true) {
			if (!move(TetrisConstants.down))
				break;
		}
	}

	/**
	 * print the mapArray data in console (for debugging)
	 */
	public void printArray() {
		for (int i = 0; i < this.mapArray.length; i++) {
			for (int j = 0; j < this.mapArray[i].length; j++) {
				System.out.print(String.format("%5d", mapArray[i][j]));
			}
			System.out.println();
		}
	}
}
