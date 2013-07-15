package org.gozhe.android.cgt.view;

import org.gozhe.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private Integer[] images;
	private String[] texts;

	public ImageAdapter(Context context) {
		this.context = context;
	}
	
	public ImageAdapter(Context context, Integer[] images,String[] texts){
		this.context=context;
		this.images=images;
		this.texts=texts;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImgTextWrapper wrapper;
		if (view == null) {
			wrapper = new ImgTextWrapper();
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.main_item, null);
			view.setTag(wrapper);
			view.setPadding(15, 15, 15, 15); // 每格的间距
		} else {
			wrapper = (ImgTextWrapper) view.getTag();
		}
		wrapper.imageView = (ImageView) view.findViewById(R.id.main_image);
		wrapper.imageView.setBackgroundResource(images[position]);
		wrapper.textView = (TextView) view.findViewById(R.id.main_textview);
		wrapper.textView.setText(texts[position]);
		return view;
	}
}

class ImgTextWrapper {
	ImageView imageView;
	TextView textView;
}
