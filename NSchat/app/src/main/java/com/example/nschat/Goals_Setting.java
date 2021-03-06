package com.example.nschat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Goals_Setting extends AppCompatActivity {

    EditText goalText;
    String goal;

    long now = System.currentTimeMillis(); //밀리세컨드 단위로 시간을 얻어옴
    long Goal_time = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_setting);
        goalText = findViewById(R.id.goals);

        Button imageButton = (Button) findViewById(R.id.goals_count);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(goalText.getText().toString().equals("0") || goalText.getText().toString().equals("")){
                    Toast.makeText(Goals_Setting.this, "1~999까지 가능합니다.", Toast.LENGTH_SHORT).show();
                }else {

                    SharedPreferences sharedPreferences = getSharedPreferences("NS", MODE_PRIVATE);    // NS 이름의 기본모드 설정
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                    editor.putString("goal", goalText.getText().toString()); // key,value 형식으로 저장
                    editor.putLong("time", now);
                    editor.putLong("goal_time",Goal_time );
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                    goal = sharedPreferences.getString("goal", "0");


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

