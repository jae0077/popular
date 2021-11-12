package kr.pe.javaStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.domain.Users;
import kr.pe.javaStudy.dto.ResponseDTO;
import kr.pe.javaStudy.dto.StoreDTO;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.service.StoreService;
import kr.pe.javaStudy.service.UsersService;

@RestController
public class StoreController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private UsersService userService;

//	가게 이름으로 조회
	@GetMapping("/store/namecontaining")
	public ResponseDTO.StoreListResponse findAllByName(StoreDTO.Get dto) throws NotFoundException {
		System.out.println("가게 이름으로 조회");
		List<Store> storeList = storeService.findAllByNameContaining(dto);

		return new ResponseDTO.StoreListResponse(true, storeList);
	}

//	내 가게 조회
	@GetMapping("/store/useridx")
	public ResponseDTO.StoreListResponse findMyStore(StoreDTO.Get dto) {
		System.out.println("내 가게 조회");

		boolean result = false;
		List<Store> storeList = null;
		try {
			Users user = userService.findOne(dto.getUserIdx());
			storeList = user.getStoreList();
			result = true;
		} catch (javassist.NotFoundException e) {
			e.printStackTrace();
		}
		return new ResponseDTO.StoreListResponse(result, storeList);
	}

//	가게 단일 조회
	@GetMapping("/store")
	public ResponseDTO.StoreResponse findOne(StoreDTO.Get dto) {
		System.out.println("가게 단일 검색 시도");
		boolean result = false;
		Store store = null;
		try {
			store = storeService.findOne(dto.getStoreIdx());
			result = true;
		} catch (NotFoundException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.StoreResponse(result, store);
	}

//	가게 전체조회
	@GetMapping("/storeall")
	public ResponseDTO.StoreListResponse findAll() {
		System.out.println("가게 전체 검색시도");
		List<Store> storeList = storeService.findAll();

		if (storeList.size() == 0) {
			System.out.println("가게 정보가 존재하지 않습니다.");
		}
		return new ResponseDTO.StoreListResponse(true, storeList);
	}

//	가게 삭제
	@DeleteMapping("/store")
	public ResponseDTO.Delete deleteStore(StoreDTO.Delete dto) {
		System.out.println("가게 삭제 시도");
		boolean result = false;
		try {
			storeService.deleteStore(dto);
			result = true;
		} catch (NotFoundException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Delete(result);
	}

//	가게 저장
	@PostMapping("/store")
	public ResponseDTO.Create saveStore(@RequestBody StoreDTO.Create dto) {
		System.out.println("가게 저장 시도");

		boolean result = false;
		Long saveId = null;

		try {
			Users user = userService.findOne(dto.getUserIdx());

			saveId = storeService.saveStore(new Store(user, dto.getStoreName(), dto.getTel(), dto.getAddress(),
					dto.getOpenTime(), dto.getCloseTime(), dto.getStoreImage()));
			result = true;
		} catch (javassist.NotFoundException | ArgumentNullException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Create(saveId, result);
	}

//	가게 수정
	@PutMapping("/store")
	public ResponseDTO.Update updateStore(@RequestBody StoreDTO.Update dto) {
		System.out.println("가게 수정 시도");
		boolean result = false;
		try {
			storeService.updateStore(dto);
			result = true;
		} catch (NotFoundException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Update(result);
	}
}
