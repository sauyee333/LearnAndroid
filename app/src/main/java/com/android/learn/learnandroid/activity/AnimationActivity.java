package com.android.learn.learnandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.learn.learnandroid.R;

//Reference: http://code.tutsplus.com/tutorials/android-sdk-creating-a-simple-tween-animation--mobile-14898
//By Sue Smith: http://tutsplus.com/authors/sue-smith

public class AnimationActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animate_sun);

    ImageView sun = (ImageView) findViewById(R.id.sun);
    Animation sunRise = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
    sun.startAnimation(sunRise);

    ImageView clock = (ImageView) findViewById(R.id.clock);
    Animation clockTurn = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
    clock.startAnimation(clockTurn);

    ImageView hour = (ImageView) findViewById(R.id.hour);
    Animation hourTurn = AnimationUtils.loadAnimation(this, R.anim.hour_turn);
    hour.startAnimation(hourTurn);
  }
}
