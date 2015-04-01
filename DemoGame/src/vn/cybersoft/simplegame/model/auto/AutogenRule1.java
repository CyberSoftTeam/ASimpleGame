package vn.cybersoft.simplegame.model.auto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import vn.cybersoft.simplegame.Preferences;
import vn.cybersoft.simplegame.demo.IDGenerator;
import vn.cybersoft.simplegame.model.GameObject;
import vn.cybersoft.simplegame.model.PlayerRule;
import vn.cybersoft.simplegame.model.PrimaryCharacter;
import vn.cybersoft.simplegame.model.Product;
import vn.cybersoft.simplegame.model.Tool;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class AutogenRule1 extends PlayerRule {
	public AutogenRule1(String id) {
		super(id);
	}
	
	public GameObject doRule(GameObject tool, GameObject secChar, GameObject priChar){
		if (tool instanceof Tool && secChar instanceof Product
				&& priChar instanceof PrimaryCharacter) {
			return doRule(tool, secChar, priChar);
		}
		throw new IllegalArgumentException("Invalid Argument");
	}
	
	public Product doRule(Tool tool, Product secChar, PrimaryCharacter priChar) {
		Product newItem = null;
		if (tool.getName().equals("Shovel")
				&& secChar.getName().startsWith("Seed")) {
			String newid = IDGenerator.getNextId();
			newItem =
					new Product(newid, "Sprout "+newid, "Sprout");
			Bitmap secCharImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/sprout.png");
			newItem.setImage(secCharImg);
			newItem.setBonus(10);
		} else if (tool.getName().equals("Watering-Can")
				&& secChar.getName().startsWith("Sprout")) {
			String newid = IDGenerator.getNextId();
			newItem =
					new Product(newid, "Rose "+newid, "Rose");
			Bitmap secCharImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/rose.png");
			newItem.setBonus(30);
			newItem.setImage(secCharImg);
		} else if (tool.getName().equals("Scissor")
				&& secChar.getName().startsWith("Rose")) {
			priChar.setScore(priChar.getScore()+secChar.getBonus());
			String newid = IDGenerator.getNextId();
			newItem =
					new Product(newid, "Seed "+newid, "Seed");
			Bitmap secCharImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/seed.png");
			newItem.setImage(secCharImg);
		}   
		return newItem;
	}
}
