package com.lary.health.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import netlib.model.HomeDirect;

import com.lary.health.R;
import com.lary.health.ui.adaper.HomeFunctionAdapter;
import com.lary.health.ui.widget.RollViewPager2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:31:12
 * @Email zhaoyp@witmob.com
 * @Description 首页fragment
 */
public class HomeFragment extends BaseViewPagerFragment {

	private ImageView ceshiImage;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;
	private RollViewPager2 rollViewPager2;// 只需要一个layout即可,ViewPager是动态加载的
	private ArrayList<String> uriList;
	private ArrayList<View> dots;
	private GridView func_gridview;
	List<HomeDirect> homedirects = null;//6大功能
	private HomeFunctionAdapter gridAdapter = null;

	@Override
	protected void initData() {
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		rollViewPager2 = (RollViewPager2) view.findViewById(R.id.forimg_viewpager);
		func_gridview = (GridView) view.findViewById(R.id.func_gridview);
		initItems();
		gridAdapter = new HomeFunctionAdapter(getActivity(), homedirects);
		func_gridview.setAdapter(gridAdapter);
		dots = new ArrayList<View>();
		dots.add(view.findViewById(R.id.dot_0));
		dots.add(view.findViewById(R.id.dot_1));
		dots.add(view.findViewById(R.id.dot_2));
		dots.add(view.findViewById(R.id.dot_3));
		dots.add(view.findViewById(R.id.dot_4));
		return view;
	}

	@Override
	protected void initWidgetActions() {
		String url ="http://a.hiphotos.baidu.com/image/w%3D230/sign=1a3ed992a60f4bfb8cd09957334f788f/1e30e924b899a901d434ed3b1f950a7b0208f5be.jpg";
	//	imageLoader.displayImage(url, ceshiImage, avatarOptions);
		forImg();
	}

	@Override
	protected void refreshData() {

	}
	
	private void initItems()
	{
		String name[] ={"垫上美操","体质辨识","养身音乐","原创音乐","最新活动","锻炼记录"};
		int [] image = {R.drawable.list01,R.drawable.list02,R.drawable.list03,
				R.drawable.list04,R.drawable.list05,R.drawable.list06};
		 homedirects =new  ArrayList<HomeDirect>();
		 for(int i=1;i<image.length +1 ;i++)
		 {
			 HomeDirect direct = new HomeDirect();
			 direct.setId(i);
			 direct.setName(name[i-1]);
			 direct.setImage(image[i-1]);
			 homedirects.add(direct);
		 }
	}
	
	public void forImg(){
		// 构造网络图片
		uriList = new ArrayList<String>();
		uriList.add("http://h.hiphotos.baidu.com/album/w%3D2048/sign=730e7fdf95eef01f4d141fc5d4c69825/94cad1c8a786c917b8bf9482c83d70cf3ac757c9.jpg");
		uriList.add("http://g.hiphotos.baidu.com/album/w%3D2048/sign=00d4819db8014a90813e41bd9d4f3812/562c11dfa9ec8a137de469cff603918fa0ecc026.jpg");
		uriList.add("http://c.hiphotos.baidu.com/album/w%3D2048/sign=a8631adb342ac65c67056173cfcab011/b8389b504fc2d56217d11656e61190ef77c66cb4.jpg");
		uriList.add("http://e.hiphotos.baidu.com/album/w%3D2048/sign=ffac8994a71ea8d38a227304a332314e/1ad5ad6eddc451da4d9d32c4b7fd5266d01632b1.jpg");
		uriList.add("http://a.hiphotos.baidu.com/album/w%3D2048/sign=afbe93839a504fc2a25fb705d1e5e611/d058ccbf6c81800a99489685b03533fa838b478f.jpg");
		rollViewPager2.setDot(dots, R.drawable.dot_focus, R.drawable.dot_normal);

		
		// 用来显示的点

		rollViewPager2.setPagerCallback(new RollViewPager2.OnPagerClickCallback() {
			
			@Override
			public void onPagerClick(int position) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(),
						"第" + position + "张图片被点击了", 0).show();
			}
		});
		
		//mViewPager.setResImageIds(imageIDs);//设置res的图片id
		rollViewPager2.setUriList(uriList);//设置网络图片的url
		rollViewPager2.startRoll();//不调用的话不滚动
	}

}
