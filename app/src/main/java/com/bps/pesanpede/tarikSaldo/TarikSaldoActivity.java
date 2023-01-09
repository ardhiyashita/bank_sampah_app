package com.bps.pesanpede.tarikSaldo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.bps.pesanpede.API.ApiClient;
import com.bps.pesanpede.API.requests.TarikRequest;
import com.bps.pesanpede.API.responses.TarikResponse;
import com.bps.pesanpede.R;
import com.bps.pesanpede.User;
import com.bps.pesanpede.authentication.SessionManager;

import android.view.View;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TarikSaldoActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private SessionManager sessionManager;

    Button btnLanjut;
    EditText etTarik, etCatatan;
    TextView tvSemuaSaldo;
    CheckBox cb_semuaSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik_saldo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Tarik Saldo");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        btnLanjut = findViewById(R.id.lanjutTarikSaldoButton);
        etTarik = findViewById(R.id.jumlah_tarik);
        cb_semuaSaldo = findViewById(R.id.cb_semua_saldo);
        tvSemuaSaldo = findViewById(R.id.tv_semua_saldo);
        etCatatan = findViewById(R.id.input_nota_tarik);

        User user = sessionManager.fetchUser();
        tvSemuaSaldo.setText(formatRupiah(user.getSaldo()));

        cb_semuaSaldo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                     if(cb_semuaSaldo.isChecked()){
                         etTarik.setText(Integer.toString(user.getSaldo()));
                     }
                 }
             }
        );
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
            }
        });
    }

    private void tarikSaldo(){
        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        AlertDialog alertD = alertDialogBuilder.create();
        alertDialogBuilder.setCancelable(false);
        alertD.show();

        TarikRequest tarikRequest = new TarikRequest();
        tarikRequest.setUang(Integer.parseInt(etTarik.getText().toString()));
        tarikRequest.setCatatan(etCatatan.getText().toString());

        Call<TarikResponse> tarikResponseCall = apiClient.getApiService(this).tarikSaldo(tarikRequest);
        tarikResponseCall.enqueue(new Callback<TarikResponse>() {
            @Override
            public void onResponse(Call<TarikResponse> call, Response<TarikResponse> response) {
                TarikResponse tarikResponse = response.body();
                if (tarikResponse.getSuccess()==true) {
                    Intent intent = new Intent(TarikSaldoActivity.this, RincianPenarikanActivity.class);
                    intent.putExtra("id",tarikResponse.getData().getId());
                    intent.putExtra("uang",tarikResponse.getData().getUang());
                    intent.putExtra("tanggal",tarikResponse.getData().getCreated_at());
                    startActivity(intent);
                } else {
                    Toast.makeText(TarikSaldoActivity.this, "Pengajuan Gagal, Coba Lagi" , Toast.LENGTH_LONG).show();
                }
                alertD.dismiss();
            }

            @Override
            public void onFailure(Call<TarikResponse> call, Throwable t) {
                Toast.makeText(TarikSaldoActivity.this, "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void textWatcher(){
        etTarik.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(inputValidation()){
                    etTarik.setError(null);
                }

            }
        });

        etCatatan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(inputValidation()){
                    etCatatan.setError(null);
                }

            }
        });

    }

    public boolean inputValidation(){
        String jumlahTxt = etTarik.getText().toString();
        String catatan = etCatatan.getText().toString();
        int jumlah;
        if(!jumlahTxt.equals("")){
            jumlah = Integer.parseInt(jumlahTxt);
        }else {
            jumlah = 0;
        }
        int saldo = sessionManager.fetchUser().getSaldo();

        if( jumlah<=0 ||jumlah>saldo || TextUtils.isEmpty(catatan)){
            if(jumlah<=0 && TextUtils.isEmpty(catatan)){
                Toast.makeText(TarikSaldoActivity.this, "Jumlah Penarikan dan Catatan Wajib Diisi", Toast.LENGTH_SHORT).show();
            }
            if(jumlah<=0){
                etTarik.setError("Jumlah tidak boleh kurang dari 0");
            }
            if(jumlah>saldo){
                etTarik.setError("Jumlah yang anda masukan melebihi saldo saat ini:" + formatRupiah(saldo) );
            }
            if(TextUtils.isEmpty(catatan)){
                etCatatan.setError("Catatan penarikan wajib diisi");
            }
            return false;
        }
        return true;
    }

    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

}