package tn.biat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.biat.model.Compte;
import tn.biat.repository.CompteRepository;

@Service
public class CompteService {

	@Autowired
	private CompteRepository compteRepository ;
	
	public Compte create (String projectName, String key, String email) {
		
		Compte compte = new Compte ("","","",0);
		
		List<Compte> Listecompte =compteRepository.findByadminManager(email);
		int compteNumber =Listecompte.size();
		if (compteNumber < 10) {
		return compteRepository.save(new Compte(projectName, key, email, 9 - compteNumber)) ;
		}
		return compte ;
	}
	
	
	
//	public Compte GetByFirstName(String firstName) throws IOException {
//		
//	}
}
