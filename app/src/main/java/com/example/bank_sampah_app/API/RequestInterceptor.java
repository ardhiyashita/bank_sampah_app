package com.example.bank_sampah_app.API;

import android.content.Context;

import com.example.bank_sampah_app.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    private SessionManager sessionManager;

    public RequestInterceptor(Context context) {
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
