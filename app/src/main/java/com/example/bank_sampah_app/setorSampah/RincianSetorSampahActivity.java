package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.tarikSaldo.RincianPenarikanActivity;
import com.example.bank_sampah_app.tarikSaldo.TarikSaldoActivity;

public class RincianSetorSampahActivity extends AppCompatActivity {

    Button kirimSetorSampahButton, unggahbutton;
    TextView tipePengambilanTv, totalBeratRincianTv;
    int valueTotalBerat;
    ImageView fotoSampahImg;
    private static final int requestcamera_code= 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_setor_sampah);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Rincian Setor Sampah");


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //init
        tipePengambilanTv = findViewById(R.id.tipePengambilanTv);
        totalBeratRincianTv = findViewById(R.id.totalBeratRincianTv);
        unggahbutton = findViewById(R.id.unggahbutton);
        fotoSampahImg = findViewById(R.id.fotoSampahImg);

        //get data tipe pengambilan
        Intent intent = getIntent();
        String jenisSetorValue = intent.getStringExtra("tipePengambilanValue");
        tipePengambilanTv.setText(jenisSetorValue);

        //get data berat total sampah
        Intent intent2 = getIntent();
        valueTotalBerat = intent2.getIntExtra("totalBeratValue",1);
        totalBeratRincianTv.setText(Integer.toString(valueTotalBerat));

        //button unggah foto
        unggahbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,requestcamera_code);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==requestcamera_code){
            Bitmap imageBitmap=(Bitmap) data.getExtras().get("data");
            fotoSampahImg.setImageBitmap(imageBitmap);
        }
    }
}