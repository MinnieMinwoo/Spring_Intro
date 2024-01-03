package com.practice.mollu.answer;


import com.practice.mollu.question.Question;
import com.practice.mollu.user.SiteUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String Content;

  private LocalDateTime createDate;

  @ManyToOne
  private Question question;

  @ManyToOne
  private SiteUser author;

  private LocalDateTime modifyDate;
}
