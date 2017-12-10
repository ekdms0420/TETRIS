import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * 
 * <b>Constants for TetrisGame</b> <br>
 * <br>
 * 
 * static final variables that is relative to tetris logic
 * 
 * @see BlockConstants
 * 
 * @author DongYeop Lee
 *
 */
public class TetrisConstants {
	/** Height of back logic array */
	public static final int MAP_ARRAY_YLEN = 22;
	/** Width of back logic array */
	public static final int MAP_ARRAY_XLEN = 12;
	/**
	 * Point of start position of block
	 * 
	 * @see Point
	 */
	public static final Point BLOCK_START_POS = new Point(4, 0);

	/**
	 * screen size of current device
	 * 
	 * @see Dimension
	 * @see Toolkit
	 */
	public static final Dimension res = Toolkit.getDefaultToolkit().getScreenSize();// Toolkit을 이용하여 스크린의 크기를 저장

	/**
	 * BlockCube size
	 * 
	 * @see BlockCube
	 */
	public static final int BLOCKSIZE = res.width * 40 / 1920;
	/**
	 * Width of map
	 * 
	 * @see Game
	 */
	public static final int MAP_WIDTH = BLOCKSIZE * 12;
	/**
	 * Height of map
	 * 
	 * @see Game
	 */
	public static final int MAP_HEIGHT = BLOCKSIZE * 22;
	/**
	 * Default Font Size of game
	 */
	public static final int Menu_fontsize = res.height * 40 / 1080;
	// 해상도에 맞춰서 블록 큐브, 맵 크기, 폰트 크기를 지정함

	/**
	 * Tetris block set's size
	 * 
	 * @see BlockGenerator
	 */
	public static final int RANDOMSETSIZE = 7;

	public static final int down = 1;
	public static final int up = -1;
	public static final int right = 5;
	public static final int left = -5; // move 메소드에서 사용할 up down left right 상수로 지정

	// 해상도에 맞춰서 이미지 크기를 리사이징 할때 사용할 원본 파일 이름과, 바꿀 파일 이름의 String 배열
	public static final String[] original_filename = { "BLUE_d.png", "CYAN_d.png", "EMPTY_d.png", "GREEN_d.png",
			"ORANGE_d.png", "PURPLE_d.png", "RED_d.png", "YELLOW_d.png", "TetrisLayout_d.png", "pauseMenu_d.png",
			"Background_d.jpg" };
	public static final String[] target_filename = { "BLUE.png", "CYAN.png", "EMPTY.png", "GREEN.png", "ORANGE.png",
			"PURPLE.png", "RED.png", "YELLOW.png", "TL.png", "pauseMenu.png", "Background.jpg" };

	// 레벨에 따른 스레드의 sleep 시간과 level 상승의 역치값을 지정
	/**
	 * sleep time's array
	 * 
	 * @see GameThread
	 * @see Thread
	 * 
	 */
	public static final int sleepLv[] = { 800, 600, 400, 300, 200, 100, 80, 50 };

	/**
	 * threshold of level up in game
	 * 
	 * @see GameThread
	 * @see Game
	 */
	public static final int[] levelThreshold = { 800, 2000, 6000, 12000, 30000, 100000, 1500000 };

}
