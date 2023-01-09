package com.bps.pesanpede.authentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bps.pesanpede.API.ApiClient;
import com.bps.pesanpede.API.requests.LoginRequest;
import com.bps.pesanpede.API.responses.LoginResponse;
import com.bps.pesanpede.ConnectionReceiver;
import com.bps.pesanpede.MainActivity;
import com.bps.pesanpede.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ConnectionReceiver.ReceiverListener {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    TextInputEditText username, password;
    TextView register, forgotPassword;
    Button btnLogin;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkConnection();
        setContentView(R.layout.activity_login);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        btnLogin = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLoginListener();

    }

    public void btnLoginListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Username / Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    login();
                }
            }

        });
    }

    private void login() {
        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        AlertDialog alertD = alertDialogBuilder.create();
        alertDialogBuilder.setCancelable(false);
        alertD.show();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = apiClient.getApiService(this).userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.getSuccess()==true) {
                    sessionManager.saveAuthToken(loginResponse.getToken());
                    sessionManager.saveUser(loginResponse.getUser());
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Username dan password tidak sesuai", Toast.LENGTH_LONG).show();
                }
                alertD.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
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