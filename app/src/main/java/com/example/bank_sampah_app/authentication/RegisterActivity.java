package com.example.bank_sampah_app.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.requests.LoginRequest;
import com.example.bank_sampah_app.API.requests.RegisterRequest;
import com.example.bank_sampah_app.API.responses.LoginResponse;
import com.example.bank_sampah_app.API.responses.RegisterResponse;
import com.example.bank_sampah_app.MainActivity;
import com.example.bank_sampah_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ApiClient apiClient;
    Button btnRegist;
    TextInputEditText etNama, etHp, etEmail, etLahir, etAlamat, etPass, etCpass;
    TextInputLayout tlNama, tlHp, tlEmail, tlLahir, tlAlamat, tlPass, tLCpass;
    RadioGroup rgGender;
    RadioButton radioButton;
    TextView tvLogin, tvGender;
    int checkgroup;
    Date lahirDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        apiClient = new ApiClient();

        btnRegist = findViewById(R.id.btn_daftar);
        tvLogin = findViewById(R.id.link_login);
        tvGender = findViewById(R.id.tv_gender);

        etNama = findViewById(R.id.reg_nama);
        etHp = findViewById(R.id.reg_hp);
        etEmail = findViewById(R.id.reg_email);
        etLahir = findViewById(R.id.reg_lahir);
        etAlamat = findViewById(R.id.reg_alamat);
        rgGender = findViewById(R.id.reg_gender);
        etPass = findViewById(R.id.reg_password);
        etCpass = findViewById(R.id.reg_cpassword);

        tlNama = findViewById(R.id.input_layout_nama);
        tlHp = findViewById(R.id.input_layout_hp);
        tlEmail = findViewById(R.id.input_layout_email);
        tlLahir = findViewById(R.id.input_layout_lahir);
        tlAlamat = findViewById(R.id.input_layout_alamat);
        tlPass = findViewById(R.id.input_layout_password);
        tLCpass = findViewById(R.id.input_layout_confirm);


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
                if(checkDateFormat(etLahir.getText().toString())==false){
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

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                tvGender.setError(null);
                checkgroup = checkedId;
                radioButton = findViewById(checkedId);
            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                tlPass.setError(null);
            }
        });

        etCpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!etCpass.getText().toString().equals(etPass.getText().toString())){
                    tLCpass.setError("Password tidak sesuai");
                } else {
                    tLCpass.setError(null);
                }
            }
        });


        btnRegisterListener();
        txtLoginListener();
    }

    public void txtLoginListener() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btnRegisterListener() {
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerValidation();
                register();
            }

        });
    }

    private void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(etNama.getText().toString());
        registerRequest.setEmail(etEmail.getText().toString());
        registerRequest.setPassword(etPass.getText().toString());
        registerRequest.setAlamat(etAlamat.getText().toString());
        registerRequest.setJenis_kelamin(radioButton.getText().toString());
        registerRequest.setNo_hp(etHp.getText().toString());
        registerRequest.setTgl_lahir(etLahir.getText().toString());

        Call<RegisterResponse> registerResponseCall = apiClient.getApiService(this).userRegister(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (registerResponse.getSuccess()==true) {
                    Toast.makeText(RegisterActivity.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                    toLogin();
                } else {
                    Toast.makeText(RegisterActivity.this, "Pendaftaran Gagal" + registerResponse.getMessage().getErrorInfo(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void registerValidation(){
        String nama = etNama.getText().toString();
        String hp = etHp.getText().toString();
        String lahir = etLahir.getText().toString();
        String alamat = etAlamat.getText().toString();
        String pass = etPass.getText().toString();
        String cpass = etCpass.getText().toString();

        if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(hp) && TextUtils.isEmpty(lahir) && TextUtils.isEmpty(alamat) && checkgroup <=0 && TextUtils.isEmpty(pass) && TextUtils.isEmpty(cpass)){
            Toast.makeText(RegisterActivity.this, "Semua kolom wajib diisi", Toast.LENGTH_LONG).show();
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
        if (checkgroup <= 0){
            Drawable errorIcon = getResources().getDrawable(R.drawable.ic_error_outline_red);
            errorIcon.setBounds(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight());
            tvGender.setError("Jenis kelamin wajib diisi",errorIcon);
        }
        if(TextUtils.isEmpty(pass)){
            tlPass.setError("Password wajib diisi");
        }
        if(TextUtils.isEmpty(cpass)){
            tLCpass.setError("Konfirmasi password wajib diisi");
        }

    }


    public Boolean checkDateFormat(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

}




