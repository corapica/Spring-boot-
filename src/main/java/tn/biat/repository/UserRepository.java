package tn.biat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.biat.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByEmailIgnoreCase(String username);

}
