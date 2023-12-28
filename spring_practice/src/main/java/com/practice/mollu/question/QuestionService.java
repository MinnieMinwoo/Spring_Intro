package com.practice.mollu.question;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.practice.mollu.DataNotFoundException;


@RequiredArgsConstructor
@Service
public class QuestionService {
  private final QuestionRepository questionRepository;

  public List<Question> getList() {
    return this.questionRepository.findAll();
  }

  public Question getQuestion(Integer id) {
    Optional<Question> question = this.questionRepository.findById(id);
    if(question.isPresent()) {
      return  question.get();
    } else {
      throw new DataNotFoundException("question not found");
    }
  }
}
