package com.techelevator.model;

import static java.lang.Math.toIntExact;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
		long presidentId = ThreadLocalRandom.current().nextInt(1, numberOfPlayers + 1);
	    	
        String sqlInsertNewGame = "INSERT INTO game "
                                    + "(name, number_of_players, president) "
                                    + "VALUES (?, ?, ?) RETURNING game_id";
        
        String sqlAddCurrentUserToNewGame = "INSERT INTO users_game "
                                            + "(user_id, game_id) "
                                            + "VALUES (?, ?)";

        long gameId = jdbcTemplate.queryForObject(sqlInsertNewGame, Long.class, name, numberOfPlayers, presidentId);
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
