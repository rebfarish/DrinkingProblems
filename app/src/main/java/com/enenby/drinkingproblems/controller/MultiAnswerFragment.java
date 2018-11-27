package com.enenby.drinkingproblems.controller;

import static android.content.Context.DEVICE_POLICY_SERVICE;
import static com.enenby.drinkingproblems.controller.MainActivity.QUESTION_ID;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.ScreenLock;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import io.github.kexanie.library.MathView;
import java.util.Collections;

/**
 * This fragment displays a multiple answer question.
 */
public class MultiAnswerFragment extends QuestionsFragment implements CheckBox.OnClickListener {

  private CheckBox checkboxA;
  private CheckBox checkboxB;
  private CheckBox checkboxC;
  private CheckBox checkboxD;
  private TextView cabButton;
  private TextView emergencyButton;
  private MathView questionTextView;
  private MathView mathButtonA;
  private MathView mathButtonB;
  private MathView mathButtonC;
  private MathView mathButtonD;
  private Button submit;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

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
    mathButtonA = v.findViewById(R.id.checkbox_a_button);
    mathButtonB = v.findViewById(R.id.checkbox_b_button);
    mathButtonC = v.findViewById(R.id.checkbox_c_button);
    mathButtonD = v.findViewById(R.id.checkbox_d_button);
    submit = v.findViewById(R.id.submit);
    cabButton = v.findViewById(R.id.cab_button);
    emergencyButton = v.findViewById(R.id.emergency_button);
    questionTextView = v.findViewById(R.id.question_text);
    submit.setOnClickListener(this);
    cabButton.setOnClickListener(this);
    emergencyButton.setOnClickListener(this);
    mathButtonA.setOnClickListener(this);
    mathButtonB.setOnClickListener(this);
    mathButtonC.setOnClickListener(this);
    mathButtonD.setOnClickListener(this);
    mathButtonA.setEnabled(true);
    mathButtonB.setEnabled(true);
    mathButtonC.setEnabled(true);
    mathButtonD.setEnabled(true);
    mathButtonA.setClickable(true);
    mathButtonB.setClickable(true);
    mathButtonC.setClickable(true);
    mathButtonD.setClickable(true);
    mathButtonA.setOnTouchListener((v1, event) -> {
      v1.performClick();
      return true;
    });
    mathButtonB.setOnTouchListener((v1, event) -> {
      v1.performClick();
      return true;
    });
    mathButtonC.setOnTouchListener((v1, event) -> {
      v1.performClick();
      return true;
    });
    mathButtonD.setOnTouchListener((v1, event) -> {
      v1.performClick();
      return true;
    });

    devicePolicyManager = (DevicePolicyManager) getActivity()
        .getSystemService(DEVICE_POLICY_SERVICE);
    compName = new ComponentName(getActivity(), ScreenLock.class);

    Bundle bundle = getArguments();

    new QuestionAndAnswersTask().execute(bundle.getLong(QUESTION_ID));
    return v;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.checkbox_a_button:
        checkboxA.setChecked(true);
        break;
      case R.id.checkbox_b_button:
        checkboxB.setChecked(true);
        break;
      case R.id.checkbox_c_button:
        checkboxC.setChecked(true);
        break;
      case R.id.checkbox_d_button:
        checkboxD.setChecked(true);
        break;
      case R.id.submit:
        checkAnswer();
        break;
      case R.id.cab_button:
        callCab();
        break;

      case R.id.emergency_button:
        emergency();
        break;
    }
  }

  private void checkAnswer() {
    if (checkboxA.isChecked() == questionAndAnswers.getAnswers().get(0).isCorrect() &&
        checkboxB.isChecked() == questionAndAnswers.getAnswers().get(1).isCorrect() &&
        checkboxC.isChecked() == questionAndAnswers.getAnswers().get(2).isCorrect() &&
        checkboxD.isChecked() == questionAndAnswers.getAnswers().get(3).isCorrect()) {
      handleAnswer(true);
    } else {
      handleAnswer(false);
    }
  }

  private class QuestionAndAnswersTask extends AsyncTask<Long, Void, QuestionAndAnswers> {

    @Override
    protected void onPostExecute(QuestionAndAnswers questionAndAnswers) {
      super.onPostExecute(questionAndAnswers);
      if (questionAndAnswers.getQuestion().isRandomAnswer()) {
        Collections.shuffle(questionAndAnswers.getAnswers());
      }

      mathButtonA.setText(questionAndAnswers.getAnswers().get(0).getText());
      mathButtonB.setText(questionAndAnswers.getAnswers().get(1).getText());
      mathButtonC.setText(questionAndAnswers.getAnswers().get(2).getText());
      mathButtonD.setText(questionAndAnswers.getAnswers().get(3).getText());
      questionTextView.setText(questionAndAnswers.getQuestion().getText());

      MultiAnswerFragment.this.questionAndAnswers = questionAndAnswers;
    }

    @Override
    protected QuestionAndAnswers doInBackground(Long... id) {
      return QuestionsDatabase.getInstance(getActivity()).getQuestionDao().selectById(id[0]);
    }
  }
}
