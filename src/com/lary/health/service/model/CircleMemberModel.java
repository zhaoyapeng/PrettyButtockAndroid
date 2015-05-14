package com.lary.health.service.model;

import java.io.Serializable;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-13下午9:10:39
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈 成员item model
 */
public class CircleMemberModel extends BaseModel implements Serializable {
	private String id;
	private String name;
	private String photoImage;
	private String TrainingTimes;

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

	public String getPhotoImage() {
		return photoImage;
	}

	public void setPhotoImage(String photoImage) {
		this.photoImage = photoImage;
	}

	public String getTrainingTimes() {
		return TrainingTimes;
	}

	public void setTrainingTimes(String trainingTimes) {
		TrainingTimes = trainingTimes;
	}
}
