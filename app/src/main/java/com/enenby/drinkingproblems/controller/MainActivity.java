package com.enenby.drinkingproblems.controller;

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


public class MainActivity extends AppCompatActivity {


  private TextView questionTextView;
  private int correct;

  private Toolbar topToolbar;
  private OnClickListener listener;
  private Button fragOneButton, fragTwoButton, fragThreeButton;


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


   fragOneButton = (Button) findViewById(R.id.fragment_one);
    fragTwoButton = (Button) findViewById(R.id.fragment_two);
    fragThreeButton = (Button) findViewById(R.id.fragment_three);

    topToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(topToolbar);



    listener = new OnClickListener() {
      @Override
      public void onClick(View view) {
        Fragment fragment = null;
        int id= view.getId();
        switch (id) {
          case R.id.fragment_one:
            fragment = new MultiChoiceFragment();
            break;
          case R.id.fragment_two:
            fragment = new TrueFalseFragment();
            break;
          case R.id.fragment_three:
            fragment = new MultiAnswerFragment();
        }
        if (fragment != null) {
          getSupportFragmentManager().beginTransaction()
              .replace(R.id.fragment_container_options, fragment)
              .commit();
        }
      }
    };

    fragOneButton.setOnClickListener(listener);
    fragTwoButton.setOnClickListener(listener);
    fragThreeButton.setOnClickListener(listener);
  }
}






    //TODO add cycle through questions
//TODO add timer to run in background