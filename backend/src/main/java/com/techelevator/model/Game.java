package com.techelevator.model;

public class Game {
    
    private String name;
    private int numberOfPlayers;
    private int libPolicies;
    private int fashPolicies;
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
	public int getLibPolicies() {
		return libPolicies;
	}
	public void setLibPolicies(int libPolicies) {
		this.libPolicies = libPolicies;
	}
	public int getFashPolicies() {
		return fashPolicies;
	}
	public void setFashPolicies(int fashPolicies) {
		this.fashPolicies = fashPolicies;
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