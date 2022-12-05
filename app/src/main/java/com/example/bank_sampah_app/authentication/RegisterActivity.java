package com.example.bank_sampah_app.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.Call;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.bank_sampah_app.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    Button btnDaftar;
    TextInputEditText regNama, regHp, regLahir, regAlamat, regPass, regCpass;
    RadioGroup regGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnDaftar = findViewById(R.id.btn_daftar);
        regNama = findViewById(R.id.reg_nama);
        regHp = findViewById(R.id.reg_hp);
        regLahir = findViewById(R.id.reg_lahir);
        regAlamat = findViewById(R.id.reg_alamat);
        regGender = findViewById(R.id.reg_gender);
        regPass = findViewById(R.id.reg_password);
        regCpass = findViewById(R.id.reg_cpassword);
    }

    public void registerUsers(){

    }
}