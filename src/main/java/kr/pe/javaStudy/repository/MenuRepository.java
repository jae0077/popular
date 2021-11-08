package kr.pe.javaStudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.javaStudy.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

	List<Menu> findMenuByMenuNameContaining(String menuName);

}
