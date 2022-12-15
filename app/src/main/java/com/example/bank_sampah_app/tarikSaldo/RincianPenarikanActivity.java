package com.example.bank_sampah_app.tarikSaldo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bank_sampah_app.R;

public class RincianPenarikanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_penarikan);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Tarik Saldo");


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}