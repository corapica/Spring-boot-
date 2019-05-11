package tn.biat.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.model.User;
import tn.biat.service.UserService;

@RestController
public class UserContoller {

	@Autowired private UserService userService ;
	
	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>>getAllusers(Principal principal){
		
			List<User> users = userService.findAll();
			
		users = users.stream().filter(user -> user.getEmail().equals(principal.getName()) || user.getManagerMail().equals(principal.getName()) ).collect(Collectors.toList());
	
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);

	}
	
	@GetMapping(value="/getuser")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User>getUser(Principal principal){
		
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PutMapping(value="/update")
	@PreAuthorize("hasRole('ADMIN')")
	public void update(@RequestBody User userUpdated) {
		userService.update(userUpdated.getFirstName(), userUpdated.getLastName(),userUpdated.getEmail(), userUpdated.getRole(), userUpdated.getPhoneNumber());
	//	return user.toString();
	}
	
	@DeleteMapping(value="/deleteAll")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteAll() {
	 userService.deleteAll();
		return "Deleted all records !!!";
	}
	
	@DeleteMapping(value="/deleteByEmail")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@RequestParam String email) {
		userService.delete(email);
	}
	
}
