package com.example.bank_sampah_app.informasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.DataKategori;
import com.example.bank_sampah_app.API.responses.DataPengumuman;
import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.API.responses.KategoriResponse;
import com.example.bank_sampah_app.API.responses.PengumumanResponse;
import com.example.bank_sampah_app.API.responses.TransaksiResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.transaksi.SetoranAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InformasiFragment extends Fragment {

    LinearLayout jadwalPengumpulan, jadwalPenjemputan;
    private ApiClient apiClient;
    private RecyclerView rv_kategori, rv_pengumuman;
    private ArrayList<DataKategori> data;
    private ArrayList<DataPengumuman> dataPengumumen;
    private KategoriAdapter adapter;
    private PengumumanAdapter pengumumanAdapter;

    private ShimmerFrameLayout mShimmerViewContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_informasi, container, false);
        mShimmerViewContainer = v.findViewById(R.id.shimmer_kategori);

        rv_pengumuman = v.findViewById(R.id.rv_pengumuman);

        rv_kategori = v.findViewById(R.id.rv_kategori);
        mShimmerViewContainer.startShimmer();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView.LayoutManager layoutManagerPengumuman = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_kategori.setLayoutManager(layoutManager);
        rv_kategori.setNestedScrollingEnabled(false);
        rv_pengumuman.setLayoutManager(layoutManagerPengumuman);

        apiClient = new ApiClient();

        Call<PengumumanResponse> callPengumuman = apiClient.getApiService(getActivity()).getPengumuman();
        callPengumuman.enqueue(new Callback<PengumumanResponse>() {
            @Override
            public void onResponse(Call<PengumumanResponse> call, Response<PengumumanResponse> response) {
                PengumumanResponse pengumumanResponse = response.body();
                if (pengumumanResponse.getSuccess()) {
                    dataPengumumen = new ArrayList<>(Arrays.asList(pengumumanResponse.getData()));
                    pengumumanAdapter = new PengumumanAdapter(dataPengumumen);
                    rv_pengumuman.setAdapter(pengumumanAdapter);
                } else {
                    Toast.makeText(getActivity(), "Kategori Gagal di Muat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PengumumanResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Call<KategoriResponse> call = apiClient.getApiService(getActivity()).getKategori();
        call.enqueue(new Callback<KategoriResponse>() {
            @Override
            public void onResponse(Call<KategoriResponse> call, Response<KategoriResponse> response) {
                KategoriResponse kategoriResponse = response.body();
                if (kategoriResponse.getSuccess()) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    rv_kategori.setVisibility(View.VISIBLE);
                    data = new ArrayList<>(Arrays.asList(kategoriResponse.getData()));
                    adapter = new KategoriAdapter(data);
                    rv_kategori.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Kategori Gagal di Muat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<KategoriResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}