package com.lary.health.musicListModel;

import java.util.ArrayList;
import java.util.List;

import netlib.util.Player;

import com.lary.health.R;
import com.lary.health.service.model.GymnasticsListItemModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import GymnasticsListItemModel.GymnasticeAdapter.ViewHolder;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MusicListAdapter extends BaseAdapter {

	private Context mContext;
	private List<GymnasticsListItemModel> list;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;
	private ListItemClickHelp callback;
	public Boolean mClick = false;
	private ArrayList<ImageView> playImg;
	private String murl;
	private  Player player;

	public MusicListAdapter(Context mContext) {
		this.mContext = mContext;
		list = new ArrayList<GymnasticsListItemModel>();
		playImg = new ArrayList<ImageView>();
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.music_list_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.update(list.get(position));
		return convertView;
	}

	public class ViewHolder {
		private TextView nameText;
		private ImageView playBt;
		private ImageView outup;
		private LinearLayout handler;
		private ImageView shareBr;
		private ImageView uploadBt;
		private boolean playing = false;
		public GymnasticsListItemModel model;

		public ViewHolder(View view) {
			nameText = (TextView) view.findViewById(R.id.music_name);
			playBt = (ImageView) view.findViewById(R.id.player_music_iv);
			outup = (ImageView) view.findViewById(R.id.more_music_iv);
			handler = (LinearLayout) view.findViewById(R.id.handle_music_ly);
			shareBr = (ImageView) view.findViewById(R.id.list_music_share);
			uploadBt = (ImageView) view.findViewById(R.id.list_music_down);

		}

		public void update(GymnasticsListItemModel model) {

			this.model = model;
			nameText.setText(model.getName());
			playBt.setOnClickListener(listrener);
			outup.setOnClickListener(listrener);
			shareBr.setOnClickListener(listrener);
			uploadBt.setOnClickListener(listrener);
			playImg.add(playBt);
		}

		OnClickListener listrener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				murl = mContext.getResources().getString(R.string.base_url_no)+model.getAudioUrl();
			//	murl = "http://64.22.109.100/~ventrix/olympiakos/aek.mp3";
				switch (v.getId()) {
				case R.id.player_music_iv:
					setUnplay();
					if(player != null){
						playBt.setImageResource(R.drawable.music_play);
					}else{
						playBt.setImageResource(R.drawable.music_pause);

					}
					playMedir();
					break;
				case R.id.more_music_iv:
					visia();
					break;
				case R.id.list_music_share:
					Toast.makeText(mContext, "分享成功"+model.getName(), 0)
					.show();
					break;
				case R.id.list_music_down:
					Toast.makeText(mContext, "下载成功"+model.getName(), 0)
					.show();
					break;
				// case R.id.player_music_iv:

				// break;
				default:
					break;
				}
			}
		};

		public void visia() {
			if (handler.isClickable()) {
				handler.setClickable(false);
				handler.setVisibility(View.GONE);
				outup.setImageResource(R.drawable.list_down);
			} else {
				handler.setClickable(true);
				handler.setVisibility(View.VISIBLE);
				outup.setImageResource(R.drawable.list_up);

			}

		}
		
		public void playMedir(){
			if (player != null) {
				player.stop();
				player = null;
			}else{
				player = new Player(null);

				new Thread(new Runnable() {

					@Override
					public void run() {
						Log.d("播放音乐", murl);
					//	murl
						player.playUrl(murl);//pathText.getText().toString());
					}
				}).start();
			}
		}

	}

	public void refreshData(List<GymnasticsListItemModel> list) {
		this.list.clear();
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void addData(List<GymnasticsListItemModel> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void setUnplay(){
		for(int i=0;i<playImg.size();i++){
			playImg.get(i).setImageResource(R.drawable.music_play);
		}
	}
	
	public void stopPLay(){
		if (player != null) {
			player.stop();
			player = null;
		}
	}
}
