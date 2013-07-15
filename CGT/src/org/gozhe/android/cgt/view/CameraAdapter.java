package org.gozhe.android.cgt.view;

import java.util.List;

import org.gozhe.android.R;
import org.gozhe.android.cgt.activity.upload.AddQuestionBActivity;
import org.gozhe.android.cgt.utils.ImageCacheUtil;
import org.gozhe.android.cgt.view.ProgressAdapter.ViewHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CameraAdapter extends BaseAdapter{

	Context context;
	List<String> photos;
	private LayoutInflater mInflater;
	
	public CameraAdapter(Context context){
		
		this.context=context;
		mInflater = LayoutInflater.from(context);
	}
	
	public CameraAdapter(Context context, List<String> photos){
		this.context=context;
		this.photos= photos;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photos.size() ;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return photos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.add_question_photo_item, null);
			holder.img=(ImageView)convertView.findViewById(R.id.question_photo_item_imageview);
			holder.title=(TextView)convertView.findViewById(R.id.question_photo_item_textview);
		
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		Bitmap bmp = ImageCacheUtil.getResizedBitmap(photos.get(position), null,   
                this.context, null, 400, false);  
		
		//---默认方法会 OOM
		//Bitmap bmp = BitmapFactory.decodeFile(photos.get(position));
		
		holder.img.setImageBitmap(bmp);
		holder.title.setText("照片信息"); 
	
		return convertView;
		
	}
	
	public final class ViewHolder {
		public ImageView img;
		public TextView title;
	}
}
