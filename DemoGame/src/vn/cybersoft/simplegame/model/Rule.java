package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public abstract class Rule {
	private String id;
	private String action;
	private String syntax;
	
	public Rule(String id, String action, String syntax) {
		this.id = id;
		this.action = action;
		this.syntax = syntax;
	}

	public Rule(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", action=" + action + ", syntax=" + syntax
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public abstract GameObject doRule(GameObject obj1, GameObject obj2, GameObject obj3);
}
