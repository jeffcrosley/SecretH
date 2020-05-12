package com.techelevator.model;

import java.util.concurrent.ThreadLocalRandom;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcGameDao implements GameDao {
	 
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public void createGame(long creatorId, String name, int numberOfPlayers) {
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

}
