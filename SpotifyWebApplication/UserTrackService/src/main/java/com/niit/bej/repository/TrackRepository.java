package com.niit.bej.repository;

import com.niit.bej.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track,String> {
    Track findTrackByTrackNameEqualsIgnoreCase(String trackName);
}
