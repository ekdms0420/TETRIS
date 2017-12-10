public class GameThread implements Runnable {

	private Game game;
	private BlockGenerator bGen;

	private long nSleepTime;
	int holdBlock;
	int[] arr;

	public GameThread(Game game) {
		this.game = game;
		nSleepTime = (long) TetrisConstants.sleepLv1;
		bGen = new BlockGenerator();
		holdBlock = -1;
	}

	public void setSleepTime(long nSleepTime) {
		this.nSleepTime = nSleepTime;
	}

	public long getSleepTime() {
		return this.nSleepTime;
	}

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

	private void callNextBlock() {
		game.spawnBlock(new Block(arr[0]));
		game.setNextBlock(arr[1]);
		// game.validate();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		boolean fail;
		bGen.generate();
		arr = bGen.getCurNext();

		callNextBlock();

		while (true) {

			// game.spawnBlock();

			fail = game.move(TetrisConstants.down);

			if (!fail) {
				game.gameOverCheck();
				arr = bGen.getCurNext();
				callNextBlock();
			} else {
				try {
					Thread.sleep(nSleepTime);
				} catch (Exception e) {
				}

			}

		}
	}

}
