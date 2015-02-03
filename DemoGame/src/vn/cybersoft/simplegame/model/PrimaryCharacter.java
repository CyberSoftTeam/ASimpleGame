package vn.cybersoft.simplegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class PrimaryCharacter extends GameObject {
	private long score = 0;
	private List<Tool> inventory = new ArrayList<Tool>();
	
	public PrimaryCharacter(String name, String description) {
		super(name, description);
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public List<Tool> getInventory() {
		return inventory;
	}

	public void setInventory(List<Tool> inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "PrimaryCharacter [score=" + score + ", inventory=" + inventory
				+ "]";
	}
	
}
