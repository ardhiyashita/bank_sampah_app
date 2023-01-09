package com.bps.pesanpede.setorSampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bps.pesanpede.MainActivity;
import com.bps.pesanpede.R;

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