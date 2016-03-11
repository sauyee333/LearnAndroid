package com.android.learn.learnandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.android.learn.learnandroid.R;

//Reference: http://code.tutsplus.com/tutorials/an-introduction-to-android-transitions--cms-21640
//By Sue Smith: http://tutsplus.com/authors/sue-smith

public class TransitionActivity extends Activity {

  private Scene scene1, scene2;
  // private Transition transition;
  private boolean start;
  private TransitionSet transition;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.transition_start);

    RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);
    ViewGroup startViews = (ViewGroup) getLayoutInflater().inflate(
        R.layout.transition_start, baseLayout, false);
    ViewGroup endViews = (ViewGroup) getLayoutInflater().inflate(
        R.layout.transition_end, baseLayout, false);

    scene1 = new Scene(baseLayout, startViews);
    scene2 = new Scene(baseLayout, endViews);

    transition = new TransitionSet();
    transition.setDuration(2000);
    transition.addTransition(new ChangeBounds());
    transition.setInterpolator(new AccelerateDecelerateInterpolator());
    // transition = new AutoTransition();
    // transition.setDuration(5000);
    // transition.setInterpolator(new AccelerateDecelerateInterpolator());

    start = true;
  }

  public void changeScene(View v) {
    if (start) {
      TransitionManager.go(scene2, transition);
      start = false;
    } else {
      TransitionManager.go(scene1, transition);
      start = true;
    }
  }
}
