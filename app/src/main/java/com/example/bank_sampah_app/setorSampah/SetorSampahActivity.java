package com.example.bank_sampah_app.setorSampah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetorSampahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner tipePengambilanSpinner;
    EditText totalBeratEt, catatanSampahEt;
    Button lanjutSetorButton;
    CharSequence textJenisSampah;
//    int admin_id = 0;
    private ApiClient apiClient;
    RadioGroup radioButton_pengambilan;
    int checkgroup;
    RadioButton radioButton;
    private SessionManager sessionManager;
    String[] jenisPenyetoranSampahString;
    ArrayAdapter<String> jenisPenyetoranAdapter;
    int positionOfSelectedDataFromSpinner;
    String selectedTipePengambilan;
    int totalBeratValue;

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

        //spinner dan adapter pilihan tipe pengambilan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipePengambilanString, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipePengambilanSpinner.setAdapter(adapter);

        //ambil data spinner
        tipePengambilanSpinner.setOnItemSelectedListener(this);

        lanjutSetorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate
//                int value = Integer.valueOf(totalBeratEt.getText().toString());
//                String totalBeratString = totalBeratEt.getText().toString();
//                totalBeratValue = Integer.parseInt(totalBeratString);
//                if(value>0){
//                    Intent intentlanjutsetor = new Intent(SetorSampahActivity.this, RincianSetorSampahActivity.class);
//                    intentlanjutsetor.putExtra("tipePengambilanValue", selectedTipePengambilan.toString());
//                    intentlanjutsetor.putExtra("totalBeratValue", totalBeratValue);
//                    startActivity(intentlanjutsetor);
//                }else {
//                    Toast.makeText(SetorSampahActivity.this, "Mohon Masukkan Total Berat Sampah", Toast.LENGTH_SHORT).show();
//                }
                pengajuan();
            }
        });
    }

    public void pengajuan() {
        User user = sessionManager.fetchUser();
        PengajuanRequest pengajuanRequest = new PengajuanRequest();
        pengajuanRequest.setUser_id(user.getId_user());
        String stringInt = String.valueOf(user.getId_user());
        pengajuanRequest.setCatatan_sampah(catatanSampahEt.getText().toString());
        pengajuanRequest.setTipe_pengambilan(tipePengambilanSpinner.getSelectedItem().toString());
//        pengajuanRequest.setTipe_pengambilan(radioButton.getText().toString());
        pengajuanRequest.setBerat(totalBeratEt.getText().toString());
//        pengajuanRequest.setAdmin_id(admin_id);
//        pengajuanRequest.setFoto_sampah(path.getText.toString());

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
                    Toast.makeText(SetorSampahActivity.this, "Data Tidak Berhasil Terkirim", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PengajuanResponse> call, Throwable t) {
                Toast.makeText(SetorSampahActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
}