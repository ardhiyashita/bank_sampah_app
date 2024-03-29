package com.bps.pesanpede;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.bps.pesanpede.onboarding.OnBoardingActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        final Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                    startActivity(intent);
                    finish();
            }
        },3000);
    }
}