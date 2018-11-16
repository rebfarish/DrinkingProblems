package com.enenby.drinkingproblems.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import com.enenby.drinkingproblems.R;
import com.enenby.drinkingproblems.model.entity.Answer;
import io.github.kexanie.library.MathView;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Answer> {

  int selectedIndex = -1;

  public CustomAdapter(Context context, int fragment_multi_choice, List<Answer> saveItems) {
    super(context, fragment_multi_choice, saveItems);
  }

  public void setSelectedIndex(int index) {
    selectedIndex = index;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View v = convertView;

    if (v == null) {
      LayoutInflater vi;
      vi = LayoutInflater.from(getContext());
      v = vi.inflate(R.layout.fragment_multi_choice, null);
    }

    RadioButton buttonA = (RadioButton) v.findViewById(R.id.button_a);
    if (selectedIndex == position) {
      //Check the position to update correct radio button.
      buttonA.setChecked(true);
    } else {
      buttonA.setChecked(false);
    }

    Answer answer = getItem(position);

    if (answer != null) {
      MathView optionAButton = (MathView) v.findViewById(R.id.option_a_button);


      //call QuestionAndAnswers task
    }
    return v;
  }
}

