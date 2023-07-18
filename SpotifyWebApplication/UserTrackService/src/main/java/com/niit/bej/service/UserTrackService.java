package com.niit.bej.service;

import com.niit.bej.domain.PlayList;
import com.niit.bej.domain.Track;
import com.niit.bej.domain.User;
import com.niit.bej.exception.*;

import java.util.List;

public interface UserTrackService {
    User addUser(User user) throws UserAlreadyExistsException;

    PlayList addSongsToUserPlaylist(String playListName, Track song, String emailId) throws UserNotFoundException, PlayListNotPresentException, TrackAlreadyExistsException, PlayListDoesNotExistException;

    boolean deletePlayList(String emailId, String playListName) throws TrackNotFoundException, UserNotFoundException, PlayListNotPresentException;

    List<PlayList> displayPlaylist(String emailId) throws UserNotFoundException, PlayListDoesNotExistException;

    List<Track> getAllTracks1() throws TrackNotFoundException;

    User createPlaylist(PlayList playlist, String emailId) throws PlayListAlreadyExistException, UserNotFoundException, TrackNotFoundException;

    boolean deleteTrack(String emailId, String trackId, String playListName) throws TrackNotFoundException, UserNotFoundException, PlayListNotPresentException, PlayListDoesNotExistException;

    List<Track> getTrackFromPlaylist(String playlistName, String emailId) throws PlayListDoesNotExistException, UserNotFoundException, TrackNotFoundException;

    Track getTrackByName(String trackName) throws TrackNotFoundException;

}
