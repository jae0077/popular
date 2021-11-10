package kr.pe.javaStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.javaStudy.domain.Menu;
import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.dto.MenuDTO;
import kr.pe.javaStudy.dto.ResponseDTO;
import kr.pe.javaStudy.dto.ResponseDTO.MenuListResponse;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.repository.MenuRepository;
import kr.pe.javaStudy.service.MenuService;
import kr.pe.javaStudy.service.StoreService;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private StoreService storeService;

//	메뉴저장
	@PostMapping("/menu")
	public ResponseDTO.Create saveMenu(@RequestBody MenuDTO.Create dto) {
		System.out.println("메뉴 저장시도");

		boolean result = false;
		Long saveId = null;

		try {
			Store store = storeService.findOne(dto.getStoreIdx());
			
			saveId = menuService.saveMenu(new Menu(store, dto.getMenuName(), dto.getPrice(), dto.getMenuImage(), dto.getMenuContents()));
			result = true;
		} catch (NotFoundException | ArgumentNullException e) {
//			e.printStackTrace();
			
		}
		return new ResponseDTO.Create(saveId, result);
	}

	@DeleteMapping("/menu")
	public ResponseDTO.Delete deletMenu(MenuDTO.Delete dto) {
		System.out.println("가게 삭제시도");
		boolean result = false;
		try {
			menuService.deleteMenu(dto);
			result = true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Delete(result);
	}

//	가게별 메뉴조회
	@GetMapping("/menu/storeidx")
	public ResponseDTO.MenuListResponse findMyMenu(MenuDTO.Get dto) {
		System.out.println("가게별 메뉴 조회");
		boolean result = false;
		List<Menu> menuList = null;
		try {
			Store store = storeService.findOne(dto.getStoreIdx());
			menuList = store.getMenuList();
			result = true;
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseDTO.MenuListResponse(result, menuList);
	}

//	이름으로 메뉴검색
	@GetMapping("/menu/namecontaining")
	public ResponseDTO.MenuListResponse findMenuByName(MenuDTO.Get dto){
		System.out.println("메뉴 이름으로 검색");
		List<Menu> menuList = menuService.findAllByNameContaining(dto);
		
		return new ResponseDTO.MenuListResponse(true, menuList);
	}
	
//	메뉴 전체조회
	@GetMapping("/menuall")
	public ResponseDTO.MenuListResponse findAll(){
		System.out.println("전체메뉴 조회");
		List<Menu> menuList = menuService.findAll();
		if(menuList.size()==0) {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		return new ResponseDTO.MenuListResponse(true, menuList);
	}
	
//	메뉴 수정
	@PutMapping("/menu")
	public ResponseDTO.Update updateMenu(@RequestBody MenuDTO.Update dto){
		System.out.println("메뉴 수정시도!");
		boolean result = false;
		try {
			menuService.updateMenu(dto);
			result = true;
		} catch(Exception e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Update(result);
	}
	
//	가격으로 메뉴조회
	@GetMapping("/menu/price")
	public ResponseDTO.MenuListResponse findAllByMenuPrice(MenuDTO.Get dto){
		System.out.println("가격으로 메뉴조회");
		
		List<Menu> menuList = menuService.findAllByPrice(dto);
		
		return new ResponseDTO.MenuListResponse(true, menuList);
	}
	
}
