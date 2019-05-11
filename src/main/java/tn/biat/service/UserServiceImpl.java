package tn.biat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.biat.model.User;
import tn.biat.repository.UserRepository;
import tn.biat.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;

	@Override
	public User save(User user) {
		String password = PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		user.setCreatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	} 
	
	@Override
	public User update(String firstName, String lastName,String email, String role, String phoneNumber) {
		System.out.println("teeeessst" + email);
		User user = userRepository.findByEmailIgnoreCase(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(role);
		user.setPhoneNumber(phoneNumber);
		return userRepository.save(user);
	}
	
	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@Override
	public void delete(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		userRepository.delete(user);
	}
}
