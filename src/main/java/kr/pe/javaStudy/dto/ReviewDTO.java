package kr.pe.javaStudy.dto;

import lombok.Data;

public class ReviewDTO {

	@Data
	public static class Create {
//		private Long userIdx;
		private Long storeIdx;
		private String reviewContent;
		private String reviewImage;
	}

	@Data
	public static class Update {
		private Long reviewIdx;
//		private Long userIdx;
//		private Long storeIdx;
		private String reviewContent;
		private String reviewImage;
	}

	@Data
	public static class Delete {
		private Long reviewIdx;
//		private Long userIdx;
//		private Long storeIdx;
	}

	@Data
	public static class Get {
		private Long reviewIdx;
		private Long userIdx;
		private Long storeIdx;
	}

}
