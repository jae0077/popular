package kr.pe.javaStudy.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.javaStudy.domain.Menu;
import kr.pe.javaStudy.dto.MenuDTO;
import kr.pe.javaStudy.exception.Exception;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	public List<Menu> findAllByNameContaining(MenuDTO.Get dto){
		return menuRepository.findMenuByMenuNameContaining(dto.getMenuName());
	}
	
	public Long saveMenu(Menu menu) throws ArgumentNullException {
		Menu save = null;
		if(menu == null) {
			throw new Exception.ArgumentNullException("정보를 확인해주세요");
		}
		save = menuRepository.save(menu);
		return save.getMenuIdx();
	}

	public void deleteMenu(MenuDTO.Delete dto) {
		Menu menu = findOne(dto.getMenuIdx());
		menuRepository.deleteById(menu.getMenuIdx());
	}

	public Menu findOne(Long menuIdx) {
		Menu menu = menuRepository.getById(menuIdx);
		return menu;
	}

	public List<Menu> findAll() {
		return menuRepository.findAll();
	}
	

}
