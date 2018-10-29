package com.enenby.drinkingproblems.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.enenby.drinkingproblems.model.entity.Answer;
import java.util.List;

@Dao
public interface AnswerDao {

  @Insert (onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<Answer> answers);

  @Query("SELECT * FROM Answer WHERE question_id = :questionId ORDER BY answer_id")
  List<Answer> select(long questionId);

  @Delete
  int delete(List<Answer> answer);

}
