package com.example.nilo.pokecruiter;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ImageView player;
    private ImageView enemy;
    private JSONObject jsonObject;
    private Iterator<?> questions;
    private TextView tvQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageView) findViewById(R.id.player);
        enemy = (ImageView) findViewById(R.id.enemy);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        slideRight(player);

        slideRight(enemy);
        try {
            readQuestionAndAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            showQuestionAndAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void readQuestionAndAnswer()throws IOException  {
        AssetManager am = getAssets();
        InputStream is = am.open("datastructureQs.json");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = bReader.readLine())!=null){
            builder.append(line);
        }

        try {
            jsonObject = new JSONObject(builder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        questions = jsonObject.keys();
    }

    private void showQuestionAndAnswer() throws IOException, JSONException {
        if(questions.hasNext()){
            String question = (String) questions.next();
            JSONArray answers = jsonObject.getJSONArray(question);

            tvQuestion.setText(question);


        }
    }

    private void slideRight(View view) {
        Animation animationSlideInLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        animationSlideInLeft.setDuration(1000);
        animationSlideInLeft.setAnimationListener(animationSlideInLeftListener);

        view.startAnimation(animationSlideInLeft);
        view.setVisibility(View.VISIBLE);
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
