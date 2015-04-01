package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class Tool extends SecondaryCharacter {
	private long cost;

	public Tool(String id, String name, String description, long cost) {
		super(id, name, description);
		this.cost = cost;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Tool [cost=" + cost + "]";
	}
	
}
