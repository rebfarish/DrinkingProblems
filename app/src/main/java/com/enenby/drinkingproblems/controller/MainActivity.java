package com.enenby.drinkingproblems.controller;

import static com.enenby.drinkingproblems.model.entity.Question.MULTI_CHOICE;

import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;
import com.enenby.drinkingproblems.MultiAnswerFragment;
import com.enenby.drinkingproblems.MultiChoiceFragment;
import com.enenby.drinkingproblems.OptionsMenu;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.TrueFalseFragment;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.entity.Question;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import java.util.List;


public class MainActivity extends AppCompatActivity {


  public static final String QUESTION_AND_ANSWER = "QuestionAndAnswer";
  public static final String QUESTION_ID = "QuestionId";
  private TextView questionText;
  private int correct;
  private Toolbar topToolbar;
  private OnClickListener listener;
  private QuestionsDatabase database;


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    //Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    Fragment fragment = null;

    int id = item.getItemId();

    if (id == R.id.more_vert) {
      FragmentManager fm = getSupportFragmentManager();
      Fragment fragmentOptions = new OptionsMenu();

      fm.beginTransaction().add(R.id.fragment_container_options, fragmentOptions).commit();

    } else if (id == R.id.arrow_back) {
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

    new QueryTask().execute();
  }

  @Override
  protected void onStart() {
    super.onStart();
    database = QuestionsDatabase.getInstance(this);
    new QueryTask().execute();
  }

  @Override
  protected void onStop() {
    database = null;
    QuestionsDatabase.forgetInstance(this);
    super.onStop();
  }

  private class QueryTask extends AsyncTask<Void, Void, Question>{

    @Override
    protected void onPostExecute(Question question) {
      super.onPostExecute(question);
      Fragment fragment = null;
      switch (question.getType()) {
        case Question.MULTI_CHOICE:
          fragment = new MultiChoiceFragment();
          break;
        case Question.TRUE_FALSE:
          fragment = new TrueFalseFragment();
          break;
        case Question.MULTI_ANS:
          fragment = new MultiAnswerFragment();
      }
      if (fragment != null) {
        //figure this out how bundles work
        Bundle bundle = new Bundle();
        bundle.putLong(QUESTION_ID, question.getId());
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container_options, fragment)
            .commit();
      }
    }


    @Override
    protected Question doInBackground(Void... voids) {
      database = QuestionsDatabase.getInstance(MainActivity.this);
      return database.getQuestionDao().selectRandomWithAnswers();
    }
  }


}

//TODO add timer to run in background