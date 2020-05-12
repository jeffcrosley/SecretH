package com.techelevator.model;

public class Game {
    
    private String name;
    private Integer numberOfPlayers;
    private Long sheepPolicies;
    private Long wolfPolicies;
    private Long president;
    private Long chancellor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public Long getSheepPolicies() {
		return sheepPolicies;
	}

	public void setSheepPolicies(Long sheepPolicies) {
		this.sheepPolicies = sheepPolicies;
	}

	public Long getWolfPolicies() {
		return wolfPolicies;
	}

	public void setWolfPolicies(Long wolfPolicies) {
		this.wolfPolicies = wolfPolicies;
	}

	public Long getPresident() {
		return president;
	}

	public void setPresident(Long president) {
		this.president = president;
	}

	public Long getChancellor() {
		return chancellor;
	}

	public void setChancellor(Long chancellor) {
		this.chancellor = chancellor;
	}

	public Game() {

	}    
}