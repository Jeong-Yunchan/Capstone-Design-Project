package com.example.nschat;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.example.nschat.Chatting.ChattingActivity;
import com.example.nschat.Map.Parsing;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {

    private TextView NStimeView, SaveMoneyView,HealthView;
    ImageButton parkBtn, chatBtn, retimeBtn, RefreshBtn, SettingBtn, helpBtn;
    String cigar, goal;
    long StartTime, NowTime, Goal_time, goalbar;
    int cigarette, goals;
    private static final String DEFAULT_PATTERN = " 목표 %d%%"; //프로그래서 바 패턴

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

        CircleProgressBar circleProgressBar;

        helpBtn = findViewById(R.id.helpButton);
        SettingBtn = findViewById(R.id.settingBtn);
        RefreshBtn = findViewById(R.id.Refresh);
        retimeBtn = findViewById(R.id.RetimeBtn);
        parkBtn = findViewById(R.id.parkBtn);
        chatBtn = findViewById(R.id.chatBtn);
        NStimeView = (TextView) findViewById(R.id.NStimeView);
        SaveMoneyView = (TextView) findViewById(R.id.SaveMoneyView);
        HealthView = (TextView) findViewById(R.id.HstatetView);

        NowTime = System.currentTimeMillis(); //밀리세컨드로 현재시간 받아옴

        SharedPreferences sharedPreferences = getSharedPreferences("NS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        cigar = sharedPreferences.getString("cigar", "0");
        StartTime = sharedPreferences.getLong("time", 0);
        Goal_time = sharedPreferences.getLong("goal_time",0);
        goal = sharedPreferences.getString("goal","0");
        long goaltime = (NowTime - Goal_time) / 1000;


        cigarette = Integer.parseInt(cigar);
        goals = Integer.parseInt(goal);

        TextSettings(); // 금연기간, 절약한 금액, 건강상태 설정

        goalbar = goaltime*100 / ((long) goals *24*60*60); // 목표 퍼센트 계산

        circleProgressBar=findViewById(R.id.cpb_circlebar);
        circleProgressBar.setProgress((int) goalbar);  // 목표 퍼센트 프로그래스바 적용
        editor.putLong("goal_persent", goalbar);
        editor.apply();
        /**
         * 목표가 달성되었다면 폭죽 애니메이션과 박수가 나옴
         */
        if (goalbar >= 100){
            Toast.makeText(MainActivity.this, "목표달성을 축하드립니다!!", Toast.LENGTH_LONG).show();

            final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);
            MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.pung); //효과음
            player.start();

        }


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
        retimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long resettime = System.currentTimeMillis();
                SharedPreferences sharedPreferences= getSharedPreferences("NS",MODE_PRIVATE);    // NS 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putLong("time",resettime);
                editor.putLong("goal_time",resettime);
                editor.apply();

                //액티비티 다시 시작
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        RefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        SettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
            }
        });
        helpBtn.setOnClickListener(new View.OnClickListener(){ //누르면 도움말 화면 실행
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void TextSettings() {
        /**
         * 절약한 돈 계산
         */
        long passtime = (NowTime - StartTime) / 1000;//지난 시간을 초로 변환
        long savemoney = cigarette * 255 * passtime / 86400; // 절약한돈
        SaveMoneyView.setText(savemoney + "원 절약하셨어요!\n(한갑당 4500원 20개비 기준)"); //Text 변경

        /**
         * 금연 시간 계산
         */
        long tt = passtime /60;
        long ss = passtime % 60;
        long mm = passtime / 60 % 60;
        long HH = passtime / 60 / 60 % 24;
        long dd = passtime / 60 / 60 / 24 % 30;
        long MM = passtime / 60 / 60 / 24 / 30 % 12;
        long yy = passtime / 60 / 60 / 24 / 30 / 12;

        if (yy > 0) NStimeView.setText(yy + "년 " + MM + "월 " + dd + "일 " + HH + "시간 " + mm + "분 " + ss + "초");
        else if (MM > 0) NStimeView.setText(MM + "월 " + dd + "일 " + HH + "시간 " + mm + "분 " + ss + "초");
        else if (dd > 0) NStimeView.setText(dd + "일 " + HH + "시간 " + mm + "분 " + ss + "초!!");
        else if (HH > 0) NStimeView.setText(HH + "시간 " + mm + "분 " + ss + "초!!");
        else if (mm > 0) NStimeView.setText(mm + "분 " + ss + "초!!  파이팅!!");
        else NStimeView.setText(ss + "초!! ");

        /**
         * 시간의 변화에 따른 건강 상태
         */
        if(tt >= 31104000) HealthView.setText("폐 질환과 암을 포함한 각종 질환의\n위험성이 큰 폭으로 감소했어요");
        else if (tt >= 23328000) HealthView.setText("드디어 관상동맥 심장질환의 발생\n가능성이 비흡연자와 같아졌어요");
        else if (tt >= 15552000) HealthView.setText("구강암, 인후암 또는 최장암이\n발생할 가능성이 크게 감소했어요");
        else if (tt >= 7776000) HealthView.setText("담배로 좁아진 혈관이 다시\n넓어져심혈관 질환 위혐이 감소했어요");
        else if (tt >= 1555200) HealthView.setText("폐의 섬모가 다시 자라나\n심장마비로 인한 위험이\n점반으로 줄었어요");
        else if (tt >= 1166400) HealthView.setText("이제 폐가 스스로 폐가\n스스로 치유되었어요");
        else if (tt >= 129600) HealthView.setText("폐기능이 향상되었고\n혈액 순환도 개선되었어요");
        else if (tt >= 4320) HealthView.setText("신체의 니코틴 수치가 감소하기\n시작했어요");
        else if (tt >= 2880) HealthView.setText("손상된 신경이 치유됨에 따라\n후각이 고조되고 미각을 생생하게\n느낄수 있게 되었습니다.");
        else if (tt >= 1440) HealthView.setText("이제 혈압이 떨어지고 고혈압으로\n인한 심장마비의 위험이\n감소하기 시작했어요");
        else if (tt >= 720) HealthView.setText("일산화탄소의 수치가 정상으로 돌아오고\n신체의 산소수치가 증가하고 있어요");
        else if (tt >= 20) HealthView.setText("심박수가 정상으로 돌아오고\n혈액순환이 개선되었어요");
        else HealthView.setText("금연 시작!");
    }

    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }
}