import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class TetrisConstants {
	public static final Point MAP_POS = new Point(20, 20);
	public static final int MAP_ARRAY_YLEN = 22;
	public static final int MAP_ARRAY_XLEN = 12;
	public static final Point BLOCK_START_POS = new Point(3, 0);

	public static final int BLOCKSIZE = 40;
	public static final int MAP_WIDTH = BLOCKSIZE * 12;
	public static final int MAP_HEIGHT = BLOCKSIZE * 22;

	public static final int RANDOMSETSIZE = 7;

	public static final int down = 1;
	public static final int up = -1;
	public static final int right = 5;
	public static final int left = -5;

	public static final int sleepLv1 = 400;
	public static final Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
}
