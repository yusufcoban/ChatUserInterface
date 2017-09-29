package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "chatroomid")
	private int chatroomid;
	@Column(name = "messageid")
	private int messageid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChatid() {
		return chatroomid;
	}

	public void setChatid(int chatid) {
		this.chatroomid = chatid;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}

	public int getChatroomid() {
		return chatroomid;
	}

	public void setChatroomid(int chatroomid) {
		this.chatroomid = chatroomid;
	}

	public chat() {

	}

	public chat(int chatroomid, int messageid) {
		this.chatroomid = chatroomid;
		this.messageid = messageid;
	}
	
}