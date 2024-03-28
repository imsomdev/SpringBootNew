package com.somdev.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.somdev.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

}
