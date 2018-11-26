package com.enenby.drinkingproblems.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.enenby.drinkingproblems.model.entity.Answer;
import java.util.List;

/**
 * The interface Answer dao.
 */
@Dao
public interface AnswerDao {

  /**
   * Insert list.
   *
   * @param answers
   * @return the list of answers
   */
  @Insert (onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(Answer... answers);

  /**
   * Select list.
   *
   * @param questionId
   * @return the list of question ids
   */
  @Query("SELECT * FROM Answer WHERE question_id = :questionId ORDER BY answer_id")
  List<Answer> select(long questionId);

}
