package com.example.nilo.pokecruiter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.Random;

/**
 * Created by fahrankamili on 9/27/15.
 */
public class ActualSplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actual_splash);
        final RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.mainContainer);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainContainer.setVisibility(View.GONE);
                Intent i = new Intent(ActualSplashActivity.this, SplashActivity.class);
                startActivity(i);
            }
        }, 25000);

    }

    Animation.AnimationListener animationSlideInLeftListener =
            new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            };
}
