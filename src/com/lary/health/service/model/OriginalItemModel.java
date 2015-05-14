package com.lary.health.service.model;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午7:04:18
 * @Email zhaoyp@witmob.com
 * @Description 原创音乐model
 */
public class OriginalItemModel extends BaseModel {
	private String Id;
	private String Name;
	private String ImgUrl;
	private String VideoUrl;
	private String Intruduce;
	private String NickName;
	private String PhotoImg;

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getPhotoImg() {
		return PhotoImg;
	}

	public void setPhotoImg(String photoImg) {
		PhotoImg = photoImg;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public String getIntruduce() {
		return Intruduce;
	}

	public void setIntruduce(String intruduce) {
		Intruduce = intruduce;
	}
}
