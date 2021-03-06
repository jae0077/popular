package kr.pe.javaStudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import kr.pe.javaStudy.domain.Users;
import kr.pe.javaStudy.dto.UsersDTO;
import kr.pe.javaStudy.exception.Exception;
import kr.pe.javaStudy.exception.Exception.ArgumentNullException;
import kr.pe.javaStudy.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	
	public Users findUserById(String UserId) throws NullPointerException{
		Users user = usersRepository.findUsersById(UserId);
		return user;
	}

	public Long saveUser(Users users) throws ArgumentNullException{
		Users save = null;
		if(users == null) {
			throw new Exception.ArgumentNullException("User can't be null");
			
		}
		save = usersRepository.save(users);
		return save.getUserIdx();
	}


	public void deleteUser(UsersDTO.Delete dto) throws NotFoundException {
		Users user = findOne(dto.getUserIdx());
	usersRepository.deleteById(user.getUserIdx());
	}
	
	public Users findOne(Long userIdx) throws NotFoundException {
		Users user = null;
		try {
			user = usersRepository.findById(userIdx).orElseThrow(() -> new Exception.NotFoundException("Student with idx: " + userIdx + " is not valid"));
		} catch (kr.pe.javaStudy.exception.Exception.NotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}


	public void updateUser(UsersDTO.Update dto) throws NotFoundException {
		Users user = findOne(dto.getUserIdx());
		
		user.setUserPhone(dto.getUserPhone());
		user.setPw(dto.getPw());
		
		usersRepository.save(user);
	}
	
	public List<Users> findAll(){
		return usersRepository.findAll();
	}
	
}
