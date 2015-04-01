package vn.cybersoft.simplegame.demo;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class IDGenerator {
	private static long nextID = 0;
	private IDGenerator() {}
	public static String getNextId() {
		nextID+=1;
		return String.valueOf(nextID); 
	}
}
