package com.example.nilo.pokecruiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by fahrankamili on 9/27/15.
 */
public class SplashActivity extends Activity {
    private EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        etName = (EditText) findViewById(R.id.etName);
    }

    public void pickedCharacter(View v){
        if(v.getId() == R.id.player_male){
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            i.putExtra("Gender","male");
            i.putExtra("username",etName.getText().toString());
            startActivity(i);
        }else if(v.getId() == R.id.player_female){
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            i.putExtra("Gender","female");
            i.putExtra("username",etName.getText().toString());
            startActivity(i);
        }


    }
}
