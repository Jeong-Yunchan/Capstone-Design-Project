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
import androidx.appcompat.app.AppCompatDelegate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class How_many_cigarette extends AppCompatActivity {

    EditText cigarText;
    String cigarette;

    long now = System.currentTimeMillis(); //밀리세컨드 단위로 시간을 얻어옴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_many_cigarette);
        cigarText = findViewById(R.id.cigar);

        Button imageButton = (Button) findViewById(R.id.cigar_count);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences= getSharedPreferences("NS",MODE_PRIVATE);    // NS 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("cigar",cigarText.getText().toString()); // key,value 형식으로 저장
                editor.putLong("time",now);
                editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                cigarette = sharedPreferences.getString("cigar","0");

                Log.d("MSG", String.valueOf(cigarette));
                Log.d("Time", String.valueOf(now));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
