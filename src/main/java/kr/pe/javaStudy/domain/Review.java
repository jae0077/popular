package kr.pe.javaStudy.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@SequenceGenerator(name = "review_seq", sequenceName = "review_seq", initialValue = 1, allocationSize = 1)
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
	@Column(name = "review_idx")
	private Long reviewIdx;

	@NonNull
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "user_idx")
	private Users userIdx;

	@NonNull
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "store_idx")
	private Store storeIdx;

	@NonNull

	@Column(name = "review_content")
	private String reviewContent;

	@NonNull
	@CreationTimestamp
	private LocalDateTime created;

	@NonNull
	@UpdateTimestamp
	private LocalDateTime updated;

	@NonNull
	@Column(name = "review_image")
	private String reviewImage;

	public Review(@NonNull Users userIdx, @NonNull Store storeIdx, @NonNull String reviewContent,
			@NonNull String reviewImage) {
		this.userIdx = userIdx;
		this.storeIdx = storeIdx;
		this.reviewContent = reviewContent;
		this.reviewImage = reviewImage;
	}

}
