package vn.cybersoft.simplegame.controller;

import java.util.List;

import vn.cybersoft.demo.simplegame.R;
import vn.cybersoft.simplegame.model.Tool;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class ToolAdapter extends ArrayAdapter<Tool> {
	private int currentToolIndex = 0;
	
	/**
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public ToolAdapter(Context context, int resource,
			List<Tool> characters) {
		super(context, resource, characters);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)
				getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.tool_item,
				parent, false);
		
		Tool item = getItem(position);
		ImageView itemview = (ImageView) convertView.findViewById(R.id.tool_image);
		if (item.getImage()!=null) {
			itemview.setImageBitmap(item.getImage());
		}
		
		if (position==currentToolIndex) {
			itemview.setBackground(getContext()
					.getResources()
					.getDrawable(R.drawable.current_item_border));
		}
		
		return convertView;
	}
	
	public void setCurrentTool(int position) {
		currentToolIndex = position;
		notifyDataSetChanged();
	}
	
	public Tool getCurrentTool() {
		return getItem(currentToolIndex);
	}
}
