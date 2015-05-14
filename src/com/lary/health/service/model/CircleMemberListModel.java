package com.lary.health.service.model;

import java.io.Serializable;
import java.util.List;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-13下午9:14:06
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈 组员 list
 */
public class CircleMemberListModel extends BaseModel implements Serializable{
	private List<CircleMemberModel> rows;

	public List<CircleMemberModel> getRows() {
		return rows;
	}

	public void setRows(List<CircleMemberModel> rows) {
		this.rows = rows;
	}
}
