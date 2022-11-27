package com.example.bank_sampah_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {
    Button btnStart;
    LinearLayout linearWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //next activity
        if (restorePreData()){
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_welcome);

        //Views
        btnStart = findViewById(R.id.btn_start);
        linearWelcome = findViewById(R.id.linear_welcome);


        // Button Start
        btnStart.setOnClickListener(view -> {
            Intent onboardingActivity = new Intent(getApplicationContext(), OnBoardingActivity.class);
            startActivity(onboardingActivity);

            savePrefsData();
            finish();
        });
    }

    private boolean restorePreData(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);

        return preferences.getBoolean("isWelcomeOpened",false);
    }

    private void savePrefsData(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("isWelcomeOpened",true);
        editor.apply();
    }
}