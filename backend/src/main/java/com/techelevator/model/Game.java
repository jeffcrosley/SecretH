package com.techelevator.model;

public class Game {
    
    private String name;
    private int numberOfPlayers;
    private int sheepPolicies;
    private int wolfPolicies;
    private String president;
    private String chancellor;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	public int getSheepPolicies() {
		return sheepPolicies;
	}
	public void setSheepPolicies(int sheepPolicies) {
		this.sheepPolicies = sheepPolicies;
	}
	public int getWolfPolicies() {
		return wolfPolicies;
	}
	public void setWolfPolicies(int wolfPolicies) {
		this.wolfPolicies = wolfPolicies;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getChancellor() {
		return chancellor;
	}
	public void setChancellor(String chancellor) {
		this.chancellor = chancellor;
	}
	
	public Game() {

	}    
}