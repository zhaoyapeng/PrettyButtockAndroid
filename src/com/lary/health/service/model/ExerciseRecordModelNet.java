package com.lary.health.service.model;

import java.util.List;

import netlib.model.BaseModel;

public class ExerciseRecordModelNet extends BaseModel {

	private String curpage;
	private String totalpage;
	private List<ExerciseRecordModel> rows;
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
	public List<ExerciseRecordModel> getRows() {
		return rows;
	}
	public void setRows(List<ExerciseRecordModel> rows) {
		this.rows = rows;
	}
	
	
	
}
