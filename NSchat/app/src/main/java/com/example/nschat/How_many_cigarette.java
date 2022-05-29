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


public class How_many_cigarette extends AppCompatActivity {

    EditText cigarText;
    String cigarette;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_many_cigarette);
        cigarText = findViewById(R.id.cigar);

        Button imageButton = (Button) findViewById(R.id.cigar_count);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cigarText.getText().toString().equals("0") || cigarText.getText().toString().equals("")){
                    Toast.makeText(How_many_cigarette.this, "1~99까지 가능합니다.", Toast.LENGTH_SHORT).show();
                }else {

                    SharedPreferences sharedPreferences = getSharedPreferences("NS", MODE_PRIVATE);    // NS 이름의 기본모드 설정
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                    editor.putString("cigar", cigarText.getText().toString()); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                    cigarette = sharedPreferences.getString("cigar", "0");

                    Log.d("MSG", String.valueOf(cigarette));
                    Intent intent = new Intent(getApplicationContext(), Goals_Setting.class);
                    startActivity(intent);
                }
            }
        });
    }
}
