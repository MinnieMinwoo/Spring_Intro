package com.practice.mollu.question;

import com.practice.mollu.answer.Answer;
import com.practice.mollu.user.SiteUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.practice.mollu.DataNotFoundException;


@RequiredArgsConstructor
@Service
public class QuestionService {
  private final QuestionRepository questionRepository;

  private Specification<Question> search(String keyword) {
    return new Specification<Question>() {
      private static final long serialVersionUID = 1L;
      @Override
      public Predicate toPredicate(Root<Question> question, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Join<Question, SiteUser> questionUser = question.join("author", JoinType.LEFT);
        Join<Question, Answer> answer = question.join("answerList", JoinType.LEFT);
        Join<Question, SiteUser> answerUser = answer.join("author", JoinType.LEFT);

        return criteriaBuilder.or(criteriaBuilder.like(question.get("subject"), "%" + keyword + "%"),
            criteriaBuilder.like(question.get("content"), "%" + keyword + "%"),
            criteriaBuilder.like(questionUser.get("username"), "%" + keyword + "%"),
            criteriaBuilder.like(answer.get("content"), "%" + keyword + "%"),
            criteriaBuilder.like(answerUser.get("username"), "%" + keyword + "%"));
      }
    };
  }

  public Page<Question> getList(int page, String keyword) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("createDate"));

    Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
    Specification<Question> spec = search(keyword);
    return this.questionRepository.findAll(spec, pageable);
  }

  public Question getQuestion(Integer id) {
    Optional<Question> question = this.questionRepository.findById(id);
    if(question.isPresent()) {
      return  question.get();
    } else {
      throw new DataNotFoundException("question not found");
    }
  }

  public void create(String subject, String content, SiteUser user) {
    Question question = new Question();
    question.setSubject(subject);
    question.setContent(content);
    question.setCreateDate(LocalDateTime.now());
    question.setAuthor(user);

    this.questionRepository.save(question);
  }

  public void modify(Question question, String subject, String content) {
    question.setSubject(subject);
    question.setContent(content);
    question.setModifyDate(LocalDateTime.now());
    this.questionRepository.save(question);
  }

  public void delete(Question question) {
    this.questionRepository.delete(question);
  }

  public void vote(Question question, SiteUser siteUser) {
    question.getVoter().add(siteUser);
    this.questionRepository.save(question);
  }
}
