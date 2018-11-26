package com.enenby.drinkingproblems.controller;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.enenby.drinkingproblems.OptionsMenu;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.ScreenLock;
import com.enenby.drinkingproblems.controller.QuestionsFragment.PhoneUnlockedReceiver;
import com.enenby.drinkingproblems.model.db.QuestionsDatabase;
import com.enenby.drinkingproblems.model.entity.Question;
import java.util.List;


/**
 * MainActivity loads questions from the database and loads a matching fragment to display it.
 */
public class MainActivity extends AppCompatActivity implements QuestionsFragment.QuestionLoader {


//  public static final String QUESTION_AND_ANSWER = "QuestionAndAnswer";

  /**
   * The constant QUESTION_ID is the primary key of the question table. Uniquely identifies each question.
   */
  protected static final String QUESTION_ID = "QuestionId";
  private TextView questionText;
  private int correct;
  private Toolbar topToolbar;
  private OnClickListener listener;
  private QuestionsDatabase database;
  private DevicePolicyManager devicePolicyManager;
  private ComponentName compName;
  private static final int RESULT_ENABLE = 11;
  private static long lastLockedTime = 0;


//TODO check to see if already signed in then just load fragment
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
    compName = new ComponentName(this, ScreenLock.class);
    registerReceiver(new PhoneUnlockedReceiver(), new IntentFilter("android.intent.action.USER_PRESENT"));

    topToolbar = findViewById(R.id.toolbar);
    setSupportActionBar(topToolbar);

    database = QuestionsDatabase.getInstance(this);


    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
        getString(R.string.lock_screen_permission));
    startActivityForResult(intent, RESULT_ENABLE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    new InitializeDataBase().execute();
    switch (requestCode) {
      case RESULT_ENABLE:
        if (resultCode == Activity.RESULT_OK) {
          Toast.makeText(MainActivity.this,
              R.string.enable_admin,
              Toast.LENGTH_LONG).show();



        } else {
          Toast.makeText(MainActivity.this,
              R.string.cannot_enable_admin,
              Toast.LENGTH_LONG).show();
        }
        break;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

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
      Toast.makeText(MainActivity.this, R.string.back_button, Toast.LENGTH_LONG).show();
    }
    //TODO make tool bar buttons navigate to where they should go
    return super.onOptionsItemSelected(item);
  }


  @Override
  protected void onResume() {
    super.onResume();
    View decorView = getWindow().getDecorView();
    decorView.setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN);
    String strIncorrect = getResources().getString(R.string.incorrect_toast,
        QuestionsFragment.incorrect);
    Toast.makeText(MainActivity.this, strIncorrect,Toast.LENGTH_LONG);

  }

  @Override
  protected void onStop() {
    database = null;
    QuestionsDatabase.forgetInstance(this);
    super.onStop();
  }


  //initializes data base to fix null pointer exception on OnCreate
  private class InitializeDataBase extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      List<Question> questions = QuestionsDatabase.getInstance(MainActivity.this)
          .getQuestionDao().select();
      if (questions.size() == 0){
        QuestionsDatabase.populateQuestions(MainActivity.this);
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        new QueryTask().execute();
    }
  }


  /**
   * Query task that loads correct fragment depending on question type.
   */
  class QueryTask extends AsyncTask<Void, Void, Question> {

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

  public void reloadQuestion(){
    new QueryTask().execute();
  }

  /**
   * Saves a tally of incorrectly answered questions to shared prefs.
   *
   * @param tally the tally of incorrectly answered questions
   */
  public void saveToSharedPrefs(int tally){
  SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
  SharedPreferences.Editor editor = sharedPreferences.edit();
  editor.putInt(getString(R.string.string_key),tally);
  editor.apply();
}

  /**
   * Gets the saved tally from shared prefs.
   *
   * @return tally int
   */
  public int getFromSharedPrefs(){
    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    return sharedPreferences.getInt(getString(R.string.string_key), 0);
}

  /**
   * Gets last locked time.
   *
   * @return locked time long
   */
  public static long getLastLockedTime(){
    return lastLockedTime;
  }

  /**
   * Resets the last locked time.
   */
  public static void resetLastLockedTime(){
    lastLockedTime = System.currentTimeMillis();
  }



}

