package tn.biat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.biat.model.ResultResearch;

@Repository
public interface ResultReasearchRepository extends MongoRepository<ResultResearch, String> {

}