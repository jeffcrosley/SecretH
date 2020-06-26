package com.jeffcrosley.model;

import java.util.List;

public interface GameDao {

	public List<Game> getAllGames();
	public Game getGameById(Long gameId);
	public List<Game> getActiveGames(Long userId);
	public List<Game> getOpenGames(Long userId);
	public List<Game> getPendingGames(Long userId);
	public void joinGame(Long gameId, Long userId);
	public void createGame(Long creatorId, String name, Integer numberOfPlayers);
	public void nominateChancellor(Long gameId, Long chancellorId);
	
}
