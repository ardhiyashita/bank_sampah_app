package com.example.bank_sampah_app.setorSampah;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetorSampahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner tipePengambilanSpinner;
    EditText totalBeratEt, catatanSampahEt;
    Button lanjutSetorButton, unggahButton;
    int admin_id = 0;
    String foto_sampah = null;
    private ApiClient apiClient;
    private SessionManager sessionManager;
    String selectedTipePengambilan;
    ImageView fotoSampahImg;
    String path;
    ProgressDialog pd;
    private static final int GALLERY_ADD_PROFILE = 1;
    private Bitmap bitmap = null;
    TextView fotoSampagTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_sampah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Setor Sampah");

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);
        User user = sessionManager.fetchUser();


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //init
        tipePengambilanSpinner = findViewById(R.id.tipePengambilanSpinner);
        totalBeratEt = findViewById(R.id.totalBeratEt);
        lanjutSetorButton = findViewById(R.id.lanjutSetorButton);
        catatanSampahEt = findViewById(R.id.catatanSampahEt);
        unggahButton = findViewById(R.id.unggahbutton);
        fotoSampahImg = findViewById(R.id.fotoSampahImg);
        fotoSampagTxt = findViewById(R.id.fotosampahtxt);

        //spinner dan adapter pilihan tipe pengambilan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipePengambilanString, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipePengambilanSpinner.setAdapter(adapter);

        //ambil data spinner
        tipePengambilanSpinner.setOnItemSelectedListener(this);

        //akses kamera
//        unggahButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED) {
//                    Intent intentCam = new Intent();
//                        intentCam.setType("image/*");
//                        intentCam.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(intentCam, 10);
//                }else {
//                    ActivityCompat.requestPermissions(SetorSampahActivity.this,
//                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                }
//            }
//        });

        //upload dari folder
        unggahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCam = new Intent(Intent.ACTION_PICK);
                intentCam.setType("image/*");
                startActivityForResult(intentCam, GALLERY_ADD_PROFILE);
            }
        });

        lanjutSetorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate
                if (pengajuanValidation()== true){
                    pengajuan();
                }
            }
        });
    }

    public void pengajuan() {
        User user = sessionManager.fetchUser();
//        File file = new File(path);
        PengajuanRequest pengajuanRequest = new PengajuanRequest();
        pengajuanRequest.setUser_id(user.getId_user());
        pengajuanRequest.setCatatan_sampah(catatanSampahEt.getText().toString());
        pengajuanRequest.setTipe_pengambilan(tipePengambilanSpinner.getSelectedItem().toString());
        pengajuanRequest.setBerat(totalBeratEt.getText().toString());
        pengajuanRequest.setAdmin_id(admin_id);
        pengajuanRequest.setFoto_sampah(bitmapToString(bitmap));
//        fotoRequest.setFoto_sampah(bitmapToString(bitmap));

        Call<PengajuanResponse> pengajuanResponseCall = apiClient.getApiService(this).userPengajuan(pengajuanRequest);
        pengajuanResponseCall.enqueue(new Callback<PengajuanResponse>() {
            @Override
            public void onResponse(Call<PengajuanResponse> call, Response<PengajuanResponse> response) {
                PengajuanResponse pengajuanResponse = response.body();
                if (pengajuanResponse.getSuccess()==true) {
                    Toast.makeText(SetorSampahActivity.this, "Data Berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    Intent intentkirimsampah = new Intent(SetorSampahActivity.this, SelesaiSetorSampahActivity.class);
                    startActivity(intentkirimsampah);
                } else {
                    Log.d("imageupload", String.valueOf(pengajuanResponse.getMessage()));
                    Toast.makeText(SetorSampahActivity.this, "Data Tidak Berhasil Terkirim"+ pengajuanResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PengajuanResponse> call, Throwable t) {
                Toast.makeText(SetorSampahActivity.this, "Throwable" +bitmapToString(bitmap), Toast.LENGTH_LONG).show();
                Log.d("image",t.getLocalizedMessage());
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

    private boolean pengajuanValidation() {
        String totalBerat = totalBeratEt.getText().toString();
        if(TextUtils.isEmpty(totalBerat)){
            if(TextUtils.isEmpty(totalBerat)){
                Toast.makeText(SetorSampahActivity.this, "Berat Sampah wajib diisi", Toast.LENGTH_LONG).show();
            }
            return false;
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        pd.dismiss();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_ADD_PROFILE && resultCode==RESULT_OK){
            Uri imgUri = data.getData();
            fotoSampahImg.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String bitmapToString(Bitmap bitmap) {
        if(bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte [] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array, Base64.DEFAULT);
        }
        return "";
    }
}