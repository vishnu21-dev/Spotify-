package com.niit.bej.repository;

import com.niit.bej.domain.Track;
import com.niit.bej.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTrackRepository extends MongoRepository<User,String> {
}
