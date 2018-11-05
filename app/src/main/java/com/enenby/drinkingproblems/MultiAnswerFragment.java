package com.enenby.drinkingproblems;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


public class MultiAnswerFragment extends Fragment {


  private CheckBox checkboxA;
  private CheckBox checkboxB;
  private CheckBox checkboxC;
  private CheckBox checkboxD;
  private TextView cabButton;
  private TextView emergencyButton;
  private TextView questionTextView;


//  private OnFragmentInteractionListener mListener;

  public MultiAnswerFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_multi_answer, container, false);
    checkboxA = (CheckBox) v.findViewById(R.id.checkbox_a);
    checkboxB = (CheckBox) v.findViewById(R.id.checkbox_b);
    checkboxC = (CheckBox) v.findViewById(R.id.checkbox_c);
    checkboxD = (CheckBox) v.findViewById(R.id.checkbox_d);
    cabButton = (TextView) v.findViewById(R.id.cab_button);
    emergencyButton = (TextView) v.findViewById(R.id.emergency_button);

    return v;
  }

//  // TODO: Rename method, update argument and hook method into UI event
//  public void onButtonPressed(Uri uri) {
//    if (mListener != null) {
//      mListener.onFragmentInteraction(uri);
//    }
//  }
//
//
//  }
//
//  /**
//   * This interface must be implemented by activities that contain this fragment to allow an
//   * interaction in this fragment to be communicated to the activity and potentially other fragments
//   * contained in that activity.
//   * <p>
//   * See the Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html"
//   * >Communicating with Other Fragments</a> for more information.
//   */
//  public interface OnFragmentInteractionListener {
//
//    // TODO: Update argument type and name
//    void onFragmentInteraction(Uri uri);
//  }
}
