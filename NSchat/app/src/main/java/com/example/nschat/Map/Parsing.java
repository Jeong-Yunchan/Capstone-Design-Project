package com.example.nschat.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nschat.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Parsing extends AppCompatActivity {
    final String TAG = "Parsing";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parsing);
        Log.d(TAG, "onCreate");

        new Thread(new Runnable() { //쓰레드 함수
            @Override
            public void run() {
                ArrayList<Park> parks = xml_parse();  //파싱 시작
                Intent intent = new Intent(Parsing.this, MapsActivity.class); //지도화면 띄우기
                intent.putExtra("park", parks); // parks ArrayList 넘기기
                startActivity(intent);
            }
        }).start(); //쓰레드 시작
    }

    /**
     * xml_parse()는 raw폴더에 있는 park_gj를 파싱하는 함수
     */
    private ArrayList<Park> xml_parse() {
        ArrayList<Park> parksList = new ArrayList<Park>(); //ArrayList 선언
        InputStream inputStream = getResources().openRawResource(R.raw.park_gj); //raw폴더에 있는 park_gj읽음
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        XmlPullParserFactory xmlPullParserFactory = null;
        XmlPullParser xmlPullParser = null;

        try {
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(reader);

            Park park = null; //Park클래스를 상속받은 park 선언 및 초기화
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){ //문서가 끝날때 까지 실행
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG, "xml START");
                        break;
                    case XmlPullParser.START_TAG:
                        String startTag = xmlPullParser.getName();
                        Log.i(TAG, "Start TAG :" + startTag);
                        if(startTag.equals("record")) { //시작 태그가 record면 park 추가
                            park = new Park();
                            Log.d(TAG, "park 추가");
                        }
                        else if(startTag.equals("관리번호")) { //태그 이름이 관리번호면 park에 관리번호 저장
                            park.setNumber(xmlPullParser.nextText());
                            Log.d(TAG, park.getNumber());
                            Log.d(TAG, "park 관리번호");
                        }
                        else if(startTag.equals("공원명")) { //태그 이름이 공원명이면 park에 공원명 저장
                            park.setPname(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("소재지지번주소")) { //태그 이름이 소재지지번주소이면 park에 소재지지번주소 저장
                            park.setPadr(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("공원구분")) { //태그 이름이 공원구분이면 park에 공원구분 저장
                            park.setPkind(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("공원면적")) { //태그 이름이 공원면적이면 park에 공원면적 저장
                            park.setPsize(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("위도")) { //태그 이름이 위도이면 park에 위도 저장
                            park.setLat(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("경도")) { //태그 이름이 경도이면 park에 경도 저장
                            park.setLng(xmlPullParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = xmlPullParser.getName();
                        Log.i(TAG,"End TAG : "+ endTag);
                        if (endTag.equals("record")) { // End 태그로 record가 나오면 parkList에 park 추가
                            parksList.add(park);
                        }
                        break;
                }
                try {
                    eventType = xmlPullParser.next();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                // 더이상 읽을것이 없으면 종료
                if(reader !=null) reader.close();
                if(inputStreamReader !=null) inputStreamReader.close();
                if(inputStream !=null) inputStream.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return parksList; // praksList 리턴
    }
}
