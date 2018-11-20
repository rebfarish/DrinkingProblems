package com.enenby.drinkingproblems.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * The type Question.
 */
@Entity
public class Question {

  /**
   * The constant MULTI_CHOICE.
   */
  public static final int MULTI_CHOICE = 0;
  /**
   * The constant MULTI_ANS.
   */
  public static final int MULTI_ANS = 1;
  /**
   * The constant TRUE_FALSE.
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
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets text.
   *
   * @param text the text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Is random answer boolean.
   *
   * @return the boolean
   */
  public boolean isRandomAnswer() {
    return randomAnswer;
  }

  /**
   * Sets random answer.
   *
   * @param randomAnswer the random answer
   */
  public void setRandomAnswer(boolean randomAnswer) {
    this.randomAnswer = randomAnswer;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public int getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(int type) {
    this.type = type;
  }
}

