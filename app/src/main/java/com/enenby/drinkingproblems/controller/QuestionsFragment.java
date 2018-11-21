package com.enenby.drinkingproblems.controller;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;

/**
 * The type Questions fragment.
 */
public abstract class QuestionsFragment extends Fragment {

  protected int incorrect = 0;
  /**
   * The Question and answers.
   */
  protected QuestionAndAnswers questionAndAnswers;
  /**
   * The constant devicePolicyManager.
   */
  protected static DevicePolicyManager devicePolicyManager;
  /**
   * The Comp name.
   */
  protected ComponentName compName;


  /**
   * Handle answer.
   *
   * @param isCorrect the is correct
   */
  protected void handleAnswer(boolean isCorrect){
    if(isCorrect){
      getActivity().finish();
    }else {
      boolean active = devicePolicyManager.isAdminActive(compName);
      if (active) {
        MainActivity.resetLastLockedTime();
        devicePolicyManager.lockNow();
        incorrect++;
        ((QuestionLoader) getActivity()).reloadQuestion();
      }
    }
  }

  /**
   * Check answer.
   *
   * @param i the
   */
  protected void checkAnswer(int i){
    if (questionAndAnswers.getAnswers().get(i).isCorrect()) {
      handleAnswer(true);
    }else{
      handleAnswer(false);
    }

  }


  /**
   * Call cab.
   */
  protected void callCab(){
    //TODO check if phone has Uber installed, give other cab options
    Uri uber = Uri.parse("https://m.uber.com/ul/?client_id=<CLIENT_ID>");
    Intent intent = new Intent(Intent.ACTION_VIEW, uber);
    startActivity(intent);

//    PackageManager manager = getContext().getPackageManager()


  }

  /**
   * Emergency.
   */
  protected void emergency(){
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:911"));
    startActivity(intent);
  }


  /**
   * The type Phone unlocked receiver.
   */
  public static class PhoneUnlockedReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
      KeyguardManager keyguardManager = (KeyguardManager)context.
          getSystemService(Context.KEYGUARD_SERVICE);
      if (keyguardManager.isKeyguardSecure()) {
        long now = System.currentTimeMillis();

        if(now - MainActivity.getLastLockedTime()<60000){
          devicePolicyManager.lockNow();
        }

      }

    }
  }

  /**
   * The interface Question loader.
   */
  interface QuestionLoader{

    /**
     * Reload question.
     */
    void reloadQuestion();
  }


}
