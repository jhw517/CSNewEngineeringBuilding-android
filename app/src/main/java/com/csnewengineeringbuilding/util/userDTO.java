package com.csnewengineeringbuilding.util;

import android.os.AsyncTask;

import com.csnewengineeringbuilding.singleton.User;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class userDTO extends AsyncTask<Void, Void, String> {


    public static String conn = "시간표", userId = "", userPw = "", userName = "";

    public String content ="";
    public Document doc, doc2, doc3;
    public boolean isConnected = false;
    public static int asCnt = 0, handinCnt = 0, realCnt = 0;

    private SFCallback preprocessCallback;
    private SFCallback connCallback;
    private SFCallback successCallback;
    private SFCallback failCallback;

    private static final int TIMEOUT = 15000;

    public userDTO(SFCallback preprocessCallback, SFCallback successCallback, SFCallback failCallback){
        this.preprocessCallback = preprocessCallback;
        this.successCallback = successCallback;
        this.failCallback = failCallback;
    }
    public userDTO(SFCallback successCallback){
        this.successCallback = successCallback;
    }

    public userDTO(SFCallback successCallback, SFCallback failCallback, SFCallback connCallback, String id, String pw){
        this.successCallback = successCallback;
        this.failCallback = failCallback;
        this.connCallback = connCallback;
        this.userId = id;
        this.userPw = pw;
    }

    @Override
    protected String doInBackground(Void... params) {
        isConnected = true;
        if (preprocessCallback != null){
            preprocessCallback.callback();
        }
        try {
            Connection.Response res = Jsoup.connect(Constants.URL_LOGIN)
                    .followRedirects(true)
                    .data("userDTO.userId", userId)
                    .data("userDTO.password", userPw)
                    .method(Connection.Method.POST)
                    .timeout(TIMEOUT)
                    .execute();
            doc = Jsoup.connect(Constants.URL_TABLE)
                    .followRedirects(true)
                    .cookies(res.cookies())
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .timeout(TIMEOUT)
                    .post();
            doc2 = Jsoup.connect(Constants.URL_ASSIGN)
                    .followRedirects(true)
                    .cookies(res.cookies())
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .timeout(TIMEOUT)
                    .post();
            doc2 = Jsoup.connect(Constants.URL_ASSIGN)
                    .followRedirects(true)
                    .cookies(res.cookies())
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .timeout(TIMEOUT)
                    .post();
            doc3 = Jsoup.connect(Constants.URL_HOME)
                    .followRedirects(true)
                    .cookies(res.cookies())
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .timeout(TIMEOUT)
                    .post();
            content = doc.toString();
        }catch(IOException e){ isConnected = false; }
        return content;
    }

    @Override
    protected void onPostExecute(String result) {
        if (isConnected){
            asCnt = 0;
            Element assign = doc2.select("TABLE[class = list-table]").first();
            if(assign == null) {
                connCallback.callback();
                return;
            }
            Element getUser = doc3.select("SPAN>strong").first();
            if(getUser == null) {
                connCallback.callback();
                return;
            }
            User.getInstance().setName(getUser.text().trim());
            User.getInstance().setStudentNumber(userId);
            /**
             * 로그인 성공시에 preference에 넣어야 햄.
             */
//            userName = getUser.text().trim();
            Element userConnection = doc.select("SPAN[class = selected]").first();
            if(userConnection == null) {
                connCallback.callback();
                return;
            }
            conn = userConnection.text();
            successCallback.callback();
        } else {
            failCallback.callback();
        }
    }
}