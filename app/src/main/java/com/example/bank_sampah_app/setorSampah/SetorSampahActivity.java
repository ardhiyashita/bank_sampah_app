package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bank_sampah_app.R;

public class SetorSampahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner tipePengambilanSpinner;
    EditText totalBeratEt, catatanSampahEt;
    Button lanjutSetorButton;
    String[] jenisPenyetoranSampahString;
    ArrayAdapter<String> jenisPenyetoranAdapter;
    int positionOfSelectedDataFromSpinner;
    String selectedTipePengambilan;
    int totalBeratValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_sampah);

        //init
        tipePengambilanSpinner = findViewById(R.id.tipePengambilanSpinner);
        totalBeratEt = findViewById(R.id.totalBeratEt);
        lanjutSetorButton = findViewById(R.id.lanjutSetorButton);
        catatanSampahEt = findViewById(R.id.catatanSampahEt);

        //spinner dan adapter pilihan tipe pengambilan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipePengambilanString, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipePengambilanSpinner.setAdapter(adapter);

        //ambil data spinner
        tipePengambilanSpinner.setOnItemSelectedListener(this);

        CharSequence textJenisSampah = (CharSequence) tipePengambilanSpinner.getSelectedItem().toString();


        //button lanjut

        lanjutSetorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate
                int value = Integer.valueOf(totalBeratEt.getText().toString());
                String totalBeratString = totalBeratEt.getText().toString();
                totalBeratValue = Integer.parseInt(totalBeratString);
                if(value>0){
                    Intent intentlanjutsetor = new Intent(SetorSampahActivity.this, RincianSetorSampahActivity.class);
                    intentlanjutsetor.putExtra("tipePengambilanValue", selectedTipePengambilan.toString());
                    intentlanjutsetor.putExtra("totalBeratValue", totalBeratValue);
                    startActivity(intentlanjutsetor);
                }else {
                    Toast.makeText(SetorSampahActivity.this, "Mohon Masukkan Total Berat Sampah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        selectedTipePengambilan = (String) parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}