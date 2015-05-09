package com.lary.health.service.model;

import java.util.List;

import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午7:07:41
 * @Email zhaoyp@witmob.com
 * @Description  原创音乐model
 */
public class OriginalListModel extends BaseModel {
	private List<OriginalItemModel> rows;
	private String curpage;
	private String totalpage;

	public List<OriginalItemModel> getRows() {
		return rows;
	}

	public void setRows(List<OriginalItemModel> rows) {
		this.rows = rows;
	}

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

}
