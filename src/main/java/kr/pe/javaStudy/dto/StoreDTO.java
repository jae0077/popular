package kr.pe.javaStudy.dto;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

public class StoreDTO {

	@Data
	public static class Create {
		private Long userIdx;
		private String storeName;
		private String tel;
		private String address;

		@DateTimeFormat(pattern = "yyyy-MM-dd ")
		private LocalTime openTime;

		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalTime closeTime;
		private String storeImage;
	}

	@Data
	public static class Update {
		private Long storeIdx;
		private String storeName;
		private String tel;
		private String address;
		@DateTimeFormat(pattern = "yyyy-MM-dd ")
		private LocalTime openTime;

		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalTime closeTime;
	}

	@Data
	public static class Delete {
		private Long storeIdx;
	}

	@Data
	public static class Get {
		private Long storeIdx;
		private Long userIdx;
		private String storeName;
		private String tel;
		private String address;

		@DateTimeFormat(pattern = "yyyy-MM-dd ")
		private String openTime;

		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private String closeTime;
		private String storeImage;
	}
}
