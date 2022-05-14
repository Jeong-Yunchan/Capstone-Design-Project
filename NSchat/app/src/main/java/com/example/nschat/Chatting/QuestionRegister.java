package com.example.nschat.Chatting;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class QuestionRegister extends StringRequest{

    // 서버 URL 설정(php파일연동)
    final static private String URL = "http://192.168.0.16/DF_Question.php";
    private Map<String, String> map;

    public QuestionRegister(String message, Response.Listener<String>listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("question", message);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}