package kr.pe.javaStudy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.javaStudy.domain.RTest;
import kr.pe.javaStudy.dto.RTestDTO;
import kr.pe.javaStudy.exception.Exception;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.repository.RTestRepository;

@Service
public class RTestService {
	
	@Autowired
	private RTestRepository rTestRepository;
	
	public Long saveRTest(RTest rTest) throws ArgumentNullException {
		RTest save = null;
		save = rTestRepository.save(rTest);			

		return save.getIdx();
	}

	public void updateRTest(RTestDTO.Update dto) throws NotFoundException {
		RTest rtest = findOne(dto.getIdx());
//		LocalDateTime date = LocalDateTime.now();
		
		rtest.setReviewContent(dto.getReviewContent());
//		rtest.setUpdated(date);
		rtest.setReviewImage(dto.getReviewImage());
		
		rTestRepository.save(rtest);
	}
	
	public void deleteRTest(RTestDTO.Delete dto) throws NotFoundException {
		RTest rtest = findOne(dto.getIdx());
		rTestRepository.deleteById(rtest.getIdx());
	}

	private RTest findOne(Long rTestIdx) throws NotFoundException {
		RTest rtest = rTestRepository.findById(rTestIdx).orElseThrow(() -> new Exception.NotFoundException("Review with idx: " + rTestIdx + " is not valid"));
		
		return rtest;
	}
	
	public List<RTest> findAll() {
		return rTestRepository.findAll();
	}

}
