package com.niit.bej.controller;

import com.niit.bej.domain.PlayList;
import com.niit.bej.domain.Track;
import com.niit.bej.domain.User;
import com.niit.bej.exception.*;
import com.niit.bej.service.UserTrackService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/userTrack")
public class UserController {
    private final UserTrackService userTrackService;

    @Autowired
    public UserController(UserTrackService userTrackService) {
        this.userTrackService = userTrackService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> newUser(@RequestBody User user) {

        try {
            User saveduser = userTrackService.addUser(user);
            return new ResponseEntity<>(saveduser, HttpStatus.OK);
        } catch (UserAlreadyExistsException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/user/addTrack/{playListName}")
    public ResponseEntity<?> addTrackToUser(HttpServletRequest httpServletRequest, @PathVariable String playListName, @RequestBody Track track) {
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();

            PlayList updatedPlaylist = userTrackService.addSongsToUserPlaylist( playListName,  track, emailId);
            return new ResponseEntity<>(updatedPlaylist, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (TrackAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (PlayListNotPresentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (PlayListDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/user/deleteTrack/{trackId}/{playListName}")
    public ResponseEntity<?> deleteTrack(HttpServletRequest httpServletRequest, @PathVariable String trackId, @PathVariable String playListName) {
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();

            return new ResponseEntity<>(userTrackService.deleteTrack(emailId, trackId, playListName), HttpStatus.OK);
        } catch (TrackNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);

        } catch (PlayListDoesNotExistException | PlayListNotPresentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllTracks1")
    public ResponseEntity<?> getAllTracks1() {
        try {


            return new ResponseEntity<>(userTrackService.getAllTracks1(), HttpStatus.OK);

            } catch (TrackNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/user/createPlaylist")
    public ResponseEntity<?> createPlaylist(HttpServletRequest httpServletRequest, @RequestBody PlayList playList) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User userWithPlaylist = userTrackService.createPlaylist(playList, emailId);
            return new ResponseEntity<>(userWithPlaylist, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PlayListAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (TrackNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/user/deletePlaylist/{playlistName}")
    public ResponseEntity<?> deletePlaylist(HttpServletRequest httpServletRequest,  @PathVariable String playlistName) {
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();

            return new ResponseEntity<>(userTrackService.deletePlayList(emailId,playlistName), HttpStatus.OK);
        } catch (TrackNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);

        } catch (PlayListNotPresentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getPlaylist")
    public ResponseEntity<?> getAllPlaylist(HttpServletRequest httpServletRequest) {
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();
            List<PlayList> playlists = userTrackService.displayPlaylist(emailId);
            return new ResponseEntity<>(playlists, HttpStatus.OK);
        } catch (UserNotFoundException | PlayListDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/user/getPlaylistSong/{playlistName}")
    public ResponseEntity<?> getPlaylistSong(HttpServletRequest httpServletRequest,@PathVariable String playlistName){
        try{
            String emailId = httpServletRequest.getAttribute("emailId").toString();
            List<Track> playlistSong= userTrackService.getTrackFromPlaylist(playlistName,emailId);
            return new ResponseEntity<>(playlistSong, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (PlayListDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (TrackNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getSong/{songName}")
    public ResponseEntity<?> getPlaylistSong1(@PathVariable String songName){
        Track foundTrack= null;
        try {
            foundTrack = userTrackService.getTrackByName(songName);
        } catch (TrackNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundTrack, HttpStatus.OK);

    }

}

