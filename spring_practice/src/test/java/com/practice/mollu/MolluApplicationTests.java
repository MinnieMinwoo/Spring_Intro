package com.practice.mollu;

import com.practice.mollu.question.Question;
import com.practice.mollu.question.QuestionService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MolluApplicationTests {
	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {
		for(int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터 [%03d]", i);
			String content = "몰루";
			this.questionService.create(subject, content, null);
		}

	}

}
