package com.lary.health.ui;

import com.lary.health.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayVideoActivity extends BaseFragmentActivity implements OnClickListener{
	
	private String url="http://114.113.237.28/family/./Public/Uploads/20140812/53e9bbc65e1d9.mp4";
	private VideoView videoView;
	private ImageView startBt;
	private Uri mUri;

	//private 
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		mUri = Uri.parse(url);

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.video_view);
		videoView = (VideoView) findViewById(R.id.my_video_view);
		startBt = (ImageView) findViewById(R.id.playButton);
	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		startBt.setOnClickListener(this);
		focusForMidea();
		if (videoView != null && mUri != null) {
			//pbLoad.setVisibility(View.VISIBLE);
			videoView.setVideoURI(mUri);
			videoView.requestFocus();
			videoView.start();
		} else {
			Toast.makeText(this, "无法播放此视频", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.playButton:
			
			break;

		default:
			break;
		}
	}
	private void focusForMidea(){
		AudioManager mAm=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
		@SuppressWarnings("unused")
		int result = mAm.requestAudioFocus(
                new OnAudioFocusChangeListener() {

                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            // 获得音频焦点
                            if (!videoView.isPlaying()) {
                            	videoView.start();
                            //	playButton.setImageResource(R.drawable.mv_pause_button);
                            }
                            // 还原音量
//                            mediaPlayer.setVolume(1.0f, 1.0f);
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                            // 长久的失去音频焦点，释放MediaPlayer
                            if (videoView.isPlaying())
                            	videoView.stopPlayback();
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            // 展示失去音频焦点，暂停播放等待重新获得音频焦点
                            if (videoView.isPlaying())
                            	videoView.pause();
                        //    playButton.setImageResource(R.drawable.mv_start_button);
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            // 失去音频焦点，无需停止播放，降低声音即可
                            if (videoView.isPlaying()) {
//                            	videoView.setVolume(0.1f, 0.1f);
                            }
                            break;
                        }
                    }
                }, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
	}
}
