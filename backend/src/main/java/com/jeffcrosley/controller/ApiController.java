package com.jeffcrosley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jeffcrosley.authentication.AuthProvider;
import com.jeffcrosley.authentication.UnauthorizedException;
import com.jeffcrosley.model.Game;
import com.jeffcrosley.model.GameDao;
import com.jeffcrosley.model.User;
import com.jeffcrosley.model.UserDao;

/**
 * ApiController
 */
@RestController
@RequestMapping("/")
public class ApiController {

    @Autowired
    private AuthProvider authProvider;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private GameDao gameDao;
    
    /////////////////////////////////////////////////////
    ////////////////////     USERS     /////////////////
    /////////////////////////////////////////////////////
    
    /*READ A USER BY USERNAME*/
    @GetMapping("/user/{username}")
    public User userByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }
    
    /*READ A USER BY USERNAME*/
    @GetMapping("/userId/{userId}")
    public User userByUsername(@PathVariable Long userId) {
    	return userDao.getUserById(userId);
    }
    
    /*READ A USER'S SECRET ROLE IN A GIVEN GAME*/
    @GetMapping("/userRole/{gameId}/{userId}")
    public String secretRole(@PathVariable Long gameId, @PathVariable Long userId) {
    	return userDao.secretRole(gameId, userId);
    }
    
    /*READ ALL USERS IN A GIVEN GAME*/
    @GetMapping("/usersInGame/{gameId}")
    public List<User> usersInGame(@PathVariable Long gameId) {
    	return userDao.getUsersInGame(gameId);
    }
    
    /////////////////////////////////////////////////////
    ////////////////////     GAMES     /////////////////
    /////////////////////////////////////////////////////    
    
    /*GET A GAME BY ID*/
    @GetMapping("/game/{gameId}")
    public Game getGame(@PathVariable Long gameId) {
    	return gameDao.getGameById(gameId);
    }
    
    /*CREATE A NEW GAME*/
    @PostMapping("/game/{creatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createGame(@RequestBody Game game, @PathVariable Long creatorId) {
        gameDao.createGame(creatorId, game.getName(), game.getNumberOfPlayers());
        return "{\"success\":true}";
    }    
    
    /*JOIN A GAME*/
    @PostMapping("/joinGame/{gameId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String joinGame(@PathVariable Long gameId, @PathVariable Long userId) {
	gameDao.joinGame(gameId, userId);
    	return "{\"success\":true}";
    }    
    
    /*GET ALL GAMES*/
    @GetMapping("/allGames")
    public List<Game> allGames() {
    	return gameDao.getAllGames();
    }
    
    /*GET ACTIVE GAMES*/
    @GetMapping("/activeGames/{userId}")
    public List<Game> activeGames(@PathVariable Long userId) {
    	return gameDao.getActiveGames(userId);
    }
    
    /*GET OPEN GAMES*/
    @GetMapping("/openGames/{userId}")
    public List<Game> openGames(@PathVariable Long userId) {
    	return gameDao.getOpenGames(userId);
    }
    
    /*GET PENDING GAMES*/
    @GetMapping("/pendingGames/{userId}")
    public List<Game> pendingGames(@PathVariable Long userId) {
    	return gameDao.getPendingGames(userId);
    }

    
    /////////////////////////////////////////////////////
    ////////////////////ROLE RESTRICTIONS ///////////////
    /////////////////////////////////////////////////////       

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String authorizedOnly() throws UnauthorizedException {
        /*
        You can lock down which roles are allowed by checking
        if the current user has a role.
        
        In this example, if the user does not have the admin role
        we send back an unauthorized error.
        */
        if (!authProvider.userHasRole(new String[] { "admin" })) {
            throw new UnauthorizedException();
        }
        return "Success";
    }
}