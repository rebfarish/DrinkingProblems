package com.enenby.drinkingproblems;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * The type Options menu.
 */
public class OptionsMenu extends Fragment {


  private TextView general;
  private TextView uber;
  private TextView lyft;
  private TextView geoTaxi;
  private TextView help;
  private TextView emergency;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    //Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_options_menu, container, false);

   general  = (TextView) v.findViewById(R.id.general_settings);
   uber = (TextView) v.findViewById(R.id.uber);
   lyft = (TextView) v.findViewById(R.id.lyft);
   geoTaxi = (TextView) v.findViewById(R.id.geo_taxi);
   help = (TextView) v.findViewById(R.id.help);
   emergency = (TextView) v.findViewById(R.id.emergency_button);


   //TODO wire up the buttons
  return v;
  }
}
