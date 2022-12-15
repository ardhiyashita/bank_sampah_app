package com.example.bank_sampah_app.tarikSaldo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.bank_sampah_app.R;

import android.view.View;

public class TarikSaldoActivity extends AppCompatActivity {

    Button lanjutTarikSaldoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik_saldo);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Tarik Saldo");


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //button lanjut tarik saldo
        lanjutTarikSaldoButton = findViewById(R.id.lanjutTarikSaldoButton);
        lanjutTarikSaldoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlanjutnarik = new Intent(TarikSaldoActivity.this, RincianPenarikanActivity.class);
                startActivity(intentlanjutnarik);
            }
        });
    }
}