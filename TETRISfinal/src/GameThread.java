/**
 * 
 * Thread for each game
 * 
 * @author DongYeop Lee
 *
 */
public class GameThread implements Runnable {

	private Game game;
	private BlockGenerator bGen;

	/**
	 * sleep time determined by level
	 * 
	 * @see TetrisConstants
	 */
	private long nSleepTime;
	int holdBlock;
	int level;
	int[] arr;

	/**
	 * Default Constructor
	 * 
	 * @param game
	 */
	public GameThread(Game game) {
		this.game = game;
		level = 0;
		nSleepTime = (long) TetrisConstants.sleepLv[level];
		bGen = new BlockGenerator();
		holdBlock = -1; // ��ϼ� �����, ���� �ð� ����
	}

	/**
	 * set sleep time as nSleepTime
	 * 
	 * @param nSleepTime
	 */
	public void setSleepTime(long nSleepTime) {
		this.nSleepTime = nSleepTime;
	}

	/**
	 * get sleep time
	 * 
	 * @return nSleepTime
	 */
	public long getSleepTime() {
		return this.nSleepTime;
	}

	/**
	 * hold the block and apply it to set
	 */
	public void hold() {
		if (holdBlock == -1) {
			holdBlock = arr[0];
			game.removeBlock();
			game.setHoldBlock(arr[0]);
			arr = bGen.getCurNext();
			callNextBlock();
		} else {
			int temp = holdBlock;
			holdBlock = arr[0];
			arr[0] = temp;
			game.removeBlock();
			game.setHoldBlock(holdBlock);
			game.setCurrentBlock(arr[0]);

		}
	}

	/**
	 * spawn next block
	 */
	private void callNextBlock() {
		game.spawnBlock(new Block(arr[0]));
		game.setNextBlock(arr[1]);
		// game.validate();
	}

	public void levelUp() {
		if (level == 6) // �ִ� ���� ���޽� return
			return;
		this.nSleepTime = TetrisConstants.sleepLv[++level]; // level�� �°� sleepTime ����

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		boolean fail;
		bGen.generate(); // ��� �� ����
		arr = bGen.getCurNext(); // ����, ���� ��� �ް�

		callNextBlock(); // ��� spawn

		while (true) {

			// game.spawnBlock();

			fail = game.move(TetrisConstants.down); // down �޼ҵ� ���� �� moveException �߻��� fail == false

			if (!fail) { // move �����Ѱ��
				game.breakLine(); // breakLine�޼ҵ� ȣ��

				game.gameOverCheck(); // ���� ���� Ȯ��

				arr = bGen.getCurNext(); // ���� ��� ����
				callNextBlock(); // ��� spawn
			} else { // (fail)
				try {
					Thread.sleep(nSleepTime); // sleep
				} catch (Exception e) {
				}

			}

			if (this.game.getFather().getLblScore().getScore() > (TetrisConstants.levelThreshold[level])) {
				levelUp(); // ���ھ ���� ������ ��ġ�� �Ѿ��� ��� ���� ��
			}

		}
	}

}
