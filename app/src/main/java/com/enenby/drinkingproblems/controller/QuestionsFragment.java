package com.enenby.drinkingproblems.controller;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;

public abstract class QuestionsFragment extends Fragment {

  protected QuestionAndAnswers questionAndAnswers;
  protected static DevicePolicyManager devicePolicyManager;
  protected ComponentName compName;


  protected void checkAnswer(int i){
    if (questionAndAnswers.getAnswers().get(i).isCorrect()) {
      getActivity().finish();
    } else {
      boolean active = devicePolicyManager.isAdminActive(compName);
      if (active) {
        MainActivity.resetLastLockedTime();
        devicePolicyManager.lockNow();
        ((QuestionLoader) getActivity()).reloadQuestion();
      }
    }
  }

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

  interface QuestionLoader{
    void reloadQuestion();
  }

}
