package com.example.bank_sampah_app.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.requests.RegisterRequest;
import com.example.bank_sampah_app.API.responses.LoginResponse;
import com.example.bank_sampah_app.API.responses.UserDataResponse;
import com.example.bank_sampah_app.HomeFragment;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.databinding.MainActivityBinding;
import com.example.bank_sampah_app.help.HelpFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private SessionManager sessionManager;

    Button btnSimpan;
    TextInputEditText etNama, etHp, etEmail, etLahir, etAlamat;
    TextInputLayout tlNama, tlHp, tlEmail, tlLahir, tlAlamat;
    ImageView imgFoto, imgEdit;
    String jenis_kelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Edit Profile");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        btnSimpan = findViewById(R.id.btn_simpan_profile);

        etNama = findViewById(R.id.edit_nama);
        etHp = findViewById(R.id.edit_hp);
        etEmail = findViewById(R.id.edit_email);
        etLahir = findViewById(R.id.edit_lahir);
        etAlamat = findViewById(R.id.edit_alamat);

        tlNama = findViewById(R.id.input_layout_editnama);
        tlHp = findViewById(R.id.input_layout_edithp);
        tlEmail = findViewById(R.id.input_layout_editemail);
        tlLahir = findViewById(R.id.input_layout_editlahir);
        tlAlamat = findViewById(R.id.input_layout_editalamat);

        User user = sessionManager.fetchUser();
        etNama.setText(user.getName());
        etHp.setText(user.getNo_hp());
        etEmail.setText(user.getEmail());
        etLahir.setText(user.getTgl_lahir());
        etAlamat.setText(user.getAlamat());
        jenis_kelamin = user.getJenis_kelamin();

        textWatcher();
        btnSimpanListener();


    }

    public void btnSimpanListener() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputValidation()){
                    updateUserData();
                }
            }
        });
    }

    private void updateUserData() {
        RegisterRequest editProfileRequest = new RegisterRequest();
        editProfileRequest.setName(etNama.getText().toString());
        editProfileRequest.setEmail(etEmail.getText().toString());
        editProfileRequest.setAlamat(etAlamat.getText().toString());
        editProfileRequest.setJenis_kelamin(jenis_kelamin);
        editProfileRequest.setNo_hp(etHp.getText().toString());
        editProfileRequest.setTgl_lahir(etLahir.getText().toString());
        editProfileRequest.setFoto(null);

        Call<LoginResponse> loginResponseCall = apiClient.getApiService(this).userEdit(editProfileRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.getSuccess()==true) {
                    Toast.makeText(EditProfileActivity.this, "Data Berhasil Diperbarui", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Data Tidak Berhasil Diperbarui" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean inputValidation(){
        String nama = etNama.getText().toString();
        String hp = etHp.getText().toString();
        String lahir = etLahir.getText().toString();
        String alamat = etAlamat.getText().toString();

        if(TextUtils.isEmpty(nama) || TextUtils.isEmpty(hp) || TextUtils.isEmpty(lahir) || TextUtils.isEmpty(alamat)){
            if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(hp) && TextUtils.isEmpty(lahir) && TextUtils.isEmpty(alamat) ){
                Toast.makeText(EditProfileActivity.this, "Semua data wajib diisi", Toast.LENGTH_LONG).show();
            }
            if(TextUtils.isEmpty(nama)){
                tlNama.setError("Nama wajib diisi");
            }
            if(TextUtils.isEmpty(hp)){
                tlHp.setError("Nomor HP wajib diisi");
            }
            if(TextUtils.isEmpty(lahir)){
                tlLahir.setError("Tanggal Lahir wajib diisi");
            }
            if(TextUtils.isEmpty(alamat)){
                tlAlamat.setError("Alamat wajib diisi");
            }
            return false;
        }
        return true;
    }

    private void textWatcher(){
        etNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                tlNama.setError(null);
            }
        });

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

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(etEmail.getText().toString())){
                    tlEmail.setError(null);
                    tlEmail.setErrorEnabled(false);
                }
                else {
                    if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()){
                        tlEmail.setError("Alamat email salah");
                    } else {
                        tlEmail.setError(null); }
                }
            }
        });

        etLahir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!checkDateFormat(etLahir.getText().toString())){
                    tlLahir.setError("Format tanggal tidak sesuai (yyyy-mm-dd)");
                } else {
                    tlLahir.setError(null);
                }
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

    public Boolean checkDateFormat(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-DD");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }
}