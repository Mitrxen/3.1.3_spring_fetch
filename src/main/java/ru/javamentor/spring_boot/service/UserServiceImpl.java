package ru.javamentor.spring_boot.service;

import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.javamentor.spring_boot.model.User;
import ru.javamentor.spring_boot.repository.RoleRepository;
import ru.javamentor.spring_boot.repository.UserRepository;

@Component
@Transactional
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}



	@Override
	public User findUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findAllByEmail(email).get(0);
	}

	@Override
	public void saveUser(User user, SortedSet<String> roles) {
		for(String role : roles) {
			user.addRoleToUser(roleRepository.findAllByName(role).get(0));
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}



	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

}
