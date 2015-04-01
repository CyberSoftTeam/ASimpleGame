package vn.cybersoft.simplegame.controller;

import java.util.ArrayList;
import java.util.List;

import vn.cybersoft.demo.simplegame.R;
import vn.cybersoft.simplegame.Preferences;
import vn.cybersoft.simplegame.demo.IDGenerator;
import vn.cybersoft.simplegame.model.PrimaryCharacter;
import vn.cybersoft.simplegame.model.Product;
import vn.cybersoft.simplegame.model.Rule;
import vn.cybersoft.simplegame.model.Tool;
import vn.cybersoft.simplegame.model.gen.AutogenRule1;
import vn.cybersoft.simplegame.view.FButton;
import vn.cybersoft.simplegame.view.HorizontalListView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class MainActivity extends Activity 
implements OnClickListener {
	private static final String TAG = MainActivity.class.getSimpleName();
	private PrimaryCharacter user;
	private List<Product> characters = new ArrayList<Product>();
	private List<Tool> tools = new ArrayList<Tool>();
	private GameScript gscript;

	private SecondaryCharAdapter charAdapter;
	private ToolAdapter toolAdapter;

	private HorizontalListView toolListView;
	private GridView charGridView;
	private TextView txtToolDesc;
	private TextView txtStatistic;
	private FButton btnStopGame;

	private OnItemClickListener toolItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (toolAdapter==null) {
				return;
			}
			Tool item = toolAdapter.getItem(position);
			txtToolDesc.setText(item.getDescription());
			toolAdapter.setCurrentTool(position);
		}
	};
	private OnItemClickListener charItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (charAdapter==null) {
				return;
			}
			Product item = charAdapter.getItem(position);
			Log.d(TAG, "You are choosing "+item.getName()+"-"+item.getDescription());

			Tool tool = toolAdapter.getCurrentTool();
			for (Rule rule : gscript.getListRule()) {
				if (rule instanceof AutogenRule1) {
					AutogenRule1 rule1 = (AutogenRule1) rule;
					Product newItem = rule1.doRule(tool, item, user);
					if (newItem!=null) {
						charAdapter.replaceItem(position, newItem);
						updateUserScore();
					}
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		toolListView = (HorizontalListView) findViewById(R.id.list_tools);
		charGridView = (GridView) findViewById(R.id.garden_field);
		txtToolDesc  = (TextView) findViewById(R.id.txt_tool_desc);
		txtStatistic = (TextView) findViewById(R.id.txt_statistic);
		btnStopGame = (FButton) findViewById(R.id.btn_stop_game);

		btnStopGame.setOnClickListener(this);

		new AsyncTask<Void, Void, Void>() {
			ProgressDialog progressDialog;
			protected void onPreExecute() {
				progressDialog = ProgressDialog.show(MainActivity.this,
						"ASimpleGame", "Setting up...");
			};
			@Override
			protected Void doInBackground(Void... params) {
				constructDemoObjects();
				return null;
			}
			protected void onPostExecute(Void result) {
				setupAdapters();
				progressDialog.dismiss();
			};
		}.execute();

	}

	public void setupAdapters() {
		charAdapter = new SecondaryCharAdapter(this, R.layout.secondary_char_item, characters);
		toolAdapter = new ToolAdapter(this, R.layout.tool_item, tools);
		charGridView.setAdapter(charAdapter);
		toolListView.setAdapter(toolAdapter);
		toolListView.setOnItemClickListener(toolItemClickListener);
		charGridView.setOnItemClickListener(charItemClickListener);
	}

	public static void showActivity(Context context) {
		Intent i = new Intent(context, MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	@SuppressWarnings("unused")
	private void updateUserScore(long cumulativeScore) {
		user.setScore(user.getScore()+cumulativeScore);
		txtStatistic.setText(getString(R.string.game_statistic_content, user.getScore()));
	}

	private void updateUserScore() {
		txtStatistic.setText(getString(R.string.game_statistic_content, user.getScore()));
	}

	/**
	 * Hard code method - only for demo
	 */
	private void constructDemoObjects(){
		user = new PrimaryCharacter(IDGenerator.getNextId(), "Player", "Demo player");
		
		// prepare tools
		Tool scissor = new Tool(IDGenerator.getNextId(), "Scissor", "Scissor help you harvest flower", 5);
		Tool watering = new Tool(IDGenerator.getNextId(), "Watering-Can", "Watering-Can help sprout grow up to flower", 5);
		Tool shovel = new Tool(IDGenerator.getNextId(), "Shovel", "Shovel help seed grow up to sprout", 5);
		scissor.setImage(BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/scissor.png"));
		watering.setImage(BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/watering.png"));
		shovel.setImage(BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/shovel.png"));
		tools.add(shovel);
		tools.add(watering);
		tools.add(scissor);

		// prepare secondary character
		Bitmap seedImg = BitmapFactory.decodeFile(Preferences.IMAGE_DIR_PATH+"/seed.png");
		for (int i = 0; i < 9; i++) {
			String newid = IDGenerator.getNextId();
			Product seed = new Product(newid, "Seed "+newid, "Seed");
			seed.setImage(seedImg);
			characters.add(seed);
		}
		Log.d(TAG, String.format("Character: %d, Tool: %d\n", characters.size(), tools.size()));
		
		// load rules
		gscript = new GameScript(IDGenerator.getNextId());
		gscript.getListRule().add(new AutogenRule1(IDGenerator.getNextId()));
		
	}

	@Override
	public void onClick(View v) {
		EndActivity.showActivity(this, user.getScore());
	}

}
