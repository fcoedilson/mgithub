package org.gozhe.android.cgt.view;

import java.util.List;
import java.util.Map;

import org.gozhe.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	public List<Map<String, Object>> mData;

	public ProgressAdapter(Context context) {

		this.mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.up_manager_item, null);
			holder.img=(ImageView)convertView.findViewById(R.id.up_imageview);
			holder.title=(TextView)convertView.findViewById(R.id.up_title);
			holder.progress=(ProgressBar)convertView.findViewById(R.id.up_progress);
			holder.btn=(Button)convertView.findViewById(R.id.up_btn);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
		holder.title.setText((String)mData.get(position).get("title"));
		holder.progress.setProgress(60);
		holder.btn.setText("ÔÝÍ£");
		
		return convertView;

	}

	public final class ViewHolder {
		public ImageView img;
		public TextView title;
		public ProgressBar progress;
		public Button btn;
	}
}
