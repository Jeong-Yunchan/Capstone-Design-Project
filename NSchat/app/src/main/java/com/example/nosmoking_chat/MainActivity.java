package com.example.nosmoking_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


/*
SharedPreferences pref = getSharedPreferences("chekFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst",false);
        if(checkFirst == false) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("checkFirst",true);
        editor.commit();

        Intent intent = new Intent(MainActivity.this, First_Like.class);
        startActivity(intent);
        finish();
        }

SharedPreferences prefs = getSharedPreferences("Pref", MODE_PRIVATE);

public void checkFirstRun(){
        boolean isFirstRun = prefs.getBoolean("isFirstRun",true);
        if(isFirstRun)
        {
        Intent newIntent = new Intent(MainActivity.this, First_Like.class);
        startActivity(newIntent);

        prefs.edit().putBoolean("isFirstRun",false).apply();
        }
        }


       */




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