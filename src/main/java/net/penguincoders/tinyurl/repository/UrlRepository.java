package net.penguincoders.tinyurl.repository;

import net.penguincoders.tinyurl.model.Url;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, ObjectId> {
    Optional<Url> findByUrl(String url);
}
