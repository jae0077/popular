package kr.pe.javaStudy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import kr.pe.javaStudy.domain.Users;
import kr.pe.javaStudy.dto.ResponseDTO;
import kr.pe.javaStudy.dto.UsersDTO;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.service.UsersService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UsersController {

	@Autowired
	private UsersService userservice;

	@PostMapping("/login")
	public ResponseDTO.Login loginUser(@ApiIgnore HttpSession session,@RequestBody UsersDTO.Login dto) {
		System.out.println("유저 로그인 시도");
		boolean result = false;
		UsersDTO.Get user = userservice.login(dto.getId());

		if (user != null) {
//			System.out.println("111");
			// 로그인 성공시
			if (user.getPw().equals(dto.getPw())) {
				session.setAttribute("loginUser", user);
				result = true;

				// 로그인 실패시
			} else {
				return null;
			}
		}
		return new ResponseDTO.Login(result);
	}

//	유저저장
	@PostMapping("/users")
	public ResponseDTO.Create saveUser(@RequestBody UsersDTO.Create dto) {
		System.out.println("유저저장시도");
		boolean result = false;
		Long saveId = null;

		if (userservice.findUserById(dto.getId()) == null) {
			try {
				saveId = userservice.saveUser(
						new Users(dto.getUserName(), dto.getUserPhone(), dto.getId(), dto.getPw(), dto.getType()));
				result = true;
			} catch (ArgumentNullException e) {
				// e.printStackTrace();
			}
		} else {
			System.out.println("이미 존재하는 회원입니다.");
		}
		return new ResponseDTO.Create(saveId, result);
	}

	// 유저 삭제
	@DeleteMapping("/users")
	public ResponseDTO.Delete deleteUser(UsersDTO.Delete dto) {
		System.out.println("학생 삭제 시도");
		boolean result = false;

		try {
			userservice.deleteUser(dto);
			result = true;
		} catch (NotFoundException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Delete(result);
	}

	// 유저 수정
	@PutMapping("/users")
	public ResponseDTO.Update updateUser(@RequestBody UsersDTO.Update dto) {

		System.out.println("유저 수정 시도");
		boolean result = false;
		try {
			userservice.updateUser(dto);
			result = true;
		} catch (NotFoundException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.Update(result);
	}

	// 유저 단일 조회
	@GetMapping("/users")
	public ResponseDTO.UsersResponse findOne(UsersDTO.Get dto) {
		System.out.println("유저 단일 검색 시도");
		boolean result = false;
		Users user = null;
		try {
			user = userservice.findOne(dto.getUserIdx());
			result = true;
		} catch (NotFoundException e) {
//			e.printStackTrace();
		}
		return new ResponseDTO.UsersResponse(result, user);
	}

	// 유저 전체조회
	@GetMapping("/userall")
	public ResponseDTO.UserListResponse findAll() {
		System.out.println("전체유저 검색시도");
		List<Users> userList = userservice.findAll();
		if (userList == null) {
			System.out.println("아무런 유저가 없습니다");
		}
		return new ResponseDTO.UserListResponse(true, userList);
	}
}
