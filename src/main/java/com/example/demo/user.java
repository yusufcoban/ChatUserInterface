package com.example.demo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "user")
public class user implements Serializable {
	private static final long serialVersionUID = 1L;
	// @GeneratedValue
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "vorname")
	private String vorname;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;

	@ManyToMany
	@JoinTable(name = "chatroom", joinColumns = @JoinColumn(name = "receiverid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "senderid", referencedColumnName = "id"))
	private List<user> receiver;

	@ManyToMany
	@JoinTable(name = "chatroom", joinColumns = @JoinColumn(name = "senderid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "receiverid", referencedColumnName = "id"))
	private List<user> sender;

	public Set<Integer> getReceiver() {
		Set<Integer> output = new HashSet<Integer>();
		for (user a : receiver) {
			output.add(a.getId());
		}

		return output;
	}

	public void setReceiver(List<user> receiver) {
		this.receiver = receiver;
	}

	public Set<Integer> getSender() {
		Set<Integer> output = new HashSet<Integer>();
		for (user a : sender) {
			output.add(a.getId());
		}
		return output;
	}

	public void setSender(List<user> sender) {
		this.sender = sender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public user() {

	}

	public user(String vorname, String nachname, String password) {
		this.vorname = vorname;
		this.name = vorname;
		this.password = password;
	}

	public user(String vorname, String nachname, String password, String email) {
		this.vorname = vorname;
		this.name = vorname;
		this.password = password;
		this.email = email;
	}

	public void save(user user) {
		// TODO Auto-generated method stub
		
	}

}
