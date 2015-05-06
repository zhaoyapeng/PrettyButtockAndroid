package com.lary.health.ui.adaper;

import java.util.List;
import java.util.zip.Inflater;

import com.lary.health.R;

import netlib.model.HomeDirect;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 功能模块适配器
 * @author du
 *
 */
public class HomeFunctionAdapter extends BaseAdapter {
	protected static final int SUCCESS_GET_IMAGE = 0;
	private Context context;
	private List<HomeDirect> homedirects; 
	
	public HomeFunctionAdapter(Context context,List<HomeDirect> homedirects){
		this.context = context;
		this.homedirects = homedirects;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return homedirects.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return homedirects.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	ViewHolder vh;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.homepage_list_item, null);
			vh = new ViewHolder();
			vh.fun_icon = (ImageView) convertView.findViewById(R.id.home_item_image);
			vh.fun_name = (TextView) convertView.findViewById(R.id.home_item_text);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		vh.fun_icon.setImageResource(homedirects.get(position).getImage());
		vh.fun_name.setText(homedirects.get(position).getName());
		return convertView;
	}
	
	class ViewHolder{
		ImageView fun_icon;
		TextView fun_name;
	}

}
