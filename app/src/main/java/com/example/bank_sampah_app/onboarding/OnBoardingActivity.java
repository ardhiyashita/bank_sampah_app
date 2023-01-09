package com.example.bank_sampah_app.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bank_sampah_app.MainActivity;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.SplashScreenActivity;
import com.example.bank_sampah_app.WelcomeActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager screenPager;
    OnBoardingViewPagerAdapter onBoardingViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext, btnStart;
    LinearLayout linearNext, linearStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        //next activity
        if (restorePreData()){
            Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_onboarding);

        //Views
        btnNext = findViewById(R.id.btn_next_onboarding);
        btnStart = findViewById(R.id.btn_start_onboarding);

        linearNext = findViewById(R.id.linear_next);
        linearStart = findViewById(R.id.linear_start);

        tabIndicator = findViewById(R.id.tab_indicator);

        final List<OnBoardingItem> mList = new ArrayList<>();
        mList.add(new OnBoardingItem("Sayangi Bumi Sedari Dini","Dengan memilah sampah sendiri, bisa membantu bumi menjadi lebih asri!", R.drawable.asset_onboarding1));
        mList.add(new OnBoardingItem("Lingkungan Bersih Bebas dari Sampah","Hidup sehat dengan lingkungan bersih dan terjaga, bebas dari sampah!", R.drawable.asset_onboarding2));
        mList.add(new OnBoardingItem("Dapatkan Cuan  dengan Cara Mudah","Setor sampahmu, dan dapatkan cuan dengan cara mudah!", R.drawable.asset_onboarding3));

        //ViewPager
        screenPager = findViewById(R.id.viewpager_onboarding);
        onBoardingViewPagerAdapter = new OnBoardingViewPagerAdapter(this,mList);
        screenPager.setAdapter(onBoardingViewPagerAdapter);

        //Tab Indicator
        tabIndicator.setupWithViewPager(screenPager);

        //Button next
        btnNext.setOnClickListener(view -> screenPager.setCurrentItem(screenPager.getCurrentItem()+1, true));

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Button Start
        btnStart.setOnClickListener(view -> {
            Intent welcomeActivity = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(welcomeActivity);

            savePrefsData();
            finish();
        });
    }

    private boolean restorePreData(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);

        return preferences.getBoolean("isOnboardingOpened",false);
    }

    private void savePrefsData(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOnboardingOpened",true);
        editor.apply();
    }

    private  void loadLastScreen(){
        linearNext.setVisibility(View.INVISIBLE);
        linearStart.setVisibility(View.VISIBLE);
    }
}