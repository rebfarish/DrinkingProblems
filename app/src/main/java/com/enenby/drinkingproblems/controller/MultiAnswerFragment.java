package com.enenby.drinkingproblems.controller;

import static com.enenby.drinkingproblems.controller.MainActivity.QUESTION_ID;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import io.github.kexanie.library.MathView;


public class MultiAnswerFragment extends QuestionsFragment implements MathView.OnClickListener{

  private MathView checkboxA;
  private MathView checkboxB;
  private MathView checkboxC;
  private MathView checkboxD;
  private TextView cabButton;
  private TextView emergencyButton;
  private MathView questionTextView;



  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public void onClick(View view){
    //Is the checkbox checked?
    boolean checked = ((CheckBox) view).isChecked();

      // Check which radio button was clicked
      switch (view.getId()) {
        case R.id.checkbox_a:
          if (checked) {
            checkAnswer(0);
          }
          break;
        case R.id.checkbox_b:
          if (checked) {
            checkAnswer(1);
          }
          break;
        case R.id.checkbox_c:
          if (checked) {
          checkAnswer(2);
        }
          break;
        case R.id.checkbox_d:
          if (checked) {
            checkAnswer(3);
          }
          break;
      }
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_multi_answer, container, false);
    checkboxA = v.findViewById(R.id.checkbox_a);
    checkboxB = v.findViewById(R.id.checkbox_b);
    checkboxC = v.findViewById(R.id.checkbox_c);
    checkboxD = v.findViewById(R.id.checkbox_d);
    cabButton = v.findViewById(R.id.cab_button);
    emergencyButton = v.findViewById(R.id.emergency_button);
    questionTextView = v.findViewById(R.id.question_text);
    checkboxA.setOnClickListener(this);
    checkboxB.setOnClickListener(this);
    checkboxC.setOnClickListener(this);
    checkboxD.setOnClickListener(this);


    Bundle bundle = getArguments();

    new QuestionAndAnswersTask().execute(bundle.getLong(QUESTION_ID));
    return v;
  }


  private class QuestionAndAnswersTask extends AsyncTask<Long, Void, QuestionAndAnswers> {

    @Override
    protected void onPostExecute(QuestionAndAnswers questionAndAnswers) {
      super.onPostExecute(questionAndAnswers);

      checkboxA.setText(questionAndAnswers.getAnswers().get(0).getText());
      checkboxB.setText(questionAndAnswers.getAnswers().get(1).getText());
      checkboxC.setText(questionAndAnswers.getAnswers().get(2).getText());
      checkboxD.setText(questionAndAnswers.getAnswers().get(3).getText());
      questionTextView.setText(questionAndAnswers.getQuestion().getText());

      MultiAnswerFragment.this.questionAndAnswers=questionAndAnswers;
    }

    @Override
    protected QuestionAndAnswers doInBackground(Long... id) {
      return QuestionsDatabase.getInstance(getActivity()).getQuestionDao().selectById(id[0]);
    }
  }
}