package vn.cybersoft.simplegame.model;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class Product extends SecondaryCharacter {
	private long bonus = 0;

	public Product(String id, String name, String description) {
		super(id, name, description);
	}

	public long getBonus() {
		return bonus;
	}

	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "Product [bonus=" + bonus + "]";
	}

}
