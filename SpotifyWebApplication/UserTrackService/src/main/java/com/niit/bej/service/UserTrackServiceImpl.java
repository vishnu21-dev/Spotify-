package com.niit.bej.service;

import com.niit.bej.domain.PlayList;
import com.niit.bej.domain.Track;
import com.niit.bej.domain.User;
import com.niit.bej.exception.*;
import com.niit.bej.proxy.UserProxy;
import com.niit.bej.repository.TrackRepository;
import com.niit.bej.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserTrackServiceImpl implements UserTrackService {

    private final UserTrackRepository userTrackRepository;
    private final UserProxy userProxy;
    private final TrackRepository trackRepository;

    @Autowired
    public UserTrackServiceImpl(UserTrackRepository userTrackRepository, UserProxy userProxy, TrackRepository trackRepository) {
        this.userTrackRepository = userTrackRepository;
        this.userProxy = userProxy;
        this.trackRepository = trackRepository;
    }


    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        //check if the user is present in the db
        if (userTrackRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException("User Already Exists");
        } else {
            User savedUser = userTrackRepository.save(user);
            if (!savedUser.getName().isEmpty()) userProxy.registerUser(user);
            return savedUser;
        }
    }

    @Override
    public User createPlaylist(PlayList userPlaylist, String emailId) throws PlayListAlreadyExistException, UserNotFoundException, TrackNotFoundException {
        if (userTrackRepository.findById(emailId).isPresent()) {
            User loggedUser = userTrackRepository.findById(emailId).get();
            List<PlayList> playList = loggedUser.getUserPlayList();
            if (playList == null) {
                loggedUser.setUserPlayList(Collections.singletonList(userPlaylist));

                userTrackRepository.save(loggedUser);
                return loggedUser;
            } else {
                if (playList.stream().anyMatch(filter -> filter.getName().equalsIgnoreCase(userPlaylist.getName()))) {
                    throw new PlayListAlreadyExistException("Playlist Exist Already!");
                } else {

                    playList.add(userPlaylist);
                    loggedUser.setUserPlayList(playList);
                    userTrackRepository.save(loggedUser);
                }
            }
            return loggedUser;
        } else {
            throw new UserNotFoundException("user is not found !");
        }

    }


    @Override
    public PlayList addSongsToUserPlaylist(String playListName, Track track, String emailId) throws TrackAlreadyExistsException, PlayListDoesNotExistException, UserNotFoundException {

        Optional<User> optionalLoggedInUser = userTrackRepository.findById(emailId);
        if (optionalLoggedInUser.isPresent()) {
            User loggedInUser = userTrackRepository.findById(emailId).get();
            PlayList playList = null;
            List<PlayList> userPlayList = loggedInUser.getUserPlayList();

            if (userPlayList != null) {

                Optional<PlayList> requestedPlaylist = userPlayList.stream().filter(findPlaylist -> findPlaylist.getName().equalsIgnoreCase(playListName)).findAny();
                if (requestedPlaylist.isPresent()) {

                    PlayList updatePlaylist = requestedPlaylist.get();
                    List<Track> tracks = updatePlaylist.getTracks();
                    if (tracks == null) {
                        updatePlaylist.setTracks(Collections.singletonList(track));
                    } else {
                        Optional<Track> trackToBeAdded = tracks.stream().filter(f -> f.getTrackName().equalsIgnoreCase(track.getTrackName())).findAny();
                        if (trackToBeAdded.isPresent()) {
                            throw new TrackAlreadyExistsException("Track already present in playlist");
                        } else {
                            tracks.add(track);
                            updatePlaylist.setTracks(tracks);

                            loggedInUser.setUserPlayList(userPlayList);
                            playList = updatePlaylist;
                        }
                    }
                } else {
                    throw new PlayListDoesNotExistException("PlayList not present");
                }
                userTrackRepository.save(loggedInUser);
                return playList;
            } else {
                throw new PlayListDoesNotExistException("NO PLAYLIST OF THIS NAME");
            }
        } else throw new UserNotFoundException(" User not found");
    }

    @Override
    public List<Track> getAllTracks1() throws TrackNotFoundException {
        List<Track> allTrack = trackRepository.findAll();
        if (allTrack.isEmpty()) {
            throw new TrackNotFoundException("no tracks present");
        } else return allTrack;
    }


    @Override
    public boolean deleteTrack(String emailId, String trackId, String playListName) throws UserNotFoundException, PlayListDoesNotExistException, TrackNotFoundException {

        if (userTrackRepository.findById(emailId).isPresent()) {
            User loggeduser = userTrackRepository.findById(emailId).get();
            PlayList singlePlaylist;
            if (loggeduser.getUserPlayList() == null) {
                throw new PlayListDoesNotExistException("playlist not found!");
            } else {
                List<PlayList> playlistList = loggeduser.getUserPlayList();
                if (playlistList.stream().anyMatch(filter -> filter.getName().equalsIgnoreCase(playListName))) {
                    singlePlaylist = playlistList.stream().filter(filter -> filter.getName().equalsIgnoreCase(playListName)).findAny().get();
                    List<Track> trackList = singlePlaylist.getTracks();
                    if (trackList.stream().anyMatch(filter -> filter.getTrackName().equalsIgnoreCase(trackId))) {
                        Track trackToBeDeleted = trackList.stream().filter(filter -> filter.getTrackName().equalsIgnoreCase(trackId)).findAny().get();
                        trackList.remove(trackToBeDeleted);
                        singlePlaylist.setTracks(trackList);
                        loggeduser.setUserPlayList(playlistList);
                        userTrackRepository.save(loggeduser);
                        return true;
                    } else {
                        throw new TrackNotFoundException("Song is not found!");
                    }
                }
            }
            return true;
        } else {
            throw new UserNotFoundException("user not found!");
        }
    }

    @Override
    public List<Track> getTrackFromPlaylist(String playlistName, String emailId) throws PlayListDoesNotExistException, UserNotFoundException, TrackNotFoundException {
        Optional<User> userExists = userTrackRepository.findById(emailId);
        if (userExists.isPresent()) {
            User user = userExists.get();
            List<PlayList> userPlaylist = user.getUserPlayList();

            if (userPlaylist == null) {
                throw new PlayListDoesNotExistException(" no playList present");
            }
            Optional<PlayList> requestedPlayList = userPlaylist.stream().filter(f -> f.getName().equalsIgnoreCase(playlistName)).findAny();
            if (requestedPlayList.isPresent()) {
                List<Track> userTracks = requestedPlayList.get().getTracks();
                if (userTracks == null) {
                    throw new TrackNotFoundException("There is no song present");
                }
                return userTracks;
            } else throw new PlayListDoesNotExistException(" no playlist present of this name");
        } else throw new UserNotFoundException(" user not present");
    }

    @Override
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        Track track = trackRepository.findTrackByTrackNameEqualsIgnoreCase(trackName);
        if (track == null) {
            throw new TrackNotFoundException(" TRACK NOT EXIST");

        } else {
            return track;
        }
    }


    @Override
    public boolean deletePlayList(String emailId, String playListName) throws UserNotFoundException, PlayListNotPresentException {
        Optional<User> userExists = userTrackRepository.findById(emailId);
        if (userExists.isPresent()) {
            User user = userExists.get();
            List<PlayList> userPlayList = user.getUserPlayList();
            if (userPlayList == null) {
                throw new PlayListNotPresentException("no playList Present");
            }
            Optional<PlayList> requestedPlayList = userPlayList.stream().filter(f -> f.getName().equalsIgnoreCase(playListName)).findAny();

            if (requestedPlayList.isPresent()) {
                PlayList playlistToDelete = requestedPlayList.get();
                userPlayList.remove(playlistToDelete);
                userTrackRepository.save(user);
                return true;
            } else throw new PlayListNotPresentException("NO PLAYLIST OF THIS NAME");

        } else throw new UserNotFoundException("USER NOT PRESENT");

    }

    @Override
    public List<PlayList> displayPlaylist(String emailId) throws UserNotFoundException, PlayListDoesNotExistException {
        Optional<User> existingUser = userTrackRepository.findById(emailId);
        if (existingUser.isPresent()) {
            List<PlayList> existingUserPlaylist = existingUser.get().getUserPlayList();
            if (existingUserPlaylist == null) {
                throw new PlayListDoesNotExistException(" Playlist Does not Exist");
            } else {
                return existingUserPlaylist;
            }
        } else throw new UserNotFoundException(" User Not Present");
    }
}