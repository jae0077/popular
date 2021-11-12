package kr.pe.javaStudy.dto;

import lombok.Data;

public class UsersDTO {

	// 로그인(새로 만든 부분)
	@Data
	public static class Login {
		private String id;
		private String pw;
	}

	@Data
	public static class Create {
		private String userName;
		private String userPhone;
		private String id;
		private String pw;
		private String type;
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

	}

}
