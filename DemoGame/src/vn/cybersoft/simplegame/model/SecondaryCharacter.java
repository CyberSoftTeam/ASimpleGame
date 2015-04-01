package vn.cybersoft.simplegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class SecondaryCharacter extends GameObject{
	private int score;
	private int value;
	private List<Action> listAction = new ArrayList<Action>();
	
	public SecondaryCharacter(String id,
			String name, String description, 
			int score, int value) {
		super(id, name, description);
		this.score = score;
		this.value = value;
	}
	
	public SecondaryCharacter(String id,
			String name, String description) {
		super(id, name, description);
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public List<Action> getListAction() {
		return listAction;
	}
	public void setListAction(List<Action> listAction) {
		this.listAction = listAction;
	}
	@Override
	public String toString() {
		return "SecondaryCharacter [score=" + score + ", value=" + value + "]";
	}
	
}
