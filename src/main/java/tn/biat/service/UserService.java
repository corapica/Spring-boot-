package tn.biat.service;

import java.util.List;

import tn.biat.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String email);

	void deleteAll();

	void delete(String email);

	User update(String firstName, String lastName,String email, String role, String phoneNumber);

}
