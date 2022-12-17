package com.example.bank_sampah_app.tarikSaldo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bank_sampah_app.MainActivity;
import com.example.bank_sampah_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RincianPenarikanActivity extends AppCompatActivity {
    TextView tvUang, tvRincianUang, tvId, tvTanggal;
    Button btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_penarikan);

        tvId = findViewById(R.id.id_transaksi);
        tvUang = findViewById(R.id.jumlah_penarikan);
        tvRincianUang = findViewById(R.id.rincian_jumlah_penarikan);
        tvTanggal = findViewById(R.id.tanggal_dibuat);

        Intent in= getIntent();
        Bundle b = in.getExtras();

        int id =(int) b.get("id");
        int uang =(int) b.get("uang");

        tvId.setText(Integer.toString(id));
        tvUang.setText(Integer.toString(uang));
        tvRincianUang.setText(Integer.toString(uang));
        tvTanggal.setText(dateFormatter((String) b.get("tanggal")));

        btnSelesai = findViewById(R.id.btn_selesai_tarik);
        btnSelesai.setOnClickListener(view -> {
            Intent intent = new Intent(RincianPenarikanActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public String dateFormatter(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dates = null;
        String date1 = null;
        try {
            dates = sdf.parse(date);
            date1 = sdf.format(dates);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return date1;
    }
}