package com.lary.health.service.model;

import java.util.List;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午3:20:07
 * @Email zhaoyp@witmob.com
 * @Description TODO
 */
public class HomeModelNet extends BaseModel {
    private List<HomeModel> netInfo;

	public List<HomeModel> getNetInfo() {
		return netInfo;
	}

	public void setNetInfo(List<HomeModel> netInfo) {
		this.netInfo = netInfo;
	}
}
