package com.example.nilo.pokecruiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvQuestion.setMovementMethod(new ScrollingMovementMethod());

    }



}
