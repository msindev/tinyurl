package net.penguincoders.tinyurl.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "counter")
public class Counter {
    @Id
    private ObjectId id;

    private long count;
}
