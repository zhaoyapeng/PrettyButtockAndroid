package com.lary.health.service.model;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-12下午11:22:16
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈 群组model
 */
public class CircleItemModel extends BaseModel {

	private String id;
	private String name;
	private String imageurl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

}
