package kr.pe.javaStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.javaStudy.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
