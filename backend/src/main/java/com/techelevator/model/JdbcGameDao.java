package com.techelevator.model;

import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Game> getOpenGames(Long userId) {
		List<Game> openGames = new ArrayList<Game>();
		
		String sqlGetOpenGames = "SELECT game.* "
				+ "FROM game "
				+ "INNER JOIN users_game ON (game.game_id = users_game.game_id) "
				+ "WHERE users_game.user_id != ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetOpenGames, userId);
		while(results.next()) {
			Game theGame = mapRowSetToGame(results);
			openGames.add(theGame);
		}		
		
		return openGames;
	}
	
	public List<Game> getPendingGames(Long userId) {
		List<Game> pendingGames = new ArrayList<Game>();
		
		String sqlGetPendingGames = "SELECT game.* "
									+ "FROM game "
									+ "INNER JOIN users_game ON (game.game_id = users_game.game_id) "
									+ "WHERE users_game.user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPendingGames, userId);
		while(results.next()) {
			Game theGame = mapRowSetToGame(results);
			pendingGames.add(theGame);
		}		
		
		return pendingGames;
	}
	
    private Game mapRowSetToGame(SqlRowSet results) {
        Game theGame = new Game();

        theGame.setName(results.getString("name"));
        theGame.setNumberOfPlayers(toIntExact(results.getLong("number_of_players")));
        theGame.setPresident(results.getLong("president"));
        Long chancellor = results.getLong("chancellor");
        if (chancellor != null) {
        	theGame.setChancellor(chancellor);
        }
        theGame.setSheepPolicies(results.getLong("sheep_policies"));
        theGame.setWolfPolicies(results.getLong("wolf_policies"));        
        
        return theGame;
    }

}
