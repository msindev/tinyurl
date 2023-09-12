package net.penguincoders.tinyurl.dao;

import net.penguincoders.tinyurl.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public long getCounter() {
        Query query = new Query();
        Counter counter = mongoTemplate.findOne(query, Counter.class);
        if (counter != null) {
            return counter.getCount();
        }
        return 0;
    }

    public void setCounter(long count) {
        Query query = new Query();
        Counter counter = mongoTemplate.findOne(query, Counter.class);
        if (counter != null) {
            counter.setCount(count);
            mongoTemplate.save(counter);
        } else {
            counter = new Counter();
            counter.setCount(count);
            mongoTemplate.save(counter);
        }
    }
}
