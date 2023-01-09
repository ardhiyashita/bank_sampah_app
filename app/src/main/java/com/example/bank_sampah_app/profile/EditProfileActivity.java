package com.example.bank_sampah_app.profile;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.Constant;
import com.example.bank_sampah_app.API.requests.RegisterRequest;
import com.example.bank_sampah_app.API.responses.LoginResponse;
import com.example.bank_sampah_app.HomeFragment;
import com.example.bank_sampah_app.MainActivity;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.LoginActivity;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private SessionManager sessionManager;

    Button btnSimpan, btnEditfoto;
    TextInputEditText etBuku, etNama, etHp,  etAlamat;
    TextInputLayout tlBuku, tlNama, tlHp, tlAlamat;
    ImageView imgFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Edit Profile");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        btnSimpan = findViewById(R.id.btn_simpan_profile);
//        btnEditfoto = findViewById(R.id.btn_editfoto);

        etBuku = findViewById(R.id.edit_buku);
        etNama = findViewById(R.id.edit_nama);
        etHp = findViewById(R.id.edit_hp);
        etAlamat = findViewById(R.id.edit_alamat);

        tlBuku = findViewById(R.id.input_layout_editbuku);
        tlNama = findViewById(R.id.input_layout_editnama);
        tlHp = findViewById(R.id.input_layout_edithp);
        tlAlamat = findViewById(R.id.input_layout_editalamat);
//        imgFoto = findViewById(R.id.img_edtprofile);

        User user = sessionManager.fetchUser();
        etNama.setText(user.getName());
        etBuku.setText(user.getNo_buku());
        etHp.setText(user.getNo_hp());
        etAlamat.setText(user.getAlamat());

//        String url_image = user.getFoto();
//        if (url_image != null){
//            Picasso.get().load(Constant.BASE_URL+"./user/"+url_image).into(imgFoto);
//        } else{
//            imgFoto.setImageResource(R.drawable.ic_ubah_foto_profile);
//        }

        textWatcher();
//        editFotoListener();
        btnSimpanListener();


    }

//    public void editFotoListener(){
//        btnEditfoto.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, GALLERY_ADD_PROFILE);
//            }
//        });
//    }

    public void btnSimpanListener() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputValidation()){
                    LayoutInflater layoutInflater = LayoutInflater.from(EditProfileActivity.this);
                    View viewD = layoutInflater.inflate(R.layout.progress_dialog, null);
                    androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(EditProfileActivity.this);
                    alertDialogBuilder.setView(viewD);
                    AlertDialog alertD = alertDialogBuilder.create();
                    alertDialogBuilder.setCancelable(false);
                    alertD.show();

                    updateUserData();
                }
            }
        });
    }

    private void updateUserData() {
        RegisterRequest editProfileRequest = new RegisterRequest();
        editProfileRequest.setName(etNama.getText().toString());
        editProfileRequest.setNo_hp(etHp.getText().toString());
        editProfileRequest.setAlamat(etAlamat.getText().toString());

//        editProfileRequest.setFoto(imgSample);
//        editProfileRequest.setFoto(bitmapToString(bitmap));

        Call<LoginResponse> loginResponseCall = apiClient.getApiService(this).userEdit(editProfileRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.getSuccess()==true) {
                    finishDialog("Data Berhasil Diperbarui", loginResponse.getSuccess(),"Selesai");
                } else {
                    finishDialog("Data Gagal Diperbarui", loginResponse.getSuccess(),"Coba Lagi");

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                finishDialog("Data Gagal Diperbarui", false,"Coba Lagi");
            }
        });
    }

    private boolean inputValidation(){
        String hp = etHp.getText().toString();
        String alamat = etAlamat.getText().toString();

        if(TextUtils.isEmpty(hp) || TextUtils.isEmpty(alamat)){
            if(TextUtils.isEmpty(hp) && TextUtils.isEmpty(alamat) ){
                Toast.makeText(EditProfileActivity.this, "Semua data wajib diisi", Toast.LENGTH_LONG).show();
            }
            if(TextUtils.isEmpty(hp)){
                tlHp.setError("Nomor HP wajib diisi");
            }
            if(TextUtils.isEmpty(alamat)){
                tlAlamat.setError("Alamat wajib diisi");
            }
            return false;
        }
        return true;
    }

    private void textWatcher(){
        etHp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                tlHp.setError(null);
            }
        });

        etAlamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                tlAlamat.setError(null);
            }
        });
    }


    public void finishDialog(String title, Boolean status, String buttonText) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setIcon((status) ? R.drawable.ic_circle_done : R.drawable.ic_circle_fail);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(buttonText.equals("Selesai")){
                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

}