package kr.pe.javaStudy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.javaStudy.domain.RTest;
import kr.pe.javaStudy.dto.RTestDTO;
import kr.pe.javaStudy.dto.ResponseDTO;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.service.RTestService;

@RestController
public class RTestController {

	@Autowired
	private RTestService rTestService;

	// 후기 저장
	@RequestMapping(value = "/rtest", method = RequestMethod.POST)
	public ResponseDTO.Create saveRTest(HttpServletRequest request, @RequestBody RTestDTO.Create dto) {
		System.out.println("--- 후기 저장 시도 ---");

		boolean result = false;
		Long saveRTest = null;
//		LocalDateTime now = LocalDateTime.now();

		try {
			saveRTest = rTestService.saveRTest(new RTest(dto.getReviewContent(), dto.getReviewImage()));
			result = true;
		} catch (ArgumentNullException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseDTO.Create(saveRTest, result);
	}

	// 후기 수정
	@PutMapping("/rtest")
	public ResponseDTO.Update updateReview(HttpServletRequest request, @RequestBody RTestDTO.Update dto) {
		System.out.println("--- 후기 수정 시도 ---");

		boolean result = false;

		try {
			rTestService.updateRTest(dto);
			result = true;
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseDTO.Update(result);
	}

	// 후기 삭제
	@DeleteMapping("/rtest")
	public ResponseDTO.Delete deleteRTest(HttpServletRequest request, RTestDTO.Delete dto) {
		System.out.println("--- 후기 삭제 시도 ---");

		boolean result = false;

		try {
			rTestService.deleteRTest(dto);
			result = true;
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseDTO.Delete(result);
	}

	// 후기 리스트 전체 검색
	@GetMapping("/rtestall")
	public ResponseDTO.RTestListResponse findAll() {
		System.out.println("-- 후기 리스트 전체 검색 시도 --");

		List<RTest> rTestList = rTestService.findAll();

		return new ResponseDTO.RTestListResponse(true, rTestList);
	}

	// 특정 유저가 작성한 후기 리스트 검색

	// 특정 음식점 후기 리스트 검색

}
