package com.example.bank_sampah_app.API;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.bank_sampah_app.MainActivity;
import com.example.bank_sampah_app.authentication.LoginActivity;
import com.example.bank_sampah_app.authentication.SessionManager;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {
    private final SessionManager sessionManager;

    public AuthorizationInterceptor(Context context) {
        sessionManager = new SessionManager(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        // Jika token ada di session manager, token sisipkan di request header
        if (sessionManager.fetchAuthToken() != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + sessionManager.fetchAuthToken());
        }

        return chain.proceed(requestBuilder.build());
    }
}

