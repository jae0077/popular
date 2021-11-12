package kr.pe.javaStudy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.javaStudy.domain.Review;
import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.domain.Users;
import kr.pe.javaStudy.dto.ResponseDTO;
import kr.pe.javaStudy.dto.ReviewDTO;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.service.ReviewService;
import kr.pe.javaStudy.service.StoreService;
import kr.pe.javaStudy.service.UsersService;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UsersService userService;
	@Autowired
	private StoreService storeService;

	// 후기 저장
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ResponseDTO.Create saveReview(HttpServletRequest request, @RequestBody ReviewDTO.Create dto) {
		System.out.println("--- 후기 저장 시도 ---");

		boolean result = false;
		Long saveReview = null;

		if (request.getSession().getAttribute("user") != null) {
			Object object = request.getSession().getAttribute("user");
			Users entity = (Users) object;

			try {
				Users user = userService.findOne(entity.getUserIdx());
				Store store = storeService.findOne(dto.getStoreIdx());

				if (reviewService.isNotAlreadyReview(user, store)) {

					try {
						saveReview = reviewService
								.saveReview(new Review(user, store, dto.getReviewContent(), dto.getReviewImage()));
						result = true;
					} catch (ArgumentNullException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("해당 가게에 대한 리뷰가 이미 존재합니다. 수정을 하시거나, 삭제 후 다시 작성해주세요.");
				}

			} catch (NotFoundException | Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("로그인 정보가 없습니다. 로그인이 필요한 기능입니다.");
		}

		return new ResponseDTO.Create(saveReview, result);
	}

	// 후기 수정
	@PutMapping("/review")
	public ResponseDTO.Update updateReview(HttpServletRequest request, @RequestBody ReviewDTO.Update dto) {
		System.out.println("--- 후기 수정 시도 ---");

		boolean result = false;

		if (request.getSession().getAttribute("user") != null) {
			Object object = request.getSession().getAttribute("user");
			Users entity = (Users) object;

			try {
				Users user = userService.findOne(entity.getUserIdx());
				List<Review> reviewList = user.getReviewList();

				for (Review r : reviewList) {

					if (r.getReviewIdx() == dto.getReviewIdx()) {
						try {
							reviewService.updateReview(dto);
							result = true;
						} catch (NotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
				}

			} catch (javassist.NotFoundException e1) {
				e1.printStackTrace();
			}

		} else {
			System.out.println("로그인 정보가 없습니다. 로그인이 필요한 기능입니다.");
		}

		return new ResponseDTO.Update(result);
	}

	// 후기 삭제
	@DeleteMapping("/review")
	public ResponseDTO.Delete deleteReview(HttpServletRequest request, @RequestBody ReviewDTO.Delete dto) {
		System.out.println("--- 후기 삭제 시도 ---");

		boolean result = false;

		if (request.getSession().getAttribute("user") != null) {
			Object object = request.getSession().getAttribute("user");
			Users user = (Users) object;

			try {
				reviewService.deleteReview(user, dto);
				result = true;
			} catch (NotFoundException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("로그인 정보가 없습니다. 로그인이 필요한 기능입니다.");
		}

		return new ResponseDTO.Delete(result);
	}

	// 후기 리스트 전체 검색
	@GetMapping("/reviewall")
	public ResponseDTO.ReviewListResponse findAll() {
		System.out.println("-- 후기 리스트 전체 검색 시도 --");

		List<Review> reviewList = reviewService.findAll();

		return new ResponseDTO.ReviewListResponse(true, reviewList);
	}

	// 특정 유저가 작성한 후기 리스트 검색
	@GetMapping("/review/user/{idx}")
	public ResponseDTO.ReviewListResponse findAllByUserIdx(@PathVariable Long idx) {
		System.out.println("--- 특정 유저가 작성한 후기 리스트 검색 시도 ---");

		boolean result = false;
		List<Review> reviewList = null;

		try {
			Users user = userService.findOne(idx);
			reviewList = user.getReviewList();
			result = true;
		} catch (javassist.NotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseDTO.ReviewListResponse(result, reviewList);
	}

	// 특정 음식점 후기 리스트 검색
	@GetMapping("/review/store/{idx}")
	public ResponseDTO.ReviewListResponse findAllByStoreIdx(@PathVariable Long idx) {
		System.out.println("--- 특정 가게에 작성된 후기 리스트 검색 시도 ---");

		boolean result = false;
		List<Review> reviewList = null;

		try {
			Store store = storeService.findOne(idx);
			reviewList = store.getReviewList();
			result = true;
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return new ResponseDTO.ReviewListResponse(result, reviewList);
	}

}
