package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class SecondaryCharacter extends GameObject {
	private long cumulativeScore = 0;

	public SecondaryCharacter(String name, String description) {
		super(name, description);
	}

	public long getCumulativeScore() {
		return cumulativeScore;
	}

	public void setCumulativeScore(long cumulativeScore) {
		this.cumulativeScore = cumulativeScore;
	}

	@Override
	public String toString() {
		return "SecondaryCharacter [cumulativeScore=" + cumulativeScore + "]";
	}

}
