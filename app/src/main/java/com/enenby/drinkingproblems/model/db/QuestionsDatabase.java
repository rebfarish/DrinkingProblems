package com.enenby.drinkingproblems.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.enenby.drinkingproblems.model.dao.AnswerDao;
import com.enenby.drinkingproblems.model.dao.QuestionDao;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.entity.Question;


@Database(
    entities = {Answer.class, Question.class},
    version = 1,
    exportSchema = true
)

public abstract class QuestionsDatabase extends RoomDatabase {

  private static final String DB_NAME = "questions_db";
  private static QuestionsDatabase instance = null;


  public synchronized static QuestionsDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), QuestionsDatabase.class,
          DB_NAME)
          .build();
    }
    return instance;
  }

  public synchronized static void forgetInstance(Context context) {
    instance = null;

  }

  public abstract AnswerDao getAnswerDao();

  public abstract QuestionDao getQuestionDao();

  public static void populateQuestions(Context context) {
      QuestionsDatabase db = getInstance(context);
      QuestionDao qDao = db.getQuestionDao();
      AnswerDao aDao = db.getAnswerDao();
      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        question.setText("If $$f(x)=3x+1$$ and $$g(x)=x^2 -1$$ $$f(x)+g(x)=$$");
        question.setType(Question.MULTI_CHOICE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("$$x^2+3x$$");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("$$x^2+3x+1$$");
        answer2.setCorrect(false);

        answer3.setQuestionId(questionId);
        answer3.setText("$$x^2+4x$$");
        answer3.setCorrect(false);

        aDao.insert(answer1, answer2, answer3);
      }

//      {
//        Question question = new Question();
//        Answer answer1 = new Answer();
//        Answer answer2 = new Answer();
//        Answer answer3 = new Answer();
//
//        question.setText("What is $$\\lim_{x\\to 0}\\frac{\\sinx}{x}$$ \\?");
//        question.setType(Question.MULTI_CHOICE);
//        question.setRandomAnswer(true);
//        long questionId = qDao.insert(question);
//
//        answer1.setQuestionId(questionId);
//        answer1.setText("$$1$$");
//        answer1.setCorrect(true);
//
//        answer2.setQuestionId(questionId);
//        answer2.setText("$$0$$");
//        answer2.setCorrect(false);
//
//        answer3.setQuestionId(questionId);
//        answer3.setText("$$\\infty$$");
//        answer3.setCorrect(false);
//
//        aDao.insert(answer1, answer2, answer3);
//      }
//      {
//        Question question = new Question();
//        Answer answer1 = new Answer();
//        Answer answer2 = new Answer();
//        Answer answer3 = new Answer();
//
//        question.setText("The zeros of $$f(x)=x^3-2x^2+x$$");
//        question.setType(Question.MULTI_CHOICE);
//        question.setRandomAnswer(true);
//        long questionId = qDao.insert(question);
//
//        answer1.setQuestionId(questionId);
//        answer1.setText("$$0,-1$$");
//        answer1.setCorrect(true);
//
//        answer2.setQuestionId(questionId);
//        answer2.setText("$$0,1$$");
//        answer2.setCorrect(false);
//
//        answer3.setQuestionId(questionId);
//        answer3.setText("$$-1,1$$");
//        answer3.setCorrect(false);
//
//        aDao.insert(answer1, answer2, answer3);
//      }

//      {
//        Question question = new Question();
//        Answer answer1 = new Answer();
//        Answer answer2 = new Answer();
//
//        question.setText("$$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$");
//        question.setType(Question.TRUE_FALSE);
//        question.setRandomAnswer(true);
//        long questionId = qDao.insert(question);
//
//        answer1.setQuestionId(questionId);
//        answer1.setText("True");
//        answer1.setCorrect(true);
//
//        answer2.setQuestionId(questionId);
//        answer2.setText("False");
//        answer2.setCorrect(false);
//
//        aDao.insert(answer1, answer2);
//      }

//      {
//        Question question = new Question();
//        Answer answer1 = new Answer();
//        Answer answer2 = new Answer();
//        Answer answer3 = new Answer();
//        Answer answer4 = new Answer();
//
//        question.setText("If $$f(x)=3x+2,$$ then $$f^-1(x)=$$");
//        question.setType(Question.MULTI_ANS);
//        question.setRandomAnswer(false);
//        long questionId = qDao.insert(question);
//
//        answer1.setQuestionId(questionId);
//        answer1.setText("$$\\frac{x-2}{3}$$");
//        answer1.setCorrect(true);
//
//        answer2.setQuestionId(questionId);
//        answer2.setText("$$\\frac{1}{3x+2}$$");
//        answer2.setCorrect(false);
//
//        answer3.setQuestionId(questionId);
//        answer3.setText("$$\\frac x3 -2$$");
//        answer3.setCorrect(false);
//
//        answer4.setQuestionId(questionId);
//        answer4.setText("None of the Above");
//        answer4.setCorrect(false);
//
//        aDao.insert(answer1, answer2, answer3, answer4);
//
//      }
//      forgetInstance(context);
    }
}
