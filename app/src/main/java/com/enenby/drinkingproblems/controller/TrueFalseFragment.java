package com.enenby.drinkingproblems.controller;


import static com.enenby.drinkingproblems.controller.MainActivity.QUESTION_ID;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import io.github.kexanie.library.MathView;
import java.util.Collections;

public class TrueFalseFragment extends QuestionsFragment implements RadioButton.OnClickListener {

  private RadioButton trueButton;
  private RadioButton falseButton;
  private TextView cabButton;
  private TextView emergencyButton;
  private MathView questionTextView;
  private int correct;
  private View v;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onClick(View view) {
    //is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();
    // Check which radio button was clicked
    switch (view.getId()) {
      case R.id.true_button:
        if (checked) {
          checkAnswer(0);
        }
        break;
      case R.id.false_button:
        if (checked) {
          checkAnswer(1);
        }
        break;
    }
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_true_false, container, false);
    trueButton = v.findViewById(R.id.true_button);
    falseButton = v.findViewById(R.id.false_button);
    cabButton = v.findViewById(R.id.cab_button);
    emergencyButton = v.findViewById(R.id.emergency_button);
    questionTextView = v.findViewById(R.id.question_text);
    trueButton.setOnClickListener(this);
    falseButton.setOnClickListener(this);

    Bundle bundle = getArguments();
    new QuestionAndAnswersTask().execute(bundle.getLong(QUESTION_ID));

    return v;
  }

  private class QuestionAndAnswersTask extends AsyncTask<Long, Void, QuestionAndAnswers> {

    @Override
    protected void onPostExecute(QuestionAndAnswers questionAndAnswers) {
      super.onPostExecute(questionAndAnswers);
      if (questionAndAnswers.getQuestion().isRandomAnswer()) {
        Collections.shuffle(questionAndAnswers.getAnswers());
      }
      trueButton.setText(questionAndAnswers.getAnswers().get(0).getText());
      falseButton.setText(questionAndAnswers.getAnswers().get(1).getText());
      questionTextView.setText(questionAndAnswers.getQuestion().getText());

      TrueFalseFragment.this.questionAndAnswers = questionAndAnswers;
    }

    @Override
    protected QuestionAndAnswers doInBackground(Long... id) {
      return QuestionsDatabase.getInstance(getActivity()).getQuestionDao().selectById(id[0]);
    }
  }



}