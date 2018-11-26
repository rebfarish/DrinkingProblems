package com.enenby.drinkingproblems.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * The Answer Entity. Its primary key is the answer id and its foreign key is the question id.
 */
@Entity(
    foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "question_id",
        childColumns = "question_id", onDelete = ForeignKey.CASCADE)
)
public class Answer {

  @ColumnInfo(name = "answer_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "question_id", index = true)
  private long questionId;

  private String text;

  private boolean correct;

  /**
   * Gets answer id.
   *
   * @return answer id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets answer id.
   *
   * @param id the answer id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets question id.
   *
   * @return the question id
   */
  public long getQuestionId() {
    return questionId;
  }

  /**
   * Sets question id.
   *
   * @param questionId the question id
   */
  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  /**
   * Gets answer text.
   *
   * @return the answer text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets answer text.
   *
   * @param text the answer text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Is correct boolean.
   *
   * @return the boolean correct
   */
  public boolean isCorrect() {
    return correct;
  }

  /**
   * Sets correct answer.
   *
   * @param correct the correct answer
   */
  public void setCorrect(boolean correct) {
    this.correct = correct;
  }
}


