package com.enenby.drinkingproblems;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;


public class MultiAnswerFragment extends Fragment implements CheckBox.OnClickListener{


  private CheckBox checkboxA;
  private CheckBox checkboxB;
  private CheckBox checkboxC;
  private CheckBox checkboxD;
  private TextView cabButton;
  private TextView emergencyButton;
  private TextView questionTextView;
  private QuestionAndAnswers questionAndAnswers;


  public MultiAnswerFragment() {
    // Required empty public constructor
  }

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
          if (checked){
            if(questionAndAnswers.getAnswers().get(0).isCorrect()){
              Toast.makeText(getActivity(),"Correct" ,Toast.LENGTH_LONG ).show();
            }else {
              Toast.makeText(getActivity(),"Incorrect" ,Toast.LENGTH_LONG ).show();
            }
          }
          break;
        case R.id.checkbox_b:
          if(checked){
            if(questionAndAnswers.getAnswers().get(1).isCorrect()){
              Toast.makeText(getActivity(),"Correct" ,Toast.LENGTH_LONG ).show();
            }else {
              Toast.makeText(getActivity(),"Incorrect" ,Toast.LENGTH_LONG ).show();
            }
          }
          break;
        case R.id.checkbox_c:
          if(checked){
            if(questionAndAnswers.getAnswers().get(2).isCorrect()){
              Toast.makeText(getActivity(),"Correct" ,Toast.LENGTH_LONG ).show();
            }else {
              Toast.makeText(getActivity(),"Incorrect" ,Toast.LENGTH_LONG ).show();
            }
          }
          break;
        case R.id.checkbox_d:
          if(checked){
            if(questionAndAnswers.getAnswers().get(3).isCorrect()){
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
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_multi_answer, container, false);
    checkboxA = (CheckBox) v.findViewById(R.id.checkbox_a);
    checkboxB = (CheckBox) v.findViewById(R.id.checkbox_b);
    checkboxC = (CheckBox) v.findViewById(R.id.checkbox_c);
    checkboxD = (CheckBox) v.findViewById(R.id.checkbox_d);
    cabButton = (TextView) v.findViewById(R.id.cab_button);
    emergencyButton = (TextView) v.findViewById(R.id.emergency_button);
    questionTextView = v.findViewById(R.id.question_text);
    checkboxA.setOnClickListener(this);
    checkboxB.setOnClickListener(this);
    checkboxC.setOnClickListener(this);
    checkboxD.setOnClickListener(this);

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
