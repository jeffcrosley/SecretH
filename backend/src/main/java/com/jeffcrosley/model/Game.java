package com.jeffcrosley.model;

public class Game {
    
	private Long gameId;
    private String name;
    private Integer numberOfPlayers;
    private Long sheepPolicies;
    private Long wolfPolicies;
    private Long presidentId;
    private Long lastPresidentId;
    private Long chancellorId;
    private Long lastChancellorId;
    private Long nomineeId;
    private Long failures;
    
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

	public Long getLastPresidentId() {
		return lastPresidentId;
	}

	public void setLastPresidentId(Long lastPresidentId) {
		this.lastPresidentId = lastPresidentId;
	}

	public Long getChancellorId() {
		return chancellorId;
	}

	public void setChancellorId(Long chancellorId) {
		this.chancellorId = chancellorId;
	}

	public Long getLastChancellorId() {
		return lastChancellorId;
	}

	public void setLastChancellorId(Long lastChancellorId) {
		this.lastChancellorId = lastChancellorId;
	}

	public Long getNomineeId() {
		return nomineeId;
	}

	public void setNomineeId(Long nomineeId) {
		this.nomineeId = nomineeId;
	}

	public Long getFailures() {
		return failures;
	}

	public void setFailures(Long failures) {
		this.failures = failures;
	}

	public Game() {

	}

}