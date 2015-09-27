package com.example.nilo.pokecruiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageView) findViewById(R.id.player);

        slideRight(player);

    }

    private void slideRight(View view) {
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        Animation animationSlideInLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        animationSlideInLeft.setDuration(1000);
        animationSlideInLeft.setAnimationListener(animationSlideInLeftListener);

        view.startAnimation(animationSlideInLeft);
        view.setVisibility(View.VISIBLE);

//        final Animation posX = new TranslateAnimation(0,width-50,0,0);
//        posX.setDuration(500);
//        posX.setFillAfter(true);
//
//        view.startAnimation(posX);
//        view.setVisibility(View.VISIBLE);
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
