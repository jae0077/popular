package kr.pe.javaStudy.dto;

import lombok.Data;

public class MenuDTO {
	
	@Data
	public static class Create{
		private Long storeIdx;
		private String menuName;
		private Integer price;
		private String menuImage;
	}
	
	@Data
	public static class Update{
		private Long menuIdx;
		private String menuName;
		private Integer price;
	}
	
	@Data
	public static class Delete{
		private Long menuIdx;
	}
	
	@Data
	public static class Get{
		private Long menuIdx;
		private Long storeIdx;
		private String menuName;
		private Integer price;
		private String menuImage;
	}
}
