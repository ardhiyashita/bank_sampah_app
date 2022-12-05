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

import com.example.bank_sampah_app.R;

public class FormSetorSampahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView add;
    String positionToShowToSpinner;
    EditText beratJenisSampah;
    Spinner jenisSampahSpinner;
    Button lanjutFormSampahButton;
    TextView jenisPenyetoranTv;
    String[] jenisPenyetoranSampahString;
    ArrayAdapter<String> jenisPenyetoranAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_setor_sampah);

        //init
        beratJenisSampah = findViewById(R.id.beratJenisSampah);
        add = findViewById(R.id.add);
        jenisPenyetoranTv = findViewById(R.id.jenisPenyetoranTv);

        //ambil data intent jenis setor dan set text
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("jenisSampahValue");

        jenisPenyetoranTv.setText(text);

        //spinner dan adapter pilihan jenis penyetoran
        jenisSampahSpinner = findViewById(R.id.jenisSampahSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenisSampah, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisSampahSpinner.setAdapter(adapter);

        //button lanjut
        lanjutFormSampahButton = findViewById(R.id.lanjutFormSampahButton);
        lanjutFormSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlanjutformsampahsetor = new Intent(FormSetorSampahActivity.this, RincianSetorSampahActivity.class);
                startActivity(intentlanjutformsampahsetor);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        switch (position) {
//            case 0:
//                Toast.makeText(parent.getContext(), "Spinner item 1!", Toast.LENGTH_SHORT).show();
//                break;
//            case 1:
//                Toast.makeText(parent.getContext(), "Spinner item 2!", Toast.LENGTH_SHORT).show();
//                break;
//            case 2:
//                Toast.makeText(parent.getContext(), "Spinner item 3!", Toast.LENGTH_SHORT).show();
//                break;
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}