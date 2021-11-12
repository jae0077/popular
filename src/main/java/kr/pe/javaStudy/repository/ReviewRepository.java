package kr.pe.javaStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.javaStudy.domain.Review;
import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.domain.Users;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Review findByUserIdxAndStoreIdx(Users user, Store store);

}
