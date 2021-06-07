package ru.javamentor.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import ru.javamentor.spring_boot.service.UserService;

@Controller
public class MainController {
	
	private final UserService userService;

	public MainController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/")
    public String redirectFromRootToLoginPage() {
        return "redirect:/login";
    }
    
	@GetMapping(value = "/login")
	public String loginPage() {
		return "/login";
	}
	
    @GetMapping(value = "/admin")
    public String redirectFromAdminRootToUsersListPage() {
		return "redirect:/admin/users";
    }
    
    @GetMapping(value = "/admin/users")
	public String printAllUsers( ModelMap model) {
		model.addAttribute("users", userService.findAllUsers());
		return "/admin";
	}
	
	@GetMapping(value = "/user")
	public String userPage() {
		return "/user";
	}
	
}
