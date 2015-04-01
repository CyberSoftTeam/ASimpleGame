package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public abstract class PlayerRule extends Rule{
	private GameObject semantics;
	
	public PlayerRule(String id, String action, String syntax) {
		super(id, action, syntax);
	}
	
	public PlayerRule(String id) {
		super(id);
	}

	public GameObject getSemantics() {
		return semantics;
	}

	public void setSemantics(GameObject semantics) {
		this.semantics = semantics;
	}

	public abstract GameObject doRule(GameObject obj1, GameObject obj2, GameObject obj3);
}
