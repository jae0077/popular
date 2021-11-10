package kr.pe.javaStudy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name="menu_seq", sequenceName = "menu_seq", initialValue = 1, allocationSize = 1)
public class Menu {
	
	
	@Id
	@Column(name="menu_idx")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
	private Long menuIdx;
	
	
	@JsonManagedReference
	@ManyToOne
	@NonNull
	@JoinColumn(name="store_idx")
	private Store storeIdx;
	
	@NonNull
	@Column(name="menu_name")
	private String menuName;
	
	@NonNull
	private Integer price;
	
	@NonNull
	@Column(name="menu_image")
	private String menuImage;
	
	@NonNull
	@Column(name="menu_contents")
	private String menuContents;
	
}
