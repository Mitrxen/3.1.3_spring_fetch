package ru.javamentor.spring_boot.controller;


import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.javamentor.spring_boot.model.User;
import ru.javamentor.spring_boot.service.UserService;

@RestController
public class UserRestController {
	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
    
	

	@PostMapping(value = "/admin/users")
	public User addNewUser(@ModelAttribute User user, HttpServletRequest request) {

		SortedSet<String> roles = new TreeSet<String>();
		
		switch (request.getParameter("add_input_userRoles")) {
			case "ADMIN": roles.add("ROLE_ADMIN");
			case "USER": roles.add("ROLE_USER");
			default: break;
		}
		
		userService.saveUser(user, roles);
		return user;
	}
	
	
	
	
	@GetMapping(value = "/admin/users/{id}")
	public User getUser(@PathVariable("id") int id) {
		return userService.findUserById(id);
	}
	
	
	@DeleteMapping("/admin/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUserById(id);
	}
	
	
	@PatchMapping("/admin/users")
	public User editUser(@ModelAttribute User user, HttpServletRequest request) {
		SortedSet<String> roles = new TreeSet<String>();
		if(request.getParameter("edit_input_userRoles") != null) {
			switch (request.getParameter("edit_input_userRoles")) {
				case "ADMIN": roles.add("ROLE_ADMIN");
				default: roles.add("ROLE_USER");
			}
		}else {
			roles.add("ROLE_USER");
		}
		userService.saveUser(user, roles);
		return user;
	}
	
}
