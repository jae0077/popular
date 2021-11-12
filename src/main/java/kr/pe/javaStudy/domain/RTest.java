package kr.pe.javaStudy.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class RTest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
	private Long idx;

	@NonNull
	@JoinColumn(name = "review_content")
	private String reviewContent;

	@NonNull
	@CreationTimestamp
	private LocalDateTime created;

	@NonNull
	@UpdateTimestamp
	private LocalDateTime updated;

	@NonNull
	@JoinColumn(name = "review_image")
	private String reviewImage;

	public RTest(@NonNull String reviewContent, @NonNull String reviewImage) {
		this.reviewContent = reviewContent;
		this.reviewImage = reviewImage;
	}

}
