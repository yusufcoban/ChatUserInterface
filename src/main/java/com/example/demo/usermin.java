package com.example.demo;

public class usermin {
public int id;
public String name;

public usermin(){
	
}

public usermin(user a){
	this.id=a.getId();
	this.name=a.getVorname()+""+a.getName();
	
}

@SuppressWarnings("null")
public usermin usermin(user a){
	usermin usermins = null;
	usermins.id=a.getId();
	usermins.name=a.getVorname()+""+a.getName();
	return usermins;
}
}
