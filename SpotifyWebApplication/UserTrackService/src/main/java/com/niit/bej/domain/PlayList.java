package com.niit.bej.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;


import java.util.List;
import java.util.Objects;

public class PlayList {
    @MongoId
    private String name;
    private List<Track> tracks;

    public PlayList() {
    }

    public PlayList(String name, List<Track> tracks) {
        this.name = name;
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "PlayList{" + "name='" + name + '\'' + ", tracks=" + tracks + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return Objects.equals(name, playList.name) && Objects.equals(tracks, playList.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tracks);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
