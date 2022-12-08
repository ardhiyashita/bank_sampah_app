package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.bank_sampah_app.R;

public class FormSetorSampahActivity extends AppCompatActivity {

    ImageView add;
    EditText beratJenisSampah;
    Spinner jenisSampahSpinner;
    Button lanjutFormSampahButton;
    String[] jenisPenyetoranSampahString;
    ArrayAdapter<String> jenisPenyetoranAdapter;
    TextView jenisPenyetoranTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_setor_sampah);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Setor Sampah");


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //init
        jenisPenyetoranTv = findViewById(R.id.jenisPenyetoranTv);

        //get data jenis setor
        Intent intent = getIntent();
        String jenisSetorValue = intent.getStringExtra("jenisSampahValue");
        jenisPenyetoranTv.setText(jenisSetorValue);

        //button lanjut
//        lanjutFormSampahButton = findViewById(R.id.lanjutFormButton);
        lanjutFormSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlanjutformsampahsetor = new Intent(FormSetorSampahActivity.this, RincianSetorSampahActivity.class);
                startActivity(intentlanjutformsampahsetor);
            }
        });
    }
}