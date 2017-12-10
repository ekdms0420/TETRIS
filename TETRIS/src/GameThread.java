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
		holdBlock = -1; // 블록셋 만들고, 슬립 시간 지정
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
		if (level == 6) // 최대 레벨 도달시 return
			return;
		this.nSleepTime = TetrisConstants.sleepLv[++level]; // level에 맞게 sleepTime 설정

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		boolean fail;
		bGen.generate(); // 블록 셋 생성
		arr = bGen.getCurNext(); // 현재, 다음 블록 받고

		callNextBlock(); // 블록 spawn

		while (true) {

			// game.spawnBlock();

			fail = game.move(TetrisConstants.down); // down 메소드 실행 후 moveException 발생시 fail == false

			if (!fail) { // move 실패한경우
				game.breakLine(); // breakLine메소드 호출

				game.gameOverCheck(); // 게임 오버 확인

				arr = bGen.getCurNext(); // 다음 블록 받음
				callNextBlock(); // 블록 spawn
			} else { // (fail)
				try {
					Thread.sleep(nSleepTime); // sleep
				} catch (Exception e) {
				}

			}

			if (this.game.getFather().getLblScore().getScore() > (TetrisConstants.levelThreshold[level])) {
				levelUp(); // 스코어가 현재 레벨의 역치를 넘었을 경우 레벨 업
			}

		}
	}

}
