package com.example.smartactions;

public class Contact {
	
	//private variables
	String name;
	int ringvol;
	String mode;
	String act;
	String auto;
	String btooth;
	String wifi;
	int bright;
	// Empty constructor
	public Contact(){
		
	}

public Contact(String name, int vol, String mode, String act,String auto, String btooth,String wifi, int bright){
	this.name=name;
	this.mode=mode;
	this.ringvol=vol;
	this.act=act;
	this.auto=auto;
	this.btooth=btooth;
	this.wifi=wifi;
	this.bright=bright;
	
}
	// constructor
	
	// getting user
	public String getName(){
		return this.name;
	}
	
	// setting user
	public void setName(String user){
		this.name = user;
	}
	public int getRingvol(){
		return this.ringvol;
	}
	
	public String getMode(){
		return this.mode;
	}
	public String getAct(){
		return this.act;
	}
	
}