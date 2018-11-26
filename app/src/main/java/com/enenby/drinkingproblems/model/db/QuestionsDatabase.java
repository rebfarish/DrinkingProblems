package com.enenby.drinkingproblems.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.enenby.drinkingproblems.model.dao.AnswerDao;
import com.enenby.drinkingproblems.model.dao.QuestionDao;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.entity.Question;


/**
 * This is a database of math questions.
 */
@Database(
    entities = {Answer.class, Question.class},
    version = 1,
    exportSchema = true
)

public abstract class QuestionsDatabase extends RoomDatabase {

  private static final String DB_NAME = "questions_db";
  private static QuestionsDatabase instance = null;


  /**
   * Gets instance of the database.
   *
   * @param context
   * @return the instance of the database
   */
  public synchronized static QuestionsDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), QuestionsDatabase.class,
          DB_NAME)
          .build();
    }
    return instance;
  }

  /**
   * Forgets instance of the database.
   *
   * @param context
   */
  public synchronized static void forgetInstance(Context context) {
    instance = null;

  }

  /**
   * Gets answer dao.
   *
   * @return the answer dao
   */
  public abstract AnswerDao getAnswerDao();

  /**
   * Gets question dao.
   *
   * @return the question dao
   */
  public abstract QuestionDao getQuestionDao();

  /**
   * Populates questions with answers to the database.
   *
   * @param context
   */
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

      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        question.setText("What is $$\\lim_{x\\to 0} \\frac{\\sin x}{x}?$$");
        question.setType(Question.MULTI_CHOICE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("$$1$$");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("$$0$$");
        answer2.setCorrect(false);

        answer3.setQuestionId(questionId);
        answer3.setText("$$\\infty$$");
        answer3.setCorrect(false);

        aDao.insert(answer1, answer2, answer3);
      }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();
      Answer answer4 = new Answer();

      question.setText("Quick sort and merge sort are both examples of which big O notation?");
      question.setType(Question.MULTI_CHOICE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$O(n)$$");
      answer1.setCorrect(false);

      answer2.setQuestionId(questionId);
      answer2.setText("$$O(n\\log(n))$$");
      answer2.setCorrect(true);

      answer3.setQuestionId(questionId);
      answer3.setText("$$O(\\log(n))$$");
      answer3.setCorrect(false);

      aDao.insert(answer1, answer2, answer3);

    }
      {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        question.setText("The zeros of $$f(x)=x^3-2x^2+x$$");
        question.setType(Question.MULTI_CHOICE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("$$0,-1$$");
        answer1.setCorrect(false);

        answer2.setQuestionId(questionId);
        answer2.setText("$$0,1$$");
        answer2.setCorrect(true);

        answer3.setQuestionId(questionId);
        answer3.setText("$$-1,1$$");
        answer3.setCorrect(false);

        aDao.insert(answer1, answer2, answer3);
      }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();

      question.setText("$$\\frac{\\theta}{d\\theta} \\cos^{3}\\theta =$$");
      question.setType(Question.MULTI_CHOICE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$-3\\sin\\theta\\cos^{2}\\theta$$");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("$$-3\\sin^{2}\\theta\\cos^{2}\\theta $$");
      answer2.setCorrect(false);

      answer3.setQuestionId(questionId);
      answer3.setText("$$-3\\sin\\cos^{2}\\theta$$");
      answer3.setCorrect(false);

      aDao.insert(answer1, answer2, answer3);
    }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();

      question.setText("If $$f(x)=3x+2,$$ then $$f^-1(x)=$$");
      question.setType(Question.MULTI_CHOICE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$\\frac{x-2}{3}$$");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("$$\\frac{1}{3x+2}$$");
      answer2.setCorrect(false);

      answer3.setQuestionId(questionId);
      answer3.setText("$$\\frac x3 -2$$");
      answer3.setCorrect(false);

      aDao.insert(answer1, answer2, answer3);

    }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();

      question.setText("$${Re}(e^{ix}) = \\frac{e^{ix}+e^{-ix}}{2}=$$");
      question.setType(Question.MULTI_CHOICE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$\\cos x$$");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("$$\\sin x$$");
      answer2.setCorrect(false);

      answer3.setQuestionId(questionId);
      answer3.setText("$$-\\cos x$$");
      answer3.setCorrect(false);

      aDao.insert(answer1, answer2, answer3);

    }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();

      question.setText("$$grad(f) = \\frac{\\partial f}{\\partial x} \\vec{v}_x + "
          + "\\frac{\\partial f}{\\partial y} \\vec{v}_y + "
          + "\\frac{\\partial f}{\\partial z} \\vec{v}_z =$$");
      question.setType(Question.MULTI_CHOICE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$\\nabla f$$");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("$$\\nabla \\cdot f$$");
      answer2.setCorrect(false);

      answer3.setQuestionId(questionId);
      answer3.setText("$$\\nabla \\cdot \\vec{v}$$");
      answer3.setCorrect(false);

      aDao.insert(answer1, answer2, answer3);

    }

    {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();

        question.setText("$$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$");
        question.setType(Question.TRUE_FALSE);
        question.setRandomAnswer(true);
        long questionId = qDao.insert(question);

        answer1.setQuestionId(questionId);
        answer1.setText("True");
        answer1.setCorrect(true);

        answer2.setQuestionId(questionId);
        answer2.setText("False");
        answer2.setCorrect(false);

        aDao.insert(answer1, answer2);
      }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();

      question.setText("The divergence of a vector field$$\\vec{v}(x,y,z)$$ is a scalar "
          + "function that can be represented as:$$\\nabla \\cdot \\vec{v}$$");
      question.setType(Question.TRUE_FALSE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("True");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("False");
      answer2.setCorrect(false);

      aDao.insert(answer1, answer2);
    }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();

      question.setText("$$\\lim_{x\\to 0} \\frac 1x$$ does not exist.");
      question.setType(Question.TRUE_FALSE);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("True");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("False");
      answer2.setCorrect(false);

      aDao.insert(answer1, answer2);
    }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();
      Answer answer4 = new Answer();

      question.setText("$$\\frac{d}{dx}\\tan (x) =$$");
      question.setType(Question.MULTI_ANS);
      question.setRandomAnswer(false);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$\\sec^{2} (x)$$");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("$$\\sec (x)$$");
      answer2.setCorrect(false);

      answer3.setQuestionId(questionId);
      answer3.setText("$$\\tan^{2} (x)+1$$");
      answer3.setCorrect(true);

      answer4.setQuestionId(questionId);
      answer4.setText("$$None Of The Above.$$");
      answer4.setCorrect(false);

      aDao.insert(answer1, answer2, answer3, answer4);

    }

    {
      Question question = new Question();
      Answer answer1 = new Answer();
      Answer answer2 = new Answer();
      Answer answer3 = new Answer();
      Answer answer4 = new Answer();

      question.setText("The complex conjugate $$\\bar{z}$$ can also be written as:");
      question.setType(Question.MULTI_ANS);
      question.setRandomAnswer(true);
      long questionId = qDao.insert(question);

      answer1.setQuestionId(questionId);
      answer1.setText("$$x - iy$$");
      answer1.setCorrect(true);

      answer2.setQuestionId(questionId);
      answer2.setText("$$re^{-i\\varphi}$$");
      answer2.setCorrect(true);

      answer3.setQuestionId(questionId);
      answer3.setText("$$|z|(\\cos \\varphi - i\\sin \\varphi)$$");
      answer3.setCorrect(true);

      answer4.setQuestionId(questionId);
      answer4.setText("$$re^{i\\varphi}$$");
      answer4.setCorrect(false);

      aDao.insert(answer1, answer2, answer3, answer4);

    }
      forgetInstance(context);
    }
}
