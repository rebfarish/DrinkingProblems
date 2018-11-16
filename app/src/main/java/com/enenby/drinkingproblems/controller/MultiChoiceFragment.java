package com.enenby.drinkingproblems.controller;

import static android.content.Context.DEVICE_POLICY_SERVICE;
import static com.enenby.drinkingproblems.controller.MainActivity.QUESTION_ID;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.ScreenLock;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.entity.Answer;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import io.github.kexanie.library.MathView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MultiChoiceFragment extends QuestionsFragment implements View.OnClickListener {


  private MathView optionAButton;
  private MathView optionBButton;
  private MathView optionCButton;
  private TextView cabButton;
  private TextView emergencyButton;
  private MathView questionTextView;
  private int correct;
  private View v;
  public static final int RESULT_ENABLE = 11;



  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


  }



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_multi_choice, container, false);
    optionAButton = v.findViewById(R.id.option_a_button);
    optionBButton = v.findViewById(R.id.option_b_button);
    optionCButton = v.findViewById(R.id.option_c_button);
    cabButton = v.findViewById(R.id.cab_button);
    emergencyButton = v.findViewById(R.id.emergency_button);
    questionTextView = v.findViewById(R.id.question_text);
//    optionAButton.setOnClickListener(this);
//    optionBButton.setOnClickListener(this);
//    optionCButton.setOnClickListener(this);
//    optionAButton.setEnabled(true);
//    optionBButton.setEnabled(true);
//    optionCButton.setEnabled(true);
//    optionAButton.setClickable(true);
//    optionBButton.setClickable(true);
//    optionCButton.setClickable(true);
//    optionAButton.setOnTouchListener(new OnTouchListener() {
//      @Override
//      public boolean onTouch(View v1, MotionEvent event) {
//        v1.performClick();
//        return true;
//      }
//    });
//    optionBButton.setOnTouchListener((v1, event) -> true);
//    optionCButton.setOnTouchListener((v1, event) -> true);
    final List<Answer> saveItems = new ArrayList<>();
    String answer = null;

    // save your values here to the ItemContent List. usign dummy values as of now

    final CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.activity_main, saveItems);
    ListView listview = (ListView) v.findViewById(R.id.radioGroup);
    listview.setAdapter(adapter);
    listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setSelectedIndex(position);
        adapter.notifyDataSetChanged();
      }
    });

    devicePolicyManager = (DevicePolicyManager) getActivity()
        .getSystemService(DEVICE_POLICY_SERVICE);
    compName = new ComponentName(getActivity(), ScreenLock.class);

    Bundle bundle = getArguments();

    new QuestionAndAnswersTask().execute(bundle.getLong(QUESTION_ID));
    return v;
  }

private static Answer answers(int i, final String answer){
    Answer itemContent = new Answer();
    itemContent.setText(answer);//Not sure about this line...
    return itemContent;
}

  public void onClick(View view) {
    // Is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();

    // Check which radio button was clicked
    switch (view.getId()) {
      case R.id.option_a_button:
        if (checked) {
          checkAnswer(0);
        }
        break;
      case R.id.option_b_button:
        if (checked) {
          checkAnswer(1);
        }
        break;
      case R.id.option_c_button:
        if (checked) {
          checkAnswer(2);
        }
        break;
    }
  }



  public class QuestionAndAnswersTask extends AsyncTask<Long, Void, QuestionAndAnswers> {

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


