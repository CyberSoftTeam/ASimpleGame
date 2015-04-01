package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class Action {
	private String id;
	private String semantics;
	private String idPri;
	private String idSecond;
	
	public Action(String id, String semantics, String idPri, String idSecond) {
		this.id = id;
		this.semantics = semantics;
		this.idPri = idPri;
		this.idSecond = idSecond;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSemantics() {
		return semantics;
	}

	public void setSemantics(String semantics) {
		this.semantics = semantics;
	}

	public String getIdPri() {
		return idPri;
	}

	public void setIdPri(String idPri) {
		this.idPri = idPri;
	}

	public String getIdSecond() {
		return idSecond;
	}

	public void setIdSecond(String idSecond) {
		this.idSecond = idSecond;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", semantics=" + semantics + ", idPri="
				+ idPri + ", idSecond=" + idSecond + "]";
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
		Action other = (Action) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
