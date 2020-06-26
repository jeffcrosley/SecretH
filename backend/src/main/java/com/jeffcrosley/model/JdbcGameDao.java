package com.jeffcrosley.model;

import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcGameDao implements GameDao {
	 
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public Game getGameById(Long gameId) {
		Game theGame = new Game();
		
		String sqlGetGameById = "SELECT * " + 
				"FROM game " + 
				"WHERE game_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGameById, gameId);
		while (results.next()) {
			theGame = mapRowSetToGame(results);
		}
		
		return theGame;
	}

	@Override
	public void createGame(Long creatorId, String name, Integer numberOfPlayers) {
	    	
        String sqlInsertNewGame = "INSERT INTO game "
                                    + "(name, number_of_players) "
                                    + "VALUES (?, ?) RETURNING game_id";
        
        String sqlAddCurrentUserToNewGame = "INSERT INTO users_game "
                                            + "(user_id, game_id) "
                                            + "VALUES (?, ?)";

        long gameId = jdbcTemplate.queryForObject(sqlInsertNewGame, Long.class, name, numberOfPlayers);
        jdbcTemplate.update(sqlAddCurrentUserToNewGame, creatorId, gameId);

	}

	@Override
	public void joinGame(Long gameId, Long userId) {
		
		// ADD USER TO GAME
        String sqlAddUserToGame = "INSERT INTO users_game "
                                  + "(user_id, game_id) "
                                  + "VALUES (?, ?)";
        
        jdbcTemplate.update(sqlAddUserToGame, userId, gameId);
        
		// CHECK IF ALL SLOTS ARE FILLED, START GAME IF YES 
		Integer totalSeats = null;
		String sqlGetTotalSeats = "SELECT number_of_players FROM game WHERE game_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetTotalSeats, gameId);
		while (results.next()) {
			totalSeats = (int) results.getLong("number_of_players");
		}

		Integer filledSeats = getPlayerIds(gameId).size();

		if (totalSeats <= filledSeats) {
			startGame(gameId);
		}

	}
	
	@Override
	public List<Game> getAllGames() {
		List<Game> allGames = new ArrayList<Game>();
		
		String sqlGetAllGames = "SELECT * from game";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllGames);
		while(results.next()) {
			Game theGame = mapRowSetToGame(results);
			allGames.add(theGame);
		}
		
		return allGames;
	}
	
	@Override
	public List<Game> getActiveGames(Long userId) {
		List<Game> activeGames = new ArrayList<Game>();

		String sqlGetActiveGames = "SELECT game.* "
				+ "FROM game "
				+ "INNER JOIN users_game ON (game.game_id = users_game.game_id) "
				+ "WHERE users_game.user_id = ? AND game.president IS NOT NULL "
				+ "ORDER BY game.game_id";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetActiveGames, userId);
		while(results.next()) {
			Game theGame = mapRowSetToGame(results);
			activeGames.add(theGame);
		}		
		
		return activeGames;
	}

	@Override
	public List<Game> getOpenGames(Long userId) {
		List<Game> openGames = new ArrayList<Game>();
		
		String sqlGetOpenGames = "SELECT DISTINCT game.* "
				+ "FROM users_game "
				+ "LEFT OUTER JOIN game ON (users_game.game_id = game.game_id) "
				+ "WHERE users_game.game_id NOT IN "
					+ "(SELECT game_id "
					+ "FROM users_game "
					+ "WHERE user_id = ?) "
				+ "AND game.president IS NULL "
				+ "ORDER BY game.game_id";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetOpenGames, userId);
		while(results.next()) {
			Game theGame = mapRowSetToGame(results);
			openGames.add(theGame);
		}		
		
		return openGames;
	}
	
	@Override
	public List<Game> getPendingGames(Long userId) {
		List<Game> pendingGames = new ArrayList<Game>();
		
		String sqlGetPendingGames = "SELECT game.* "
									+ "FROM game "
									+ "INNER JOIN users_game ON (game.game_id = users_game.game_id) "
									+ "WHERE users_game.user_id = ? AND game.president IS NULL "
									+ "ORDER BY game.game_id";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPendingGames, userId);
		while(results.next()) {
			Game theGame = mapRowSetToGame(results);
			pendingGames.add(theGame);
		}		
		
		return pendingGames;
	}

	@Override
	public void nominateChancellor(Long gameId, Long chancellorId) {
		String sqlNominateChancellor = "UPDATE game SET chancellor_nominee = ? " + 
				"WHERE game_id = ?";
		
		jdbcTemplate.update(sqlNominateChancellor, chancellorId, gameId);
		
	}	
	
	private void startGame(Long gameId) {

		// FIND PLAYERS IN THIS GAME AND ASSIGN A RANDOM ID TO THE PRESIDENT
		Long presidentId = getPresidentId(gameId);

		// ASSIGN A PRESIDENT
		String sqlAssignPresident = "UPDATE game SET president = ? "
									+ "WHERE game_id = ?";

		jdbcTemplate.update(sqlAssignPresident, presidentId, gameId);

		// ASSIGN SECRET ROLES
		Integer numberOfPlayers = getPlayerIds(gameId).size();
		
		List<Long> playerIds = getPlayerIds(gameId);
		List<String> roles = getRoles(gameId);
		
		Map<Long, String> assignments = new HashMap<Long, String>();
		
		for (int i = 0; i < numberOfPlayers; i++) {
			assignments.put(playerIds.get(i), roles.get(i));
		}
		
		String sqlAssignRole = "UPDATE users_game SET secret_role = ? "
								+ "WHERE user_id = ? AND game_id = ?";
		
		for (Map.Entry<Long, String> player : assignments.entrySet()) {
			Long userId = player.getKey();
			String secretRole = player.getValue();
			jdbcTemplate.update(sqlAssignRole, secretRole, userId, gameId);
		}
		
	}

	private Long getPresidentId(Long gameId) {
		Long presidentId = null;

		List<Long> playerIds = getPlayerIds(gameId);
		Integer numberOfPlayers = playerIds.size();
		Integer idIndex = (int) (Math.random() * numberOfPlayers);
		presidentId = playerIds.get(idIndex);

		return presidentId;
	}

	private List<Long> getPlayerIds(Long gameId) {
		List<Long> playerIds = new ArrayList<Long>();

		String sqlGetAllUserIdsInGame = "SELECT DISTINCT users.id "
										+ "FROM users_game "
										+ "INNER JOIN users ON (users_game.user_id = users.id) "
										+ "WHERE users_game.user_id IN "
												+ "(SELECT user_id "
												+ "FROM users_game "
												+ "WHERE game_id = ?) "
										+ "ORDER BY users.id";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllUserIdsInGame, gameId);
		while(results.next()) {
			Long playerId = results.getLong("id");
			playerIds.add(playerId);
		}

		return playerIds;
	}
	
	private List<String> getRoles(Long gameId) {
		Integer numberOfPlayers = getPlayerIds(gameId).size();
		List<String> roles = new ArrayList<String>();
		
		roles.add("Big Bad Wolf");
		roles.add("Wolf");
		roles.add("Sheep");
		roles.add("Sheep");
		roles.add("Sheep");
		
		if (numberOfPlayers >= 6) {
			roles.add("Sheep");
		} 
		
		if (numberOfPlayers >= 7) {
			roles.add("Wolf");
		} 
		
		if (numberOfPlayers >= 8) {
			roles.add("Sheep");
		} 
		
		if (numberOfPlayers >= 9) {
			roles.add("Wolf");
		} 
		
		if (numberOfPlayers == 10) {
			roles.add("Sheep");
		}
		
		Collections.shuffle(roles);
		
		return roles;
	}
	
    private Game mapRowSetToGame(SqlRowSet results) {
        Game theGame = new Game();

        theGame.setGameId(results.getLong("game_id"));
        theGame.setName(results.getString("name"));
        theGame.setNumberOfPlayers(toIntExact(results.getLong("number_of_players")));
        theGame.setSheepPolicies(results.getLong("sheep_policies"));
        theGame.setWolfPolicies(results.getLong("wolf_policies"));        
        theGame.setPresidentId(results.getLong("president"));
        Long lastPresident = results.getLong("last_president");
        if (lastPresident != null) {
        	theGame.setLastPresidentId(lastPresident);
        }
        Long chancellor = results.getLong("chancellor");
        if (chancellor != null) {
        	theGame.setChancellorId(chancellor);
        }
        Long lastChancellor = results.getLong("last_chancellor");
        if (lastChancellor != null) {
        	theGame.setLastChancellorId(lastChancellor);
        }
        Long nominee = results.getLong("chancellor_nominee");
        if (nominee != null) {
        	theGame.setNomineeId(nominee);
        }        
        theGame.setFailures(results.getLong("election_failures"));
        
        return theGame;
    }


}
