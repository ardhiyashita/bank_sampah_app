package com.example.bank_sampah_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bank_sampah_app.authentication.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button btnRegister, btnLogin;
    LinearLayout linearWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        //next activity
//        if (restorePreData()){
//            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(mainActivity);
//            finish();
//        }
        setContentView(R.layout.activity_welcome);

        //Views
//        btnRegister= findViewById(R.id.btn_welcome_daftar);
        btnLogin= findViewById(R.id.btn_welcome_login);
        linearWelcome = findViewById(R.id.linear_welcome);


        // Button Daftar
//        btnRegister.setOnClickListener(view -> {
//            Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
//            startActivity(registerActivity);
//
//            finish();
//        });

        // Button Login
        btnLogin.setOnClickListener(view -> {
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);

            finish();
        });
    }

//    private boolean restorePreData(){
//        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//
//        return preferences.getBoolean("isWelcomeOpened",false);
//    }
//
//    private void savePrefsData(){
//        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = preferences.edit();
//
//        editor.putBoolean("isWelcomeOpened",true);
//        editor.apply();
//    }
}