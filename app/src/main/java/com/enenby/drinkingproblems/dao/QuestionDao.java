package com.enenby.drinkingproblems.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.entity.Question;
import java.util.List;

@Dao
public interface QuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert (Question question);

  @Query("SELECT * FROM Question ORDER BY type")
  List<Question> select();

  @Delete
  int delete(Question question);

  @Query("DELETE FROM Question")
  int nuke();
}
