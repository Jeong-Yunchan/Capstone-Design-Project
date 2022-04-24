package com.example.nschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    public SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 최초 실행 여부를 판단 ->>>
        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst", false);

        // false일 경우 최초 실행
        if(!checkFirst){
            // 앱 최초 실행시 하고 싶은 작업
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst",true);
            editor.apply();
            finish();

            Intent intent = new Intent(MainActivity.this, First_Like.class);
            startActivity(intent);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}