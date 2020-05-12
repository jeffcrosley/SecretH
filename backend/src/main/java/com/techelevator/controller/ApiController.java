package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Game;
import com.techelevator.model.GameDao;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

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
    
    /////////////////////////////////////////////////////
    ////////////////////     GAMES     /////////////////
    /////////////////////////////////////////////////////    
    
    /*CREATE A NEW GAME*/
    @PostMapping("/game/{creatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createGame(@RequestBody Game game, @PathVariable long creatorId) {
        gameDao.createGame(creatorId, game.getName(), game.getNumberOfPlayers());
        return "{\"success\":true}";
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