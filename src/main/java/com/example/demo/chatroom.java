package com.example.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chatroom")
public class chatroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "senderid")
	private int senderid;
	@Column(name = "receiverid")
	private int receiverid;

	@Column(name = "messageid")
	private int messageid;

	@ManyToMany
	@JoinTable(name = "chat", joinColumns = @JoinColumn(name = "chatroomid", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "messageid", referencedColumnName = "id"))
	private List<message> messages;


	public List<message> getMessages() {
		return messages;
	}

	public void setMessages(List<message> messages) {
		this.messages = messages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderid() {
		return senderid;
	}

	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}

	public int getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}

	public chatroom() {

	}

	public chatroom(int senderid, int receiverid, int messageid) {
		this.senderid = senderid;
		this.receiverid = receiverid;
		this.messageid = messageid;
	}
	

}
