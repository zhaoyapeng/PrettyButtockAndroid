package com.lary.health.service.model;

import java.util.List;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-12下午11:23:55
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈 群组list
 */
public class CircleListModel extends BaseModel {

	private List<CircleItemModel> rows;

	public List<CircleItemModel> getRows() {
		return rows;
	}

	public void setRows(List<CircleItemModel> rows) {
		this.rows = rows;
	}
}
