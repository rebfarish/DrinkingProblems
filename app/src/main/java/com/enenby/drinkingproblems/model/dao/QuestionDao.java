package com.enenby.drinkingproblems.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.enenby.drinkingproblems.model.entity.Question;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import java.util.List;

/**
 * The interface Question dao.
 */
@Dao
public interface QuestionDao {

  /**
   * Insert long.
   *
   * @param question the question
   * @return the long
   */
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert (Question question);

  /**
   * Select list.
   *
   * @return the list
   */
  @Query("SELECT * FROM Question ORDER BY type")
  List<Question> select();


  /**
   * Select random with answers question.
   *
   * @return the question
   */
  @Query("SELECT * FROM Question ORDER BY RANDOM() LIMIT 1")
  Question selectRandomWithAnswers();

  /**
   * Select by id question and answers.
   *
   * @param questionId the question id
   * @return the question and answers
   */
  @Transaction
  @Query("SELECT * FROM Question WHERE question_id =:questionId")
  QuestionAndAnswers selectById(Long questionId);
}
