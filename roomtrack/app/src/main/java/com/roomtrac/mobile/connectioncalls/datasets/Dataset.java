package com.roomtrac.mobile.connectioncalls.datasets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dataset {

	@SerializedName("list")
	@Expose
	private String list;
	@SerializedName("data")
	@Expose
	private String data;
	public String getList() {
		return this.list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}


}
