package kr.pe.javaStudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.javaStudy.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{


	List<Store> findStoreByStoreNameContaining(String storeName);

}
