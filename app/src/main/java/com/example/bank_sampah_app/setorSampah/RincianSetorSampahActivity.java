package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.tarikSaldo.RincianPenarikanActivity;
import com.example.bank_sampah_app.tarikSaldo.TarikSaldoActivity;

public class RincianSetorSampahActivity extends AppCompatActivity {

    Button kirimSetorSampahButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_setor_sampah);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Setor Sampah");


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //button kirim setor sampah di rincian
        kirimSetorSampahButton = findViewById(R.id.kirimSetorSampahButton);
        kirimSetorSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentkirimsampah = new Intent(RincianSetorSampahActivity.this, SelesaiSetorSampahActivity.class);
                startActivity(intentkirimsampah);
            }
        });
    }
}