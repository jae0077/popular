package kr.pe.javaStudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.pe.javaStudy.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

	List<Menu> findMenuByMenuNameContaining(String menuName);

	@Query("select m from Menu m where m.price <= :price")
	List<Menu> findMenuListByPrice(Integer price);

}
