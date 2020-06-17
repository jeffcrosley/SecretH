package com.techelevator.model;

import java.util.List;

public interface GameDao {

	public List<Game> getAllGames();
	public List<Game> getOpenGames(Long userId);
	public List<Game> getPendingGames(Long userId);
	public void createGame(Long creatorId, String name, Integer numberOfPlayers);
	
}
