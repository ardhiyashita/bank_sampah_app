package com.example.bank_sampah_app.tarikSaldo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.profile.EditProfileActivity;

import android.view.View;
import android.widget.Toast;

public class TarikSaldoActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private SessionManager sessionManager;

    Button btnLanjut;
    EditText etTarik, etCatatan;
    TextView tvSemuaSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik_saldo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Tarik Saldo");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        btnLanjut = findViewById(R.id.lanjutTarikSaldoButton);
        etTarik = findViewById(R.id.jumlah_tarik);
        tvSemuaSaldo = findViewById(R.id.tv_semua_saldo);
        etCatatan = findViewById(R.id.input_nota_tarik);

        User user = sessionManager.fetchUser();
        tvSemuaSaldo.setText(Integer.toString(user.getSaldo()));

        textWatcher();
        btnLanjutListener();
    }

    public void btnLanjutListener(){
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputValidation()){
                    tarikSaldo();
                }
//                Intent intentlanjutnarik = new Intent(TarikSaldoActivity.this, RincianPenarikanActivity.class);
//                startActivity(intentlanjutnarik);
            }
        });
    }

    private void tarikSaldo(){

    }

    private boolean inputValidation() {
        String jumlahTxt = etTarik.getText().toString();
        int jumlah = Integer.parseInt(jumlahTxt);
        String saldoTxt = tvSemuaSaldo.getText().toString();
        int saldo = Integer.parseInt(saldoTxt);
        boolean success = true;

        if(TextUtils.isEmpty(jumlahTxt)){
            etTarik.setError("Jumlah saldo tidak boleh kosong");
//            Toast.makeText(TarikSaldoActivity.this, "Jumlah saldo tidak boleh kosong", Toast.LENGTH_LONG).show();
            return success = false;
        }
        if(jumlah<=0){
            etTarik.setError("Jumlah tidak boleh kurang dari 0");
//            Toast.makeText(TarikSaldoActivity.this, "Jumlah tidak boleh kurang dari 0", Toast.LENGTH_LONG).show();
            return success = false;

        }
        if(jumlah>saldo){
            etTarik.setError("Jumlah yang anda masukan melebihi saldo saat ini:" + saldoTxt);
//            Toast.makeText(TarikSaldoActivity.this, "Jumlah yang anda masukan melebihi saldo saat ini:" + saldoTxt, Toast.LENGTH_LONG).show();
            return success = false;
        }
        return success;
    }

    private void textWatcher(){
        etTarik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                etTarik.setError(null);
            }
        });
    }
}