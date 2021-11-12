package kr.pe.javaStudy.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@SequenceGenerator(name="user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name="user_idx")
	private Long userIdx;
	
	@Column(name="user_name")
	@NonNull
	private String userName;
	
	@Column(name="user_phone")
	@NonNull
	private String userPhone;
	
	@NonNull
	private String id;
	
	@NonNull
	private String pw;
	
	@NonNull
	private String type;
	
//	issue 기록  (fetch =  FetchType.eager로 처리)
	@OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Store> storeList;
	
	@OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Review> reviewList;

}
