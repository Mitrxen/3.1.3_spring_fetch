package ru.javamentor.spring_boot.service;

import java.util.List;
import java.util.SortedSet;

import ru.javamentor.spring_boot.model.User;

public interface UserService {
	public List<User> findAllUsers();
	
	public User findUserById(int id);
	
	public User findUserByEmail(String email);
	
	public void saveUser(User user, SortedSet<String> roles);
	
	public void deleteUserById(int id);
	
}
