package com.twg.springboot.webappclient.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Entry 
{
   private long id;
   
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date entrydate;
   
   private String description;
   private long userid;
   
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
   
   
}
