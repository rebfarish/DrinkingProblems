package com.enenby.drinkingproblems.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.entity.Question;
import java.util.List;

/**
 * The type Question and answers.
 */
public class QuestionAndAnswers {

  @Embedded
  private Question question;

  @Relation(parentColumn = "question_id", entityColumn = "question_id")
  private List<Answer> answers;

  /**
   * Gets question.
   *
   * @return the question
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * Sets question.
   *
   * @param question the question
   */
  public void setQuestion(Question question) {
    this.question = question;
  }

  /**
   * Gets answers.
   *
   * @return the answers
   */
  public List<Answer> getAnswers() {
    return answers;
  }

  /**
   * Sets answers.
   *
   * @param answers the answers
   */
  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }


}
