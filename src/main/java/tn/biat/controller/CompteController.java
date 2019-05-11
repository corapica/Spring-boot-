package tn.biat.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.biat.model.Compte;
import tn.biat.service.CompteService;

@RestController
public class CompteController {

	@Autowired
	private CompteService compteService ;
	
	@RequestMapping("/createCompte")
	@PreAuthorize("hasRole('ADMIN')")
	public String create(@RequestParam String ProjectName, @RequestParam String key, Principal principal) {
		Compte compte = compteService.create(ProjectName, key, principal.getName());
		return compte.toString();
	}
}