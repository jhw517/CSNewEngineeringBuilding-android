package com.csnewengineeringbuilding;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("CSNewEngineeringBuilding", MODE_PRIVATE);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.white)
//                .withHeaderText("Header")
//                .withFooterText("신건우, 최민재, 정해원, 남윤한")
                .withBeforeLogoText("CS 신공학관")
                .withLogo(R.drawable.dongguk_logo)
                .withAfterLogoText("신건우, 최민재, 정해원, 남윤한");

        boolean isLogin = prefs.getBoolean("isLogin", true);
        if (isLogin) {
            easySplashScreen = easySplashScreen.withTargetActivity(LoginActivity.class);
        } else {
            easySplashScreen = easySplashScreen.withTargetActivity(NewEngineeringBuildingUI.class);
        }

        easySplashScreen.getBeforeLogoTextView().setTextColor(Color.BLACK);
        easySplashScreen.getAfterLogoTextView().setTextColor(Color.BLACK);
        easySplashScreen.getBeforeLogoTextView().setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        View easySplashScreenView = easySplashScreen.create();
        setContentView(easySplashScreenView);
    }
}
