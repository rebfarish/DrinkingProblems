package com.enenby.drinkingproblems;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.enenby.drinkingproblems.controller.QuestionsFragment;


/**
 * This fragment displays the Options menu.
 */
public class OptionsMenu extends QuestionsFragment implements OnClickListener {

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
    View v = inflater.inflate(R.layout.fragment_options_menu, container, false);
    setHasOptionsMenu(true);

    general = v.findViewById(R.id.general_settings);
    uber = v.findViewById(R.id.uber);
    lyft = v.findViewById(R.id.lyft);
    geoTaxi = v.findViewById(R.id.geo_taxi);
    help = v.findViewById(R.id.help);
    emergency = v.findViewById(R.id.emergency_button);

    general.setOnClickListener(this);
    uber.setOnClickListener(this);
    lyft.setOnClickListener(this);
    geoTaxi.setOnClickListener(this);
    help.setOnClickListener(this);
    emergency.setOnClickListener(this);
    return v;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.general_settings:
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        break;
      case R.id.uber:
        callCab();
        break;
      case R.id.lyft:
        Uri lyft = Uri.parse(
            "https://www.lyft.com/signup/SDKSIGNUP?clientId=YOUR_CLIENT_ID&sdkName=android_direct");
        Intent intent = new Intent(Intent.ACTION_VIEW, lyft);
        startActivity(intent);
        break;
      case R.id.geo_taxi:
        Uri geoTaxi = Uri.parse(
            "https://www.google.com/maps/search/taxi+near+me/@35.0848757,-106.6555811,14z/data=!3m1!4b1");
        Intent tIntent = new Intent(Intent.ACTION_VIEW, geoTaxi);
        tIntent.setPackage("com.google.android.apps.maps");
        startActivity(tIntent);
        break;
      case R.id.help:
        Uri help = Uri.parse("https://rebfarish.github.io/DrinkingProblems/UserInstructions.html");
        Intent hIntent = new Intent(Intent.ACTION_VIEW, help);
        startActivity(hIntent);
        break;
      case R.id.emergency_button:
        emergency();
        break;
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.arrow_back) {
      getFragmentManager().popBackStack();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}

