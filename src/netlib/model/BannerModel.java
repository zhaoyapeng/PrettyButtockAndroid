package netlib.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class BannerModel extends BaseModel {
	@SerializedName("netInfo")
	private  ArrayList<BannerImgInfoBean> netInfo;

	public ArrayList<BannerImgInfoBean> getNetInfo() {
		return netInfo;
	}

	public void setNetInfo(ArrayList<BannerImgInfoBean> netInfo) {
		this.netInfo = netInfo;
	}
	
}