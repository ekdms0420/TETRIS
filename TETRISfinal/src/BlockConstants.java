
/**
 * Constants for Block
 * 
 * @see Block
 * @see BlockCube
 * @see BlockGenerator
 * @author DongYeop Lee
 *
 */
public class BlockConstants {

	/**
	 * array of hex data consists block
	 */
	public static final byte[][][] BLOCK_ARR = {
			// 0000
			// 0110
			// 0110
			// 0000
			{ { 0x0, 0x6, 0x6, 0x0 } },
			// 0000 1000
			// 0110 1100
			// 1100 0100
			// 0000 0000
			{ { 0x0, 0x6, 0xc, 0x0 }, { 0x8, 0xc, 0x4, 0x0 } },
			// 0000 0100
			// 1100 1100
			// 0110 1000
			// 0000 0000
			{ { 0x0, 0xc, 0x6, 0x0 }, { 0x4, 0xc, 0x8, 0x0 } },
			// 0000 0100 0010 1100
			// 1110 0100 1110 0100
			// 1000 0110 0000 0100
			// 0000 0000 0000 0000
			{ { 0x0, 0xe, 0x8, 0x0 }, { 0x4, 0x4, 0x6, 0x0 }, { 0x2, 0xe, 0x0, 0x0 }, { 0xc, 0x4, 0x4, 0x0 } },
			// 0000 0110 1000 0100
			// 1110 0100 1110 0100
			// 0010 0100 0000 1100
			// 0000 0000 0000 0000
			{ { 0x0, 0xe, 0x2, 0x0 }, { 0x6, 0x4, 0x4, 0x0 }, { 0x8, 0xe, 0x0, 0x0 }, { 0x4, 0x4, 0xc, 0x0 } },
			// 0000 0100 0100 0100
			// 1110 0110 1110 1100
			// 0100 0100 0000 0100
			// 0000 0000 0000 0000
			{ { 0x0, 0xe, 0x4, 0x0 }, { 0x4, 0x6, 0x4, 0x0 }, { 0x4, 0xe, 0x0, 0x0 }, { 0x4, 0xc, 0x4, 0x0 } },
			// 0000 0100
			// 1111 0100
			// 0000 0100
			// 0000 0100
			{ { 0x0, 0xf, 0x0, 0x0 }, { 0x4, 0x4, 0x4, 0x4 } } };

	public static final int OMINO = 0;
	public static final int SMINO = 1;
	public static final int ZMINO = 2;
	public static final int LMINO = 3;
	public static final int JMINO = 4;
	public static final int TMINO = 5;
	public static final int IMINO = 6; // 블록 데이터 배열

	/**
	 * return array of each block
	 * 
	 * @param blockType
	 * 
	 * @return get block of parameter
	 */
	static byte[][] getBlockArr(int blockType) {
		if (blockType > 6)
			return null;
		return BLOCK_ARR[blockType];
	}
}
