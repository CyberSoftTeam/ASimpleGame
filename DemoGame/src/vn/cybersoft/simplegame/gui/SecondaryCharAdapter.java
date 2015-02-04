package vn.cybersoft.simplegame.gui;

import java.util.List;

import vn.cybersoft.demo.simplegame.R;
import vn.cybersoft.simplegame.model.SecondaryCharacter;
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
public class SecondaryCharAdapter extends ArrayAdapter<SecondaryCharacter> {
	private List<SecondaryCharacter> characters;
	
	/**
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public SecondaryCharAdapter(Context context, int resource,
			List<SecondaryCharacter> characters) {
		super(context, resource, characters);
		this.characters = characters;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)
				getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.secondary_char_item,
				parent, false);
		
		SecondaryCharacter item = getItem(position);
		ImageView itemview = (ImageView) convertView.findViewById(R.id.char_image);
		if (item.getImage()!=null) {
			itemview.setImageBitmap(item.getImage());
		}
		
		return convertView;
	}
	
	public void replaceItem(int position, SecondaryCharacter newItem) {
		characters.set(position, newItem);
		notifyDataSetChanged();
	}
}
