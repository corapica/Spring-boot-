package tn.biat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.biat.model.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String>{

	public Client findByFirstName (String firstName) ;
	public List<Client> findByEmail(String email);
	 
}