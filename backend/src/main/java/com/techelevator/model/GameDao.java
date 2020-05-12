package com.techelevator.model;

public interface GameDao {

	public void createGame(long creatorId, String name, int numberOfPlayers);
	
}
