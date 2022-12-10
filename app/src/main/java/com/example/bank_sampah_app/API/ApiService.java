package com.example.bank_sampah_app.API;

import com.example.bank_sampah_app.API.requests.LoginRequest;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.requests.RegisterRequest;
import com.example.bank_sampah_app.API.responses.LoginResponse;
import com.example.bank_sampah_app.API.responses.LogoutResponse;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.API.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST(Constant.LOGIN)
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);


    @POST(Constant.REGISTER)
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);

    @GET(Constant.LOGOUT)
    Call<LogoutResponse> userLogout();

    @POST(Constant.PENGAJUAN)
    Call<PengajuanResponse> userPengajuan(@Body PengajuanRequest pengajuanRequest);
}
