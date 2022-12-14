package com.example.bank_sampah_app.API;

import com.example.bank_sampah_app.API.requests.LoginRequest;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.requests.RegisterRequest;
import com.example.bank_sampah_app.API.requests.TarikRequest;
import com.example.bank_sampah_app.API.responses.ArtikelResponse;
import com.example.bank_sampah_app.API.responses.DetailArtikelResponse;
import com.example.bank_sampah_app.API.responses.KategoriResponse;
import com.example.bank_sampah_app.API.responses.LoginResponse;
import com.example.bank_sampah_app.API.responses.LogoutResponse;
import com.example.bank_sampah_app.API.responses.PenarikanResponse;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.API.responses.PengumumanResponse;
import com.example.bank_sampah_app.API.responses.TarikResponse;
import com.example.bank_sampah_app.API.responses.TransaksiResponse;
import com.example.bank_sampah_app.API.responses.UserDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST(Constant.LOGIN)
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @GET(Constant.LOGOUT)
    Call<LogoutResponse> userLogout();

    @POST(Constant.PENGAJUAN)
    Call<PengajuanResponse> userPengajuan(@Body PengajuanRequest pengajuanRequest);

    @GET(Constant.DATA_USER)
    Call<UserDataResponse> getUserData();

    @POST(Constant.EDIT_USER)
    Call<LoginResponse> userEdit(@Body RegisterRequest editProfileRequest);

    @POST(Constant.TARIK)
    Call<TarikResponse> tarikSaldo(@Body TarikRequest tarikRequest);

    @GET(Constant.ARTIKEL)
    Call<ArtikelResponse> getArtikel();

    @GET(Constant.ARTIKEL+"/{slug}")
    Call<DetailArtikelResponse> getDetailArtikel(@Path("slug") String slug);

    @GET(Constant.TRANSAKSI)
    Call<TransaksiResponse> getTransaksi();

    @GET(Constant.PENARIKAN)
    Call<PenarikanResponse> getPenarikan();

    @GET(Constant.KATEGORI)
    Call<KategoriResponse> getKategori();

    @GET(Constant.PENGUMUMAN)
    Call<PengumumanResponse> getPengumuman();
}
