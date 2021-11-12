package kr.pe.javaStudy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

	public ResponseDTO.StoreListResponse findMyStore(HttpServletRequest request) throws javassist.NotFoundException {
		

		System.out.println("내 가게 조회");

		boolean result = false;
		List<Store> storeList = null;
		
		if (request.getSession().getAttribute("loginUser") != null) {
			Object object = request.getSession().getAttribute("loginUser");
			Users entity = (Users) object;
			Users user = userService.findOne(entity.getUserIdx());
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

	public ResponseDTO.Delete deleteStore(HttpServletRequest request, @RequestBody StoreDTO.Delete dto) throws javassist.NotFoundException {

		System.out.println("가게 삭제 시도");
		
		boolean result = false;


		if (request.getSession().getAttribute("loginUser") != null) {
			Object object = request.getSession().getAttribute("loginUser");
			Users entity = (Users) object;
			try {
				Users user = userService.findOne(entity.getUserIdx());
				Store store = storeService.findOne(dto.getStoreIdx());
				System.out.println(store);

				if (store.getUserIdx().getUserIdx() == user.getUserIdx()) {
					storeService.deleteStore(dto);
					result = true;
				} else {
					System.out.println("본인의 만 삭제할 수 있습니다.");
				}
			} catch (NotFoundException e) {
//				e.printStackTrace();
			}

		}
		return new ResponseDTO.Delete(result);
	}

//	가게 저장
	@PostMapping("/store")

	public ResponseDTO.Create saveStore(HttpServletRequest request, @RequestBody StoreDTO.Create dto) throws javassist.NotFoundException {
		

		System.out.println("가게 저장 시도");

		boolean result = false;
		Long saveId = null;

		try {

			if (request.getSession().getAttribute("loginUser") != null) {

				Object object = request.getSession().getAttribute("loginUser");
				Users entity = (Users) object;
				Users user = userService.findOne(entity.getUserIdx());

				if (user.getType().equals("manager")) {
					saveId = storeService.saveStore(new Store(user, dto.getStoreName(), dto.getTel(), dto.getAddress(),
							dto.getOpenTime(), dto.getCloseTime(), dto.getStoreImage()));
					result = true;
					System.out.println(dto.getStoreName() + "저장 성공!");
				} else {
					System.out.println("일반회원은 가게를 추가할 수 없습니다.");
				}
			}
		} catch (ArgumentNullException e) {

//			e.printStackTrace();
		}
		return new ResponseDTO.Create(saveId, result);
	}

//	가게 수정
	@PutMapping("/store")

	public ResponseDTO.Update updateStore(HttpServletRequest request, @RequestBody StoreDTO.Update dto) {
		

		System.out.println("가게 수정 시도");
		
		boolean result = false;

		
		if (request.getSession().getAttribute("loginUser") != null) {
			Object object = request.getSession().getAttribute("loginUser");
			Users entity = (Users) object;
			System.out.println(entity.getId());
			try {
				Users user = userService.findOne(entity.getUserIdx());
				Store store = storeService.findOne(dto.getStoreIdx());
				if (store.getUserIdx().getUserIdx() == user.getUserIdx()) {
					System.out.println("나와줘ㅠㅠㅠ");
					storeService.updateStore(store.getStoreIdx(), dto);
					result = true;
				} else {
					System.out.println("자신의 가게만 수정할 수 있습니다.");
				}
			} catch (javassist.NotFoundException | NotFoundException e) {
//				e.printStackTrace();
			}


		}
		return new ResponseDTO.Update(result);
	}
}
