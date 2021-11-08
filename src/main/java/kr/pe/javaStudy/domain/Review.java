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
@SequenceGenerator(name="review_seq", sequenceName="review_seq", initialValue=1, allocationSize=1)
public class Review {
   
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="review_seq")
   @Column(name="review_idx")
   private Long reviewIdx;
   
   @JsonManagedReference
   @ManyToOne
   @NonNull
   @JoinColumn(name="user_idx")
   private Users userIdx;
   
   @JsonManagedReference
   @ManyToOne
   @NonNull
   @JoinColumn(name="store_idx")
   private Store storeIdx;
   
   @NonNull
   @JoinColumn(name="review_content")
   private String reviewContent;
   
   @NonNull
   private LocalDateTime created;
   
   @NonNull
   private LocalDateTime updated;
   
   @NonNull
   @JoinColumn(name="review_image")
   private String reviewImage;
}
