package com.techelevator.model;

import java.util.List;

public interface GameDao {

	public List<Game> getAllGames();
	public void createGame(Long creatorId, String name, Integer numberOfPlayers);
	
}
