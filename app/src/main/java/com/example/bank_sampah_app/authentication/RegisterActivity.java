package com.example.bank_sampah_app.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegist;
    TextInputEditText etNama, etHp, etLahir, etAlamat, etPass, etCpass;
    TextInputLayout tlNama, tlHp, tlLahir, tlAlamat, tlPass, tLCpass;
    RadioGroup rgGender;
    RadioButton radioButton;
    TextView tvLogin, tvGender;
    int checkgroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegist = findViewById(R.id.btn_daftar);
        tvLogin = findViewById(R.id.link_login);
        tvGender = findViewById(R.id.tv_gender);

        etNama = findViewById(R.id.reg_nama);
        etHp = findViewById(R.id.reg_hp);
        etLahir = findViewById(R.id.reg_lahir);
        etAlamat = findViewById(R.id.reg_alamat);
        rgGender = findViewById(R.id.reg_gender);
        etPass = findViewById(R.id.reg_password);
        etCpass = findViewById(R.id.reg_cpassword);

        tlNama = findViewById(R.id.input_layout_nama);
        tlHp = findViewById(R.id.input_layout_hp);
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

        etLahir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(checkDateFormat(etLahir.getText().toString())==false){
                    tlLahir.setError("Format tanggal tidak sesuai (dd-MM-YYYY)");
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
                // tambah post api
            }

        });
    }

    void registerValidation(){
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
        if (date == null || !date.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

}




