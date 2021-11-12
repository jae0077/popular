package kr.pe.javaStudy.domain;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@SequenceGenerator(name = "store_seq", sequenceName = "store_seq", initialValue = 1, allocationSize = 1)
public class Store {

	@Id
	@Column(name = "store_idx")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq")
	private Long storeIdx;

	@JsonManagedReference
	@ManyToOne
	@NonNull
	@JoinColumn(name = "user_idx")
	private Users userIdx;

	@NonNull
	@Column(name = "store_name")
	private String storeName;

	@NonNull
	private String tel;

	@NonNull
	private String address;

	@NonNull
	private LocalTime openTime;

	@NonNull
	private LocalTime closeTime;

	@NonNull
	private String storeImage;

	@OneToMany(mappedBy = "storeIdx", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Review> reviewList;

	@OneToMany(mappedBy = "storeIdx", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Menu> menuList;

}
