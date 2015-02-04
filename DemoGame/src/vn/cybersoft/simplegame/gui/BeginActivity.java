package vn.cybersoft.simplegame.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import vn.cybersoft.demo.simplegame.R;
import vn.cybersoft.simplegame.Preferences;
import vn.cybersoft.simplegame.widgets.FButton;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class BeginActivity extends Activity 
implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_begin_screen);

		FButton btnStart = (FButton) findViewById(R.id.btn_start_game);
		btnStart.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// copy demo game resource from asset directory
		copyAssets();
		//DBService.getInstance();

		// open main screen
		MainActivity.showActivity(this);
	}

	private void copyAssets() {
		AssetManager assetManager = getAssets();
		String[] files = null;
		try {
			files = assetManager.list("demo_img");
		} catch (IOException e) {
			Log.e("tag", "Failed to get asset file list.", e);
		}
		File imgDir = new File(Preferences.IMAGE_DIR_PATH);
		if (!imgDir.exists()) {
			imgDir.mkdirs();
		}
		for(String filename : files) {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = assetManager.open("demo_img"+File.separator+filename);
				File outFile = new File(imgDir, filename);
				if (!outFile.exists()) {
					out = new FileOutputStream(outFile);
					copyFile(in, out);
				}
			} catch(IOException e) {
				Log.e("tag", "Failed to copy asset file: " + filename, e);
			}     
			finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// NOOP
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// NOOP
					}
				}
			}  
		}
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}

	public static void showActivity(Context context) {
		Intent i = new Intent(context, BeginActivity.class);
		if (Build.VERSION.SDK_INT >= 11) {
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
		context.startActivity(i);
	}
}
