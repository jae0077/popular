package kr.pe.javaStudy.dto;

import lombok.Data;

@Data
public class RTestDTO {

	@Data
	public static class Create {
		private String reviewContent;
		private String reviewImage;
	}

	@Data
	public static class Update {
		private Long idx;
		private String reviewContent;
		private String reviewImage;
	}

	@Data
	public static class Delete {
		private Long idx;
	}

	@Data
	public static class Get {
		private Long idx;
	}

}
