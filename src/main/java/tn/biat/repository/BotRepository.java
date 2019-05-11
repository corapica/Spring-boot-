package tn.biat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.biat.model.Bot;

@Repository
public interface BotRepository  extends MongoRepository<Bot, String> {

}
