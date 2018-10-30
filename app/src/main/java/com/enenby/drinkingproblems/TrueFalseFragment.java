package com.enenby.drinkingproblems;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class TrueFalseFragment extends Fragment {


  public TrueFalseFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_true_false, container, false);
    RadioButton trueButton = view.findViewById(R.id.true_button);
    RadioButton falseButton = view.findViewById(R.id.false_button);
    return view;
  }

}
