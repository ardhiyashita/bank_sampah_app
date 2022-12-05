package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bank_sampah_app.R;

public class SetorSampahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner jenisPenyotaranSpinner;
    Button lanjutButton;
    String[] jenisPenyetoranSampahString;
    ArrayAdapter<String> jenisPenyetoranAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_sampah);

        //spinner dan adapter pilihan jenis penyetoran
        jenisPenyotaranSpinner = findViewById(R.id.jenisPenyetoranSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenisPenyetoranSampahString, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisPenyotaranSpinner.setAdapter(adapter);

        //button lanjut
        lanjutButton = findViewById(R.id.lanjutButton);
        lanjutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlanjutsetor = new Intent(SetorSampahActivity.this, FormSetorSampahActivity.class);
                startActivity(intentlanjutsetor);
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}