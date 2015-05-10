package com.lary.health.service.model;

import java.util.List;

import netlib.model.BaseModel;

public class UserInfoModelNet extends BaseModel {

	private List<UserInfoModel> userInfo;

	public List<UserInfoModel> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<UserInfoModel> userInfo) {
		this.userInfo = userInfo;
	}
	
}
