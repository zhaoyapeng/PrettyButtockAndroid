package com.lary.health.service.model;

import java.io.Serializable;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午5:59:50
 * @Email zhaoyp@witmob.com
 * @Description 垫上美操 item model
 */
public class GymnasticsListItemModel extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5161628430874474208L;
	private String Id;
	private String ImgUrl;
	private String VideoUrl;
	private String AudioUrl;
	public String getAudioUrl() {
		return AudioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		AudioUrl = audioUrl;
	}

	private String Name;
	private String Intruduce;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	public String getVideoUrl() {
		return VideoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		VideoUrl = videoUrl;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getIntruduce() {
		return Intruduce;
	}

	public void setIntruduce(String intruduce) {
		Intruduce = intruduce;
	}
}
