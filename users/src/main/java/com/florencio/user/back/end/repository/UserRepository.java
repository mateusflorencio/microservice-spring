package com.florencio.user.back.end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florencio.user.back.end.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByCpf(String cpf);
	
	List<User> queryNomeLike(String name);

}
