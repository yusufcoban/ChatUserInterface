package com.example.demo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class chatbase {
	public String message;
	public boolean sender;
	@Temporal(TemporalType.TIMESTAMP)
	public Date date;



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSender() {
		return sender;
	}

	public void setSender(boolean sender) {
		this.sender = sender;
	}

	public chatbase(String message, boolean sender) {
		super();
		this.message = message;
		this.sender = sender;
	}

	public chatbase(String message, boolean sender, Date date) {
		super();
		this.message = message;
		this.sender = sender;
		this.date = date;
	}

}
