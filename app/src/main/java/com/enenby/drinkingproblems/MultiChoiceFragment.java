package com.enenby.drinkingproblems;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MultiChoiceFragment extends Fragment {


  private RadioButton optionAButton;
  private RadioButton optionBButton;
  private RadioButton optionCButton;
  private TextView cabButton;
  private TextView emergencyButton;
  private TextView questionTextView;
  private int correct;
  private View view;
  private Toolbar topToolbar;

  //TODO fix question bank reference geo quiz
//  private Question[] mQuestionBank = new Question[]{
//      new Question(R.string.question_test),
//      new Question(R.string.question_test1),
//      new Question(R.string.question_test2),
//  };

  //starting the question bank at question 0
  private int CurrentIndex = 0;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  public void onRadioButtonClicked (View view){
    // Is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();

    // Check which radio button was clicked
    switch (view.getId()) {
      case R.id.option_a_button:
        if (checked)
        // TODO check answer
        {
          break;
        }
      case R.id.option_b_button:
        if (checked)
        // TODO check answer
        {
          break;
        }
      case R.id.option_c_button:
        if (checked)
        // TODO check answer
        {
          break;
        }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater,  ViewGroup container,
       Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_multi_choice, container, false);
    optionAButton = (RadioButton) v.findViewById(R.id.option_a_button);
    optionBButton = (RadioButton) v.findViewById(R.id.option_b_button);
    optionCButton = (RadioButton) v.findViewById(R.id.option_c_button);
    cabButton = (TextView) v.findViewById(R.id.cab_button);
    emergencyButton = (TextView) v.findViewById(R.id.emergency_button);


    return v;
  }
}


