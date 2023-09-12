package net.penguincoders.tinyurl.repository;

import net.penguincoders.tinyurl.model.Counter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, ObjectId> {
}
