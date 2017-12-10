/**
 * 
 * @author DaEun Kim
 *
 */
public class ScoreData {
	private int userRank;
	private String userName;
	private int userScore;

	public ScoreData() {

		userRank = 1;
		userName = "NONE";
		userScore = 0;

	}// ScoreData()

	public ScoreData(int rank, String name, int uScore) {
		userRank = rank;
		userName = name;
		userScore = uScore;
	}

	public int getRank() {
		return userRank;
	}

	public String getName() {
		return userName;
	}

	public int getScore() {
		return userScore;
	}

	public void setRank(int rank) {
		userRank = rank;
	}

	public void setName(String name) {
		userName = name;
	}

	public void setScore(int uScore) {
		userScore = uScore;
	}

	public void setData(int rank, String name, int uScore) {
		userRank = rank;
		userName = name;
		userScore = uScore;
	}
}// data �� ����üó�� �����ϰ� ����Ѵ� �����Ͽ� ��ũ�� �ֱ� ����
