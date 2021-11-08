package kr.pe.javaStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.javaStudy.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Users findUsersById(String id);
}
	