package kr.pe.javaStudy.dto;

import java.util.List;

import kr.pe.javaStudy.domain.Menu;
import kr.pe.javaStudy.domain.Store;
import kr.pe.javaStudy.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ResponseDTO {

	@Data
	@AllArgsConstructor
    public static class BaseResponse {
        Boolean success;
    }
	
	
	public static class Create extends BaseResponse {
		Long id;
		
		public Create(Long id, Boolean success) {
			super(success);
			this.id = id;
		}
	}
	
	
	public static class Update extends BaseResponse {
		public Update(Boolean success) {
			super(success);
		}
	}
	
	public static class Delete extends BaseResponse {
		public Delete(Boolean success) {
			super(success);
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class UsersResponse {
		boolean success;
		private Users user;
	}
	
	@Data
    @AllArgsConstructor
    public static class UserListResponse{
		boolean success;
		private List<Users> userList;
	}
	
	@Data
	@AllArgsConstructor
	public static class StoreResponse{
		boolean success;
		private Store store;
	}
	
	@Data
	@AllArgsConstructor
	public static class StoreListResponse{
		boolean success;
		private List<Store> storeList;
	}
	
	@Data
	@AllArgsConstructor
	public static class MenuListResponse{
		boolean success;
		private List<Menu> menuList;
	}
}
