package kr.pe.javaStudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.javaStudy.domain.Review;
import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.domain.Users;
import kr.pe.javaStudy.dto.ReviewDTO;
import kr.pe.javaStudy.exception.Exception;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public boolean isNotAlreadyReview(Users user, Store store) {
		boolean flag = false;
		Review review = reviewRepository.findByUserIdxAndStoreIdx(user, store);

		flag = (review == null) ? true : false;

		return flag;
	}

	public Long saveReview(Review review) throws ArgumentNullException {
		Review save = null;

		if (review == null) {
			throw new Exception.ArgumentNullException("Review can't be null");
		}
		save = reviewRepository.save(review);

		return save.getReviewIdx();
	}

	public void updateReview(ReviewDTO.Update dto) throws NotFoundException {
		Review review = findOne(dto.getReviewIdx());

		review.setReviewContent(dto.getReviewContent());
		review.setReviewImage(dto.getReviewImage());

		reviewRepository.save(review);
	}

	public void deleteReview(Users user, Store store) throws NotFoundException {
		Review review = reviewRepository.findByUserIdxAndStoreIdx(user, store);
		reviewRepository.deleteById(review.getReviewIdx());
	}

	private Review findOne(Long reviewIdx) throws NotFoundException {
		Review review = reviewRepository.findById(reviewIdx).orElseThrow(() -> new Exception.NotFoundException("Review with idx: " + reviewIdx + " is not valid"));

		return review;
	}

	public List<Review> findAll() {
		return reviewRepository.findAll();
	}

}
