package com.lary.health.service.model;

import java.util.List;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午5:58:54
 * @Email zhaoyp@witmob.com
 * @Description 垫上没操 list model
 */
public class GymnasticsListModel extends BaseModel {
	private String curpage;
	private String totalpage;
	private List<GymnasticsListItemModel> rows;

	public String getCurpage() {
		return curpage;
	}

	public void setCurpage(String curpage) {
		this.curpage = curpage;
	}

	public String getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}

	public List<GymnasticsListItemModel> getRows() {
		return rows;
	}

	public void setRows(List<GymnasticsListItemModel> rows) {
		this.rows = rows;
	}
}
