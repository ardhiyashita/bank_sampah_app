package com.bps.pesanpede.API;

import android.content.Context;

import com.bps.pesanpede.authentication.SessionManager;

import java.io.IOException;

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

