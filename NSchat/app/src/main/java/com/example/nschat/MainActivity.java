package com.example.nschat;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.nschat.Chatting.ChattingActivity;
import com.example.nschat.Map.Parsing;

public class MainActivity extends AppCompatActivity {

    private TextView NStimeView, SaveMoneyView;
    Button successBtn, failureBtn;
    ImageButton parkBtn, chatBtn, calBtn;
    String cigar;
    long StartTime, NowTime;
    int cigarette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 최초 실행 여부를 판단 ->>>
        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst", false);

        // false일 경우 최초 실행
        if (!checkFirst) {
            // 앱 최초 실행시 하고 싶은 작업
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst", true);
            editor.apply();
            finish();

            Intent intent = new Intent(MainActivity.this, First_Like.class);
            startActivity(intent);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        successBtn = findViewById(R.id.successBtn);
        failureBtn = findViewById(R.id.failureBtn);
        parkBtn = findViewById(R.id.parkBtn);
        chatBtn = findViewById(R.id.chatBtn);
        calBtn = findViewById(R.id.calBtn);
        NStimeView = (TextView) findViewById(R.id.NStimeView);
        SaveMoneyView = (TextView) findViewById(R.id.SaveMoneyView);

        NowTime = System.currentTimeMillis(); //밀리세컨드로 현재시간 받아옴

        SharedPreferences sharedPreferences = getSharedPreferences("NS", MODE_PRIVATE);
        cigar = sharedPreferences.getString("cigar", "0");
        StartTime = sharedPreferences.getLong("time", 0);

        cigarette = Integer.parseInt(cigar);

        /**
         * 절약한돈 계산
         */
        long passtime = (NowTime - StartTime) / 1000;//지난 시간을 초로 변환
        long savemoney = cigarette * 255 * passtime / 86400; // 절약한돈
        SaveMoneyView.setText(savemoney + "원 절약하셨어요!"); //Text 변경

        /**
         * 금연 시간 계산
         */
        long ss = passtime % 60;
        long mm = passtime / 60 % 60;
        long HH = passtime / 60 / 60 % 24;
        long dd = passtime / 60 / 60 / 24 % 30;
        long MM = passtime / 60 / 60 / 24 / 30 % 12;
        long yy = passtime / 60 / 60 / 24 / 30 / 12;

        if (yy > 0) NStimeView.setText(yy + "년 " + MM + "월 " + dd + "일 " + HH + "시간 " + mm + "분 " + ss + "초");
        else if (MM > 0) NStimeView.setText(MM + "월 " + dd + "일 " + HH + "시간 " + mm + "분 " + ss + "초");
        else if (dd > 0) NStimeView.setText(dd + "일 " + HH + "시간 " + mm + "분 " + ss + "초!!");
        else if (HH > 0) NStimeView.setText(HH + "시간 " + mm + "분 " + ss + "초!! 하루가 얼마남지 않았어요!");
        else if (mm > 0) NStimeView.setText(mm + "분 " + ss + "초!! 1시간 까지 파이팅!!");
        else NStimeView.setText(ss + "초!! 시작이 반입니다. 힘내세요!!");

        parkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Parsing.class);
                startActivity(intent);
            }
        });
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        successBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        failureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}