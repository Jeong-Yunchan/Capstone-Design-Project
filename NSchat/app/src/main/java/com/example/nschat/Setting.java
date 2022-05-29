package com.example.nschat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Setting extends AppCompatActivity {

    EditText SettingGoalText;
    String Goal_set;
    TextView NowGoal;
    Button Goal_Reset_Btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        SettingGoalText = findViewById(R.id.setting_goals);
        NowGoal = (TextView) findViewById(R.id.nowGoal);
        Goal_Reset_Btn = findViewById(R.id.Goal_Reset_Btn);

        Button imageButton = (Button) findViewById(R.id.setsaveBtn);

        SharedPreferences sharedPreferences = getSharedPreferences("NS", MODE_PRIVATE);    // NS 이름의 기본모드 설정
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        Goal_set = sharedPreferences.getString("goal", "0");
        NowGoal.setText(Goal_set + " 일");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SettingGoalText.getText().toString().equals("0")){
                    Toast.makeText(Setting.this, "1~999일 까지 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(SettingGoalText.getText().toString().equals("")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {

                    editor.putString("goal", SettingGoalText.getText().toString()); // key,value 형식으로 저장
                    editor.apply();    //최종 커밋. 커밋을 해야 저장이 된다.

                    Log.d("MSG", String.valueOf(Goal_set));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        Goal_Reset_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long GoalTime = System.currentTimeMillis();

                editor.putLong("goal_time", GoalTime);
                editor.apply();

            }
        });
    }
}