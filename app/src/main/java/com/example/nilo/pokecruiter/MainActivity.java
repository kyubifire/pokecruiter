package com.example.nilo.pokecruiter;

import android.content.res.AssetManager;
import android.os.Handler;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView player;
    private ImageView enemy;
    private JSONObject jsonObject;
    private JSONArray companyNames;
    private Iterator<?> questions;
    private TextView tvQuestion;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView company_name;
    private ImageView userhpfull;

    private String correctAnswer;
    private int currentHealth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentHealth = 100;

        player = (ImageView) findViewById(R.id.player);
        enemy = (ImageView) findViewById(R.id.enemy);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        userhpfull = (ImageView) findViewById(R.id.userhpfull);

        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        answer3 = (TextView) findViewById(R.id.answer3);
        answer4 = (TextView) findViewById(R.id.answer4);

        company_name = (TextView) findViewById(R.id.company_name);
        slideRight(player);
        slideRight(enemy);

        try {
            readCompanyNames();
            Random rand = new Random();
            int randomnum = rand.nextInt((companyNames.length() + 1)) ;
            company_name.setText((String) companyNames.get(randomnum));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    private void readCompanyNames() throws IOException {
        AssetManager am = getAssets();
        InputStream is = am.open("CompanyNames.json");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = bReader.readLine())!=null){
            builder.append(line);
        }

        try {
            companyNames = new JSONArray(builder.toString());
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

    public void checkAnswer(View v) throws IOException, JSONException {
        TextView tv = (TextView) v;
        if(tv.getText().equals(correctAnswer)){
            showSuperEffective();
//            resetState();
//            showQuestionAndAnswer();
        }else{
            currentHealth -= 50;
            if(currentHealth > 0){
                userhpfull.setImageResource(R.drawable.hp_half);
                showNotEffective(false);
            }else{
                userhpfull.setImageResource(R.drawable.hp_empty);
                showNotEffective(true);
            }
        }
    }

    private void showRightAnswerOnly() {
        check(answer1);
        check(answer2);
        check(answer3);
        check(answer4);
    }

    private void check(TextView answer) {
        if(!answer.getText().equals(correctAnswer)){
            answer.setVisibility(View.INVISIBLE);
        }
    }

    private void showNotEffective(final boolean isHealthZero) {
        final String tempAnswer1 = answer1.getText().toString();
        final String tempAnswer2 = answer2.getText().toString();
        final String tempAnswer3 = answer3.getText().toString();
        final String tempAnswer4 = answer4.getText().toString();

        answer1.setText("Your answer was not very effective.");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                answer1.setText(tempAnswer1);
                answer2.setText(tempAnswer2);
                answer3.setText(tempAnswer3);
                answer4.setText(tempAnswer4);

                if(isHealthZero){
                    showRightAnswerOnly();
                }
            }
        }, 2000);
    }

    private void showSuperEffective() {
        final String tempAnswer1 = answer1.getText().toString();
        final String tempAnswer2 = answer2.getText().toString();
        final String tempAnswer3 = answer3.getText().toString();
        final String tempAnswer4 = answer4.getText().toString();

        answer1.setText("Your answer was super effective.");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                answer1.setText(tempAnswer1);
                answer2.setText(tempAnswer2);
                answer3.setText(tempAnswer3);
                answer4.setText(tempAnswer4);
            }
        }, 2000);
    }

    private void resetState() {
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");
    }



    private void showQuestionAndAnswer() throws IOException, JSONException {
        if(questions.hasNext()){
            String question = (String) questions.next();
            JSONArray possibleAnswers = jsonObject.getJSONArray(question);

            tvQuestion.setText(question);

            for(int i = 0 ;i<possibleAnswers.length()-1;i++){
                switch (i){
                    case 0:
                        answer1.setText((String) possibleAnswers.get(i));
                        break;
                    case 1:
                        answer2.setText((String) possibleAnswers.get(i));
                        break;
                    case 2:
                        answer3.setText((String) possibleAnswers.get(i));
                        break;
                    case 3:
                        answer4.setText((String) possibleAnswers.get(i));
                        break;
                }
            }

            correctAnswer = (String) possibleAnswers.get(possibleAnswers.length()-1);
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
