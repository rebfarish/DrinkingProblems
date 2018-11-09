package com.enenby.drinkingproblems.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.entity.Question;
import java.util.List;

public class QuestionAndAnswers {

  @Embedded
  private Question question;

  @Relation(parentColumn = "question_id", entityColumn = "question_id")
  private List<Answer> answers;

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }


}
