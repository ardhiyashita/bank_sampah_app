package com.bps.pesanpede;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bps.pesanpede.API.ApiClient;
import com.bps.pesanpede.API.responses.UserDataResponse;
import com.bps.pesanpede.authentication.LoginActivity;
import com.bps.pesanpede.authentication.SessionManager;
import com.bps.pesanpede.help.HelpFragment;
import com.bps.pesanpede.informasi.InformasiFragment;
import com.bps.pesanpede.profile.ProfileFragment;
import com.bps.pesanpede.databinding.MainActivityBinding;
import com.bps.pesanpede.transaksi.TransaksiFragment;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ConnectionReceiver.ReceiverListener{

    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (!preferences.contains("isUserLogin")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else{
            if(checkConnection()){
                refreshUser();
            }
        }

        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
                } else {
                    Toast.makeText(MainActivity.this, "Data gagal diperbarui", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sesi telah habis, silahkan login kembali", Toast.LENGTH_SHORT).show();
                sessionManager.deleteAuthToken();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }

    private boolean checkConnection() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        registerReceiver(new ConnectionReceiver(), intentFilter);

        ConnectionReceiver.Listener = this;
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        noInternetDialog(isConnected);
        return isConnected;
    }

    private void noInternetDialog(boolean isConnected){
        if (!isConnected) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View view = layoutInflater.inflate(R.layout.dialog_no_internet, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(view);

            AlertDialog alertD = alertDialogBuilder.create();

            Button btnRetry = view.findViewById(R.id.btn_dialog_retry);
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkConnection();
                    alertD.dismiss();
                }
            });
            alertDialogBuilder.setCancelable(false);
            alertD.show();
        }

    }

    @Override
    public void onNetworkChange(boolean isConnected) {
        noInternetDialog(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
    }
}