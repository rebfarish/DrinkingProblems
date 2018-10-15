package com.enenby.drinkingproblems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


  private Button optionAButton;
  private Button optionBButton;
  private Button optionCButton;
  private TextView questionTextView;
  private int correct;
  private int incorrect;

  private Question [] mQuestionBank = new Question[]{
      new Question(R.string.question_test, true)
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
