package vn.cybersoft.simplegame.gui;

import vn.cybersoft.demo.simplegame.R;
import vn.cybersoft.simplegame.services.DBService;
import android.app.Activity;
import android.os.Bundle;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class BeginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBService.getInstance();
		setContentView(R.layout.activity_begin_screen);
	}
}
