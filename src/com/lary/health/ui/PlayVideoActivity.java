package com.lary.health.ui;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lary.health.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class PlayVideoActivity extends BaseFragmentActivity implements OnClickListener,OnGestureListener{
	
	private String url="http://114.113.237.28/family/./Public/Uploads/20140812/53e9bbc65e1d9.mp4";
	private VideoView videoView;
	private ImageView startBt;
	private Uri mUri;
	private ScheduledExecutorService scheduledExecutorService;
	private View mediaControllerLayout;
	private SeekBar videoSeekBar;
	private GestureDetector gestureDetector;
	private ProgressBar pbLoad;
	private TextView currentTimeView;
	private TextView totalTimeView;
	private Handler handler;
	private int mPositionWhenPaused = -1;

	//private 
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		mUri = Uri.parse(url);
		this.scheduledExecutorService = Executors.newScheduledThreadPool(2);

		this.gestureDetector = new GestureDetector(PlayVideoActivity.this);
		this.handler = new Handler();
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.video_view);
		videoView = (VideoView) findViewById(R.id.my_video_view);
		startBt = (ImageView) findViewById(R.id.playButton);
		mediaControllerLayout = findViewById(R.id.mediaControllerLayout);
		pbLoad = (ProgressBar) findViewById(R.id.pb_load);
		currentTimeView = (TextView) findViewById(R.id.currentTimeView);
		totalTimeView = (TextView) findViewById(R.id.totalTimeView);
		videoSeekBar = (SeekBar) findViewById(R.id.videoSeekBar);
		videoSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		startBt.setImageResource(R.drawable.mv_pause_button);
		startBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (videoView.isPlaying()) {
					videoView.pause();
					startBt.setImageResource(R.drawable.mv_start_button);
				} else {
					videoView.start();
					startBt.setImageResource(R.drawable.mv_pause_button);
				}
			}
		});
		
		videoView.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				pbLoad.setVisibility(View.GONE);
				return false;
			}
		});
		videoView.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				pbLoad.setVisibility(View.GONE);
				mp.start();
				startBt.setImageResource(R.drawable.mv_pause_button);
				hideControllView();
				scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

					@Override
					public void run() {
						handler.post(new Runnable() {
							@Override
							public void run() {
								if (videoView.isPlaying()) {
									float position = videoView
											.getCurrentPosition();
									int bufferPos = videoView.getBufferPercentage();
									int duration = videoView.getDuration();
									videoSeekBar.setSecondaryProgress(bufferPos);
									totalTimeView.setText(getTimeFormatValue(duration));
									currentTimeView
											.setText(getTimeFormatValue((int) position));
									videoSeekBar.setProgress((int) (position
											/ duration * videoSeekBar.getMax()));
								}
							}
						});

					}
				}, 1000, 1000, TimeUnit.MILLISECONDS);
			}
		});
	}

	private static String getTimeFormatValue(long time) {
		return MessageFormat.format(
				"{0,number,00}:{1,number,00}:{2,number,00}",
				time / 1000 / 60 / 60, time / 1000 / 60 % 60, time / 1000 % 60);
	}
	
	private void hideControllView(){
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mediaControllerLayout.setVisibility(View.INVISIBLE);
			}
		}, 5000);
	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		focusForMidea();
		if (videoView != null && mUri != null) {
			pbLoad.setVisibility(View.VISIBLE);
			videoView.setVideoURI(mUri);
			videoView.requestFocus();
			videoView.start();
		} else {
			Toast.makeText(this, "视频无法播放", Toast.LENGTH_SHORT)
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
                            // 鑾峰緱闊抽鐒︾偣
                            if (!videoView.isPlaying()) {
                            	videoView.start();
                            //	playButton.setImageResource(R.drawable.mv_pause_button);
                            }
                            // 杩樺師闊抽噺
//                            mediaPlayer.setVolume(1.0f, 1.0f);
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                            // 闀夸箙鐨勫け鍘婚煶棰戠劍鐐癸紝閲婃斁MediaPlayer
                            if (videoView.isPlaying())
                            	videoView.stopPlayback();
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            // 灞曠ず澶卞幓闊抽鐒︾偣锛屾殏鍋滄挱鏀剧瓑寰呴噸鏂拌幏寰楅煶棰戠劍鐐�
                            if (videoView.isPlaying())
                            	videoView.pause();
                        //    playButton.setImageResource(R.drawable.mv_start_button);
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            // 澶卞幓闊抽鐒︾偣锛屾棤闇�鍋滄鎾斁锛岄檷浣庡０闊冲嵆鍙�
                            if (videoView.isPlaying()) {
//                            	videoView.setVolume(0.1f, 0.1f);
                            }
                            break;
                        }
                    }
                }, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		if (videoView.isPlaying()) {
			videoView.pause();
			startBt.setImageResource(R.drawable.mv_start_button);
		} else {
			videoView.start();
			startBt.setImageResource(R.drawable.mv_pause_button);
		}
		return false;
	}


	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		if (distanceX > 0) {
			this.videoSeekBar
					.setProgress(this.videoSeekBar.getProgress() - 1);
		} else {
			this.videoSeekBar
					.setProgress(this.videoSeekBar.getProgress() + 1);
		}
		videoView.seekTo((int) (this.videoSeekBar.getProgress() * 1.0
				/ videoSeekBar.getMax() * videoView.getDuration()));
		return false;
	}


	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			mediaControllerLayout.setVisibility(View.VISIBLE);
		}
		if(event.getAction() == MotionEvent.ACTION_UP){
			hideControllView();
		}
		return this.gestureDetector.onTouchEvent(event);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mPositionWhenPaused >= 0) {
			videoView.seekTo(mPositionWhenPaused);
			mPositionWhenPaused = -1;
		}
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initWidgetAciotns();
	}
	
}
