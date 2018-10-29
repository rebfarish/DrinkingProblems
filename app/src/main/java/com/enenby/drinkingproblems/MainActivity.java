package com.enenby.drinkingproblems;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



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
  public boolean onCreateOptionsMenu(Menu menu) {
    //Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    if (id == R.id.more_vert){
      Toast.makeText(MainActivity.this, "More Options clicked", Toast.LENGTH_LONG).show();
      return true;
    }else if (id == R.id.arrow_back){
      Toast.makeText(MainActivity.this, "Back Arrow clicked", Toast.LENGTH_LONG).show();
    }
    //TODO make tool bar buttons navigate to where they should go, get rid of toasts
    return super.onOptionsItemSelected(item);

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    topToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(topToolbar);



    FragmentManager fm = getSupportFragmentManager();
    Fragment fragmentOptions = fm.findFragmentById(R.id.fragment_container_options);

    if (fragmentOptions == null){
      fragmentOptions = new Fragment();
      fm.beginTransaction().add(R.id.fragment_container_options, fragmentOptions).commit();
    }

//TODO add timer to run in background
  }


    //TODO add cycle through questions
}


