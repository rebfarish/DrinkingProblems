package com.enenby.drinkingproblems.controller;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.support.v4.app.Fragment;
import com.enenby.drinkingproblems.model.pojo.QuestionAndAnswers;
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;

public abstract class QuestionsFragment extends Fragment {

  protected QuestionAndAnswers questionAndAnswers;
  protected DevicePolicyManager devicePolicyManager;
  protected ComponentName compName;

  protected void checkAnswer(int i){
    if (questionAndAnswers.getAnswers().get(i).isCorrect()) {
      getActivity().finish();
    } else {
      boolean active = devicePolicyManager.isAdminActive(compName);
      if (active) {
        devicePolicyManager.lockNow();
        ((QuestionLoader) getActivity()).reloadQuestion();
      }
    }
  }

  interface QuestionLoader{
    void reloadQuestion();
  }

}
