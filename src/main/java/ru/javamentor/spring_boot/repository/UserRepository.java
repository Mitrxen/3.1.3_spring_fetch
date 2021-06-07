package ru.javamentor.spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.javamentor.spring_boot.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public List<User> findAllByFirstname(String firstname);
	public List<User> findAllByEmail(String email);
}
