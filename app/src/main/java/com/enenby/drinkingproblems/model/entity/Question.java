package com.enenby.drinkingproblems.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Question {

  public static final int MULTI_CHOICE = 0;
  public static final int MULTI_ANS = 1;
  public static final int TRUE_FALSE = 2;

@ColumnInfo(name = "question_id")
@PrimaryKey(autoGenerate = true)
private long id;

private String text;

private boolean randomAnswer;

private int type;

public Question() {

}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isRandomAnswer() {
    return randomAnswer;
  }

  public void setRandomAnswer(boolean randomAnswer) {
    this.randomAnswer = randomAnswer;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}

