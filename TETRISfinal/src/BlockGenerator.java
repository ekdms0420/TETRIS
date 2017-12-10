import java.util.ArrayList;

public class BlockGenerator {

	private ArrayList<Integer> nBlockSet;
	private int nCur;
	private boolean cycle;
	private int nTemp;

	/**Default Constructor*/
	public BlockGenerator() {

		nBlockSet = new ArrayList<Integer>();
		cycle = false;

	} // BlockGenerator()

	/**generate Block randomly*/
	public void generate() {
		nCur = 0;

		nBlockSet.clear();

		int[] nCountSet = new int[TetrisConstants.RANDOMSETSIZE];

		for (int i = 0; i < TetrisConstants.RANDOMSETSIZE; i++) {

			nBlockSet.add((int) (Math.random() * 7));
		}
		for (int i = 0; i < TetrisConstants.RANDOMSETSIZE; i++) {
			nCountSet[nBlockSet.get(i)]++;
		} // for

		for (int i = 0; i < TetrisConstants.RANDOMSETSIZE; i++) {
			if (nCountSet[i] > 1) {
				int temp;
				do {
					temp = (int) (Math.random() * 7);
				} while (i != temp);

				nBlockSet.set(nBlockSet.lastIndexOf(i), temp);
				nCountSet[i]--;
				i--;
			}
		}

		System.out.println(nBlockSet.toString());
	} // generate()

	/**check repeated*/
	public int[] getCurNext() {

		int[] ret = new int[2];
		if (cycle) {
			ret[0] = nTemp;
			ret[1] = nBlockSet.get(0);
			cycle = false;
		} else {
			ret[0] = nBlockSet.get(nCur++);
			ret[1] = nBlockSet.get(nCur);
		}
		if (nCur == 6) {
			nTemp = ret[1];
			cycle = true;
			generate();
		}
		return ret;
	} // getCurNext()
	
} // BlockGenerator class
