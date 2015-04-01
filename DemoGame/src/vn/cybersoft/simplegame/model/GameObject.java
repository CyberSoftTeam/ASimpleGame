package vn.cybersoft.simplegame.model;

import android.graphics.Bitmap;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class GameObject {
	private String id;
	private String name;
	private String description;
	private String status;
	
	private Bitmap image;
	
	public GameObject(String id) {
		this.name = "unknown";
		this.description = "unknown";
		this.status = "unknown";
		this.id = id;
	}
	public GameObject(String id, String name, String description, String status) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.id = id;
	}
	public GameObject(String id, String name, String description) {
		this.name = name;
		this.description = description;
		this.status = "unknown";
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "GameObject [id=" + id + ", name=" + name + ", description="
				+ description + ", status=" + status + ", image=" + image + "]";
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
		GameObject other = (GameObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
