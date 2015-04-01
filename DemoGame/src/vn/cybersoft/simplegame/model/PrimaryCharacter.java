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
	private List<Action> listAction = new ArrayList<Action>();
	
	public PrimaryCharacter(String id, String name, String description) {
		super(id, name, description);
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

	public List<Action> getListAction() {
		return listAction;
	}

	public void setListAction(List<Action> listAction) {
		this.listAction = listAction;
	}

	@Override
	public String toString() {
		return "PrimaryCharacter [score=" + score + ", inventory=" + inventory
				+ "]";
	}
	
}
