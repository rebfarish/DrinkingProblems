package com.enenby.drinkingproblems;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import java.util.Collections;


public class MultiChoiceFragment extends Fragment implements RadioButton.OnClickListener {


  private RadioButton optionAButton;
  private RadioButton optionBButton;
  private RadioButton optionCButton;
  private TextView cabButton;
  private TextView emergencyButton;
  private TextView questionTextView;
  private int correct;
  private View v;
  private QuestionAndAnswers questionAndAnswers;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  public void onClick(View view) {
    // Is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();

    // Check which radio button was clicked
    switch (view.getId()) {
      case R.id.option_a_button:
        if (checked){
          if(questionAndAnswers.getAnswers().get(0).isCorrect()){
            Toast.makeText(getActivity(),"Correct" ,Toast.LENGTH_LONG ).show();
          }else {
            Toast.makeText(getActivity(),"Incorrect" ,Toast.LENGTH_LONG ).show();
          }
        }
          break;
      case R.id.option_b_button:
        if(checked){
        if(questionAndAnswers.getAnswers().get(1).isCorrect()){
          Toast.makeText(getActivity(),"Correct" ,Toast.LENGTH_LONG ).show();
        }else {
          Toast.makeText(getActivity(),"Incorrect" ,Toast.LENGTH_LONG ).show();
        }
      }
          break;
      case R.id.option_c_button:
        if(checked){
        if(questionAndAnswers.getAnswers().get(2).isCorrect()){
          Toast.makeText(getActivity(),"Correct" ,Toast.LENGTH_LONG ).show();
        }else {
          Toast.makeText(getActivity(),"Incorrect" ,Toast.LENGTH_LONG ).show();
        }
      }
          break;

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_multi_choice, container, false);
    optionAButton = (RadioButton) v.findViewById(R.id.option_a_button);
    optionBButton = (RadioButton) v.findViewById(R.id.option_b_button);
    optionCButton = (RadioButton) v.findViewById(R.id.option_c_button);
    cabButton = (TextView) v.findViewById(R.id.cab_button);
    emergencyButton = (TextView) v.findViewById(R.id.emergency_button);
    questionTextView = v.findViewById(R.id.question_text);
    optionAButton.setOnClickListener(this);
    optionBButton.setOnClickListener(this);
    optionCButton.setOnClickListener(this);

    return v;
  }

  private class QuestionAndAnswersTask extends AsyncTask<Long, Void, QuestionAndAnswers> {

    @Override
    protected void onPostExecute(QuestionAndAnswers questionAndAnswers) {
      super.onPostExecute(questionAndAnswers);
      if (questionAndAnswers.getQuestion().isRandomAnswer()) {
        Collections.shuffle(questionAndAnswers.getAnswers());
      }
      optionAButton.setText(questionAndAnswers.getAnswers().get(0).getText());
      optionBButton.setText(questionAndAnswers.getAnswers().get(1).getText());
      optionCButton.setText(questionAndAnswers.getAnswers().get(2).getText());
      questionTextView.setText(questionAndAnswers.getQuestion().getText());

      MultiChoiceFragment.this.questionAndAnswers = questionAndAnswers;


    }

    @Override
    protected QuestionAndAnswers doInBackground(Long... id) {
      return QuestionsDatabase.getInstance(getActivity()).getQuestionDao().selectById(id[0]);
    }
  }
}


