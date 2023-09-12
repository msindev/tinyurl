package net.penguincoders.tinyurl.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "url")
public class Url {
    @Id
    private ObjectId id;
    private String url;
    private String tinyUrl;
}
