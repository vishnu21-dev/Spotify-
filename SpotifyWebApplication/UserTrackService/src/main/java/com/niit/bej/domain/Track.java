package com.niit.bej.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Track {


    private String trackName;
    private String imageUrl;
    private String artist;
    private String filePath;

    public Track() {
    }

    public Track(String trackName, String imageUrl, String artist, String filePath) {
        this.trackName = trackName;
        this.imageUrl = imageUrl;
        this.artist = artist;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackName='" + trackName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", artist='" + artist + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(trackName, track.trackName) && Objects.equals(imageUrl, track.imageUrl) && Objects.equals(artist, track.artist) && Objects.equals(filePath, track.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackName, imageUrl, artist, filePath);
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
