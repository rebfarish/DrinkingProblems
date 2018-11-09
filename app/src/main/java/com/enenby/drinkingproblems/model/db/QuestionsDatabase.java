package com.enenby.drinkingproblems.model.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.enenby.drinkingproblems.model.dao.AnswerDao;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.dao.QuestionDao;

import com.enenby.drinkingproblems.model.entity.Question;


@Database(
    entities = {Answer.class, Question.class},
    version = 1,
    exportSchema = true
)

public abstract class QuestionsDatabase extends RoomDatabase {

  private static final String DB_NAME = "questions_db";
  private static QuestionsDatabase instance = null;


  public synchronized static QuestionsDatabase getInstance(Context context){
    if (instance == null){
      instance = Room.databaseBuilder(context.getApplicationContext(), QuestionsDatabase.class,
          DB_NAME).addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
}

public synchronized static void forgetInstance(Context context){
    instance = null;

}

  public abstract AnswerDao getAnswerDao();
  public abstract QuestionDao getQuestionDao();

  private static class Callback extends RoomDatabase.Callback{

    private Context context;
    public Callback(Context context){
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new PrepopulateTask(context).execute();
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  }

  private static class PrepopulateTask extends AsyncTask<Void, Void, Void>{

    private Context context;

    public PrepopulateTask(Context context){
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      QuestionsDatabase db = getInstance(context);
      QuestionDao qDao = db.getQuestionDao();
      AnswerDao aDao = db.getAnswerDao();
      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        question.setText("question 0_a");
        question.setType(Question.MULTI_CHOICE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("correct answer");
        answer1.setCorrect(true);


        answer2.setQuestionId(questionId);
        answer2.setText("wrong answer");
        answer2.setCorrect(false);

        answer3.setQuestionId(questionId);
        answer3.setText("wrong answer");
        answer3.setCorrect(false);

        aDao.insert(answer1, answer2, answer3);
      }

      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        question.setText("Question 0_b");
        question.setType(Question.MULTI_CHOICE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("correct answer");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("wrong answer");
        answer2.setCorrect(false);

        answer3.setQuestionId(questionId);
        answer3.setText("wrong answer");
        answer3.setCorrect(false);

        aDao.insert(answer1, answer2, answer3);
      }
      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        question.setText("Question 0_c");
        question.setType(Question.MULTI_CHOICE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("correct answer");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("wrong answer");
        answer2.setCorrect(false);

        answer3.setQuestionId(questionId);
        answer3.setText("wrong answer");
        answer3.setCorrect(false);

        aDao.insert(answer1, answer2, answer3);
      }

      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();

        question.setText("Question 1");
        question.setType(Question.TRUE_FALSE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("correct answer");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("wrong answer");
        answer2.setCorrect(false);

        aDao.insert(answer1, answer2);
      }

      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();
        Answer answer4 = new Answer();

        question.setText("Question 2");
        question.setType(Question.MULTI_ANS);
        question.setRandomAnswer(false);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("correct answer");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("wrong answer");
        answer2.setCorrect(false);

        answer3.setQuestionId(questionId);
        answer3.setText("wrong answer");
        answer3.setCorrect(false);

        answer4.setQuestionId(questionId);
        answer4.setText("wrong answer");
        answer4.setCorrect(false);

        aDao.insert(answer1, answer2, answer3, answer4);

      }
      forgetInstance(context);
      return null;
    }
  }
}
