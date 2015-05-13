package com.lary.health.service.model;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午3:18:12
 * @Email zhaoyp@witmob.com
 * @Description 首页model
 */
public class HomeModel extends BaseModel{
	private String imgurl;
	private String neturl;
	private String shopurl;
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getNeturl() {
		return neturl;
	}
	public void setNeturl(String neturl) {
		this.neturl = neturl;
	}
	public String getShopurl() {
		return shopurl;
	}
	public void setShopurl(String shopurl) {
		this.shopurl = shopurl;
	}
    

}
