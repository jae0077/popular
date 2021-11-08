package kr.pe.javaStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.javaStudy.domain.RTest;

public interface RTestRepository extends JpaRepository<RTest, Long> {
	
}
