package com.example.nschat;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;
//챗봇 응답 인터페이스
public interface BotReply {

    void callback(DetectIntentResponse returnResponse);
}
