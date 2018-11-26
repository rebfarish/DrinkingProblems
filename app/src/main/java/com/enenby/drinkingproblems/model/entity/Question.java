package com.enenby.drinkingproblems.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * The Question Entity. Its primary key is question id.
 */
@Entity
public class Question {

  /**
   * The constant 0 flags question as MULTI_CHOICE question.
   */
  public static final int MULTI_CHOICE = 0;
  /**
   * The constant 1 flags question as MULTI_ANS question.
   */
  public static final int MULTI_ANS = 1;
  /**
   * The constant 2 flags question as TRUE_FALSE question.
   */
  public static final int TRUE_FALSE = 2;

@ColumnInfo(name = "question_id")
@PrimaryKey(autoGenerate = true)
private long id;

private String text;

private boolean randomAnswer;

private int type;

  /**
   * Instantiates a new Question.
   */
  public Question() {

}

  /**
   * Gets question id.
   *
   * @return the question id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets question id.
   *
   * @param id the question id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets question text.
   *
   * @return the question text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets question text.
   *
   * @param text the question text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Is random answer randomizes answer order if true.
   *
   * @return the boolean true if answers are randomized.
   */
  public boolean isRandomAnswer() {
    return randomAnswer;
  }

  /**
   * Sets random answer order.
   *
   * @param randomAnswer the random answer
   */
  public void setRandomAnswer(boolean randomAnswer) {
    this.randomAnswer = randomAnswer;
  }

  /**
   * Gets question type.
   *
   * @return the question type
   */
  public int getType() {
    return type;
  }

  /**
   * Sets question type.
   *
   * @param type the question type
   */
  public void setType(int type) {
    this.type = type;
  }
}

