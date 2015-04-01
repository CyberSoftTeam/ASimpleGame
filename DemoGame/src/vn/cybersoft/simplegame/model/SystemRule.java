package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public abstract class SystemRule extends Rule {
	private GameObject effectives;
	private int scope;
	
	public SystemRule(String id, String action, String syntax) {
		super(id, action, syntax);
	}

	public GameObject getEffectives() {
		return effectives;
	}

	public void setEffectives(GameObject effectives) {
		this.effectives = effectives;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}
	
	public abstract GameObject doRule(GameObject obj1, GameObject obj2, GameObject obj3);
}
