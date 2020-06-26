package com.jeffcrosley.model;

public class Game {
    
	private Long gameId;
    private String name;
    private Integer numberOfPlayers;
    private Long sheepPolicies;
    private Long wolfPolicies;
    private Long presidentId;
    private String presidentName;
    private Long chancellorId;
    private String chancellorName;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

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

	public Long getPresidentId() {
		return presidentId;
	}

	public void setPresidentId(Long presidentId) {
		this.presidentId = presidentId;
	}

	public String getPresidentName() {
		return presidentName;
	}

	public void setPresidentName(String presidentName) {
		this.presidentName = presidentName;
	}

	public Long getChancellorId() {
		return chancellorId;
	}

	public void setChancellorId(Long chancellorId) {
		this.chancellorId = chancellorId;
	}

	public String getChancellorName() {
		return chancellorName;
	}

	public void setChancellorName(String chancellorName) {
		this.chancellorName = chancellorName;
	}

	public Game() {

	}

}