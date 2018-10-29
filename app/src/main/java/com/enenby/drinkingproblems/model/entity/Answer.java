package com.enenby.drinkingproblems.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }
}


