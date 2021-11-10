package kr.pe.javaStudy.dto;

import kr.pe.javaStudy.domain.Users;
import lombok.Data;

public class UsersDTO {

	@Data
	public static class Create {
		private String userName;
		private String userPhone;
		private String id;
		private String pw;
		private String type;
	}
	
	// 로그인(새로 만든 부분)
	@Data
	public static class Login {
		private String id;
		private String pw;
	}
	
	@Data
	public static class Update {
		private Long userIdx;
		private String userPhone;
		private String pw;
	}
	
	@Data
	public static class Delete {
		private Long userIdx;
	}
	
	@Data
	public static class Get {
		private Long userIdx;
		private String userName;
		private String userPhone;
		private String id;
		private String pw;
		private String type;

		public Get(Users entity) {
			this.userIdx = entity.getUserIdx();
			this.userName = entity.getUserName();
			this.userPhone = entity.getUserPhone();
			this.id = entity.getId();
			this.pw = entity.getPw();
			this.type = entity.getType();
		}
	}
}
