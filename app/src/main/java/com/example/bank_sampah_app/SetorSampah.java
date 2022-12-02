package com.example.bank_sampah_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SetorSampah extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner jenisPenyotaranSpinner;
    String[] jenisPenyetoranSampahString;
    ArrayAdapter<String> jenisPenyetoranAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_sampah);

        //spinner pilihan jenis penyetoran
        jenisPenyotaranSpinner = findViewById(R.id.jenisPenyetoranSpinner);

        //adapter spinner
        jenisPenyetoranAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,jenisPenyetoranSampahString);
        jenisPenyotaranSpinner.setAdapter(jenisPenyetoranAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.jenisPenyetoranSpinner)
        {
            //
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}