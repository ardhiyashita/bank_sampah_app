package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bank_sampah_app.HomeFragment;
import com.example.bank_sampah_app.MainActivity;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.tarikSaldo.RincianPenarikanActivity;
import com.example.bank_sampah_app.tarikSaldo.TarikSaldoActivity;

public class SelesaiSetorSampahActivity extends AppCompatActivity {

    Button selesaiSetorSampahButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_setor_sampah);

        //button selesai setor sampah
        selesaiSetorSampahButton = findViewById(R.id.selesaiSetorSampahButton);
        selesaiSetorSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentselesaisetor = new Intent(SelesaiSetorSampahActivity.this, MainActivity.class);
                startActivity(intentselesaisetor);
            }
        });
    }


}