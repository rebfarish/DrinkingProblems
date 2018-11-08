package com.enenby.drinkingproblems.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.enenby.drinkingproblems.model.entity.Question;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import java.util.List;

@Dao
public interface QuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert (Question question);

  @Query("SELECT * FROM Question ORDER BY type")
  List<Question> select();


  @Query("SELECT * FROM Question ORDER BY RANDOM() LIMIT 1")
  Question selectRandomWithAnswers();

  @Transaction
  @Query("SELECT * FROM Question WHERE question_id =:questionId")
  QuestionAndAnswers selectById(Long questionId);
}
