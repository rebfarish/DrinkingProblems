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
 * Controller for question fragments.
 */
public abstract class QuestionsFragment extends Fragment {


  /**
   * The constant incorrect is number of incorrectly answered questions.
   */
  public static int incorrect = 0;
  /**
   * The Question and answers POJO gets a question and possible answers for that question.
   */
  protected QuestionAndAnswers questionAndAnswers;
  /**
   * devicePolicyManager manages policies enforced.
   */
  protected static DevicePolicyManager devicePolicyManager;
  /**
   * compName is the identifier for the BroadcastReceiver PhoneUnlockedReceiver.
   */
  protected ComponentName compName;


  /**
   * Takes in boolean isCorrect. If correct closes app, if incorrect locks screen.
   *
   * @param isCorrect boolean true for correct answer, false for incorrect.
   */
  public void handleAnswer(boolean isCorrect){
    if(isCorrect){
      getActivity().finish();
    }else {
      boolean active = devicePolicyManager.isAdminActive(compName);
      if (active) {
        MainActivity.resetLastLockedTime();
        devicePolicyManager.lockNow();
        incorrect = ((MainActivity) getActivity()).getFromSharedPrefs() +1;
        ((MainActivity) getActivity()).saveToSharedPrefs(incorrect);
        ((QuestionLoader) getActivity()).reloadQuestion();

      }
    }
  }

  /**
   * Checks if answer is correct then passes to handleAnswer.
   *
   * @param i answer selected.
   */
  protected void checkAnswer(int i){
    if (questionAndAnswers.getAnswers().get(i).isCorrect()) {
      handleAnswer(true);
    }else{
      handleAnswer(false);
    }

  }


  /**
   * Will load Uber app if installed, if not will open link to app store to install it.
   */
  protected void callCab(){
    //TODO check if phone has Uber installed, give other cab options
    Uri uber = Uri.parse("https://m.uber.com/ul/?client_id=<CLIENT_ID>");
    Intent intent = new Intent(Intent.ACTION_VIEW, uber);
    startActivity(intent);

//    PackageManager manager = getContext().getPackageManager()


  }

  /**
   * Opens dialer with 911 dialed.
   */
  protected void emergency(){
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:911"));
    startActivity(intent);
  }


  /**
   * Reciever that locks screen when password is correctly entered.
   */
  public static class PhoneUnlockedReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
      KeyguardManager keyguardManager = (KeyguardManager)context.
          getSystemService(Context.KEYGUARD_SERVICE);
      if (keyguardManager.isKeyguardSecure()) {
        long now = System.currentTimeMillis();

        //Sets the duration of time that the screen will be locked.
        if(now - MainActivity.getLastLockedTime()<10000){
          devicePolicyManager.lockNow();
        }

      }

    }
  }

  /**
   * The interface Question loader loads the next question.
   */
  interface QuestionLoader{

    /**
     * Reload question loads the next question.
     */
    void reloadQuestion();
  }


}
