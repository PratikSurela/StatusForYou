package com.dreamsdevelopers.statusforyou.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("s_id")
	@Expose
	private String sId;

	@SerializedName("s_cid")
	@Expose
	private String sCid;

	@SerializedName("s_hen")
	@Expose
	private String sHen;

	@SerializedName("s_hindi")
	@Expose
	private String sHindi;

	@SerializedName("like")
	@Expose
	private String like;

	@SerializedName("s_date")
	@Expose
	private String sDate;

	public void setSId(String sId){
		this.sId = sId;
	}

	public String getSId(){
		return sId;
	}

	public void setSCid(String sCid){
		this.sCid = sCid;
	}

	public String getSCid(){
		return sCid;
	}

	public void setSHen(String sHen){
		this.sHen = sHen;
	}

	public String getSHen(){
		return sHen;
	}

	public void setSHindi(String sHindi){
		this.sHindi = sHindi;
	}

	public String getSHindi(){
		return sHindi;
	}

	public void setLike(String like){
		this.like = like;
	}

	public String getLike(){
		return like;
	}

	public void setSDate(String sDate){
		this.sDate = sDate;
	}

	public String getSDate(){
		return sDate;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"s_id = '" + sId + '\'' + 
			",s_cid = '" + sCid + '\'' + 
			",s_hen = '" + sHen + '\'' + 
			",s_hindi = '" + sHindi + '\'' + 
			",like = '" + like + '\'' + 
			",s_date = '" + sDate + '\'' + 
			"}";
		}
}