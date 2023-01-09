package com.bps.pesanpede.API;

import com.bps.pesanpede.API.requests.LoginRequest;
import com.bps.pesanpede.API.requests.PengajuanRequest;
import com.bps.pesanpede.API.requests.RegisterRequest;
import com.bps.pesanpede.API.requests.TarikRequest;
import com.bps.pesanpede.API.responses.ArtikelResponse;
import com.bps.pesanpede.API.responses.DetailArtikelResponse;
import com.bps.pesanpede.API.responses.KategoriResponse;
import com.bps.pesanpede.API.responses.LoginResponse;
import com.bps.pesanpede.API.responses.LogoutResponse;
import com.bps.pesanpede.API.responses.PenarikanResponse;
import com.bps.pesanpede.API.responses.PengajuanResponse;
import com.bps.pesanpede.API.responses.PengumumanResponse;
import com.bps.pesanpede.API.responses.TarikResponse;
import com.bps.pesanpede.API.responses.TransaksiResponse;
import com.bps.pesanpede.API.responses.UserDataResponse;

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
