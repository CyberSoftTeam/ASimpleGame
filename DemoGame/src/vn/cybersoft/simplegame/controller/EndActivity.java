package vn.cybersoft.simplegame.controller;

import vn.cybersoft.demo.simplegame.R;
import vn.cybersoft.simplegame.view.FButton;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class EndActivity extends Activity 
implements OnClickListener {
	private static long finalScore = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_screen);
		TextView txtScore = (TextView) findViewById(R.id.txt_finalscore);
		FButton btnReset = (FButton) findViewById(R.id.btn_restart_game);
		btnReset.setOnClickListener(this);
		txtScore.setText(getString(R.string.end_screen_content, finalScore));
	}

	public static void showActivity(Context context, long finalScore) {
		EndActivity.finalScore = finalScore;

		Intent i = new Intent(context, EndActivity.class);
		if (Build.VERSION.SDK_INT >= 11) {
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
		} else {
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		context.startActivity(i);
	}

	@Override
	public void onClick(View v) {
		BeginActivity.showActivity(this);
	}
}
