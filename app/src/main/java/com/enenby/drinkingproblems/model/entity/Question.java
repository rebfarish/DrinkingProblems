package com.enenby.drinkingproblems.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Question {

@ColumnInfo(name = "question_id")
@PrimaryKey(autoGenerate = true)
private long id;

private String text;

private boolean randomAnswer;

private Enum type;
//TODO make converter for Enum type

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

  public Enum getType() {
    return type;
  }

  public void setType(Enum type) {
    this.type = type;
  }
}

