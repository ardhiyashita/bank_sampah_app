package com.example.bank_sampah_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.UserDataResponse;
import com.example.bank_sampah_app.authentication.LoginActivity;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.help.HelpFragment;
import com.example.bank_sampah_app.informasi.InformasiFragment;
import com.example.bank_sampah_app.profile.ProfileFragment;
import com.example.bank_sampah_app.databinding.MainActivityBinding;
import com.example.bank_sampah_app.transaksi.TransaksiFragment;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        refreshUser();
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.informasi:
                    replaceFragment(new InformasiFragment());
                    break;
                case R.id.transaksi:
                    replaceFragment(new TransaksiFragment());
                    break;
                case R.id.bantuan:
                    replaceFragment(new HelpFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public void refreshUser() {
        SessionManager sessionManager = new SessionManager(this);
        ApiClient apiClient = new ApiClient();
        Call<UserDataResponse> userDataResponseCall = apiClient.getApiService(this).getUserData();
        userDataResponseCall.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                UserDataResponse userDataResponse = response.body();
                if (userDataResponse.getSuccess()==true) {
                    sessionManager.saveUser(userDataResponse.getUser());
//                    Toast.makeText(MainActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data gagal diperbarui", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("DEBUG", "Fetch timeline error: " + t.toString());
                sessionManager.deleteAuthToken();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }
}