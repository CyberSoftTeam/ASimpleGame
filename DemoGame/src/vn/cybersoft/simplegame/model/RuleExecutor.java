package vn.cybersoft.simplegame.model;

import vn.cybersoft.simplegame.Preferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class RuleExecutor {
	public static SecondaryCharacter doRule(Tool tool, SecondaryCharacter secChar,
			int position, PrimaryCharacter priChar) {
		SecondaryCharacter newItem = null;
		if (tool.getName().equals("Shovel")
				&& secChar.getName().startsWith("Seed")) {
			newItem =
					new SecondaryCharacter("Sprout "+position, "Sprout");
			Bitmap secCharImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/sprout.png");
			newItem.setImage(secCharImg);
			newItem.setCumulativeScore(10);
		} else if (tool.getName().equals("Watering-Can")
				&& secChar.getName().startsWith("Sprout")) {
			newItem =
					new SecondaryCharacter("Rose "+position, "Rose");
			Bitmap secCharImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/rose.png");
			newItem.setCumulativeScore(30);
			newItem.setImage(secCharImg);
		} else if (tool.getName().equals("Scissor")
				&& secChar.getName().startsWith("Rose")) {
			priChar.setScore(priChar.getScore()+secChar.getCumulativeScore());
			newItem =
					new SecondaryCharacter("Seed "+position, "Seed");
			Bitmap secCharImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/seed.png");
			newItem.setImage(secCharImg);
		}   
		return newItem;
	}
	
}
