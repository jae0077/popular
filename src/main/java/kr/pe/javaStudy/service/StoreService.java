package kr.pe.javaStudy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.dto.StoreDTO;
import kr.pe.javaStudy.dto.StoreDTO.Update;
import kr.pe.javaStudy.exception.Exception;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.exception.Exception.NotFoundException;
import kr.pe.javaStudy.repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Transactional
	public Store findOne(Long storeIdx) throws NotFoundException{
		Store store = storeRepository.findById(storeIdx).orElseThrow(() -> new Exception.NotFoundException ("Store with idx: " + storeIdx + " is not valid"));
		
		return store;
	}
//	@Transactional
	public List<Store> findAll(){

		return storeRepository.findAll();
	}

	public void deleteStore(StoreDTO.Delete dto) throws NotFoundException {
		Store store = findOne(dto.getStoreIdx());

		System.out.println(store.getStoreName());
//		storeRepository.deleteById(store.getStoreIdx());

		storeRepository.deleteById(store.getStoreIdx());
		System.out.println("deletStore2");
	}

	public List<Store> findAllByNameContaining(StoreDTO.Get dto) {
		return storeRepository.findStoreByStoreNameContaining(dto.getStoreName());
	}

	
//	@Transactional
	public Long saveStore(Store store) throws ArgumentNullException{
		Store save = null;
		System.out.println("savestore");
		if(store == null) {

			throw new Exception.ArgumentNullException("Store can't be null");
		}
		save = storeRepository.save(store);
		return save.getStoreIdx();
	}


	public void updateStore(Long storeIdx, StoreDTO.Update dto) throws NotFoundException{
		Store store = findOne(storeIdx);
		

		store.setStoreName(dto.getStoreName());
		store.setTel(dto.getTel());
		store.setAddress(dto.getAddress());
		store.setOpenTime(dto.getOpenTime());
		store.setCloseTime(dto.getCloseTime());

		storeRepository.save(store);
	}
	
	
}
