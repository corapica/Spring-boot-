package tn.biat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.biat.model.Compte;

@Repository
public interface CompteRepository extends MongoRepository<Compte, String> {

	public List<Compte> findByadminManager (String adminManager) ;
}
