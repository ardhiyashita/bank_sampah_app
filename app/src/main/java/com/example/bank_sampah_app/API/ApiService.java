package com.example.bank_sampah_app.API;

import com.example.bank_sampah_app.API.requests.LoginRequest;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.requests.RegisterRequest;
import com.example.bank_sampah_app.API.responses.LoginResponse;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.API.responses.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST(Constant.LOGIN)
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);


    @POST("register")
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);

    @POST("pengajuan/create/")
    Call<PengajuanResponse> userPengajuan(@Body PengajuanRequest pengajuanRequest);


}
