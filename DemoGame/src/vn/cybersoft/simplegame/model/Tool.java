package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class Tool extends GameObject {
	private long price;

	public Tool(String name, String description, long price) {
		super(name, description);
		this.price = price;
	}
	
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Tool [price=" + price + "]";
	}
	
}
