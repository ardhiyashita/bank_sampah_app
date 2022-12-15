package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.RealPathUtil;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RincianSetorSampahActivity extends AppCompatActivity {

    Button kirimSetorSampahButton, unggahbutton;
    int admin_id = 0;
    TextView tipePengambilanTv, totalBeratRincianTv;
    int valueTotalBerat;
    private ApiClient apiClient;
    private SessionManager sessionManager;
    ImageView fotoSampahImg;
    EditText catatanSampahEt;
//    private static final int requestcamera_code= 12;

//    ActivityRincianSetorSampahBinding setorSampahBinding;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_setor_sampah);
//        setContentView(setorSampahBinding.getRoot());

//        setorSampahBinding = DataBindingUtil.setContentView(this, R.layout.activity_rincian_setor_sampah);
//        setorSampahBinding = ActivityRincianSetorSampahBinding.inflate(getLayoutInflater());
        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);
        User user = sessionManager.fetchUser();

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Rincian Setor Sampah");


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //init
        tipePengambilanTv = findViewById(R.id.tipePengambilanTv);
        totalBeratRincianTv = findViewById(R.id.totalBeratRincianTv);
        unggahbutton = findViewById(R.id.unggahbutton);
        fotoSampahImg = findViewById(R.id.fotoSampahImg);
        catatanSampahEt = findViewById(R.id.catatanSampahEt);

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
//                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(camera,requestcamera_code);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED) {
                    Intent intentCam = new Intent();
                    intentCam.setType("image/*");
                    intentCam.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intentCam, 10);
                }else {
                    ActivityCompat.requestPermissions(RincianSetorSampahActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        });

        //button kirim setor sampah di rincian
        kirimSetorSampahButton = findViewById(R.id.kirimSetorSampahButton);
        kirimSetorSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengajuan();
//                Intent intentkirimsampah = new Intent(RincianSetorSampahActivity.this, SelesaiSetorSampahActivity.class);
//                startActivity(intentkirimsampah);
            }
        });
    }


    public void pengajuan() {
        User user = sessionManager.fetchUser();
        PengajuanRequest pengajuanRequest = new PengajuanRequest();
        pengajuanRequest.setUser_id(user.getId_user());
        pengajuanRequest.setTipe_pengambilan(tipePengambilanTv.getText().toString());
        pengajuanRequest.setCatatan_sampah(catatanSampahEt.getText().toString());
        pengajuanRequest.setBerat(totalBeratRincianTv.getText().toString());
//        pengajuanRequest.setAdmin_id(admin_id);
//        pengajuanRequest.setFoto_sampah(path.getText.toString());

        Call<PengajuanResponse> pengajuanResponseCall = apiClient.getApiService(this).userPengajuan(pengajuanRequest);
        pengajuanResponseCall.enqueue(new Callback<PengajuanResponse>() {
            @Override
            public void onResponse(Call<PengajuanResponse> call, Response<PengajuanResponse> response) {
                PengajuanResponse pengajuanResponse = response.body();
                if (pengajuanResponse.getSuccess()==true) {
                    Toast.makeText(RincianSetorSampahActivity.this, "Data Berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    Intent intentkirimsampah = new Intent(RincianSetorSampahActivity.this, SelesaiSetorSampahActivity.class);
                    startActivity(intentkirimsampah);
                } else {
                    Toast.makeText(RincianSetorSampahActivity.this, "Data Tidak Berhasil Terkirim", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PengajuanResponse> call, Throwable t) {
                Toast.makeText(RincianSetorSampahActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==requestcamera_code){
//            Bitmap imageBitmap=(Bitmap) data.getExtras().get("data");
//            fotoSampahImg.setImageBitmap(imageBitmap);
//        }
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Context context = RincianSetorSampahActivity.this;
            path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            fotoSampahImg.setImageBitmap(bitmap);
        }
    }
}