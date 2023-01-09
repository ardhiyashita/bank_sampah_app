package com.example.bank_sampah_app.transaksi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.API.responses.TransaksiResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetoranFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private RecyclerView rv_transaksi;
    private ArrayList<DataTransaksi> data;
    private SetoranAdapter adapter;
    private ShimmerFrameLayout mShimmerViewContainer;


    public SetoranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setoran, container, false);

        mShimmerViewContainer = v.findViewById(R.id.shimmer_setoran);

        rv_transaksi = v.findViewById(R.id.rv_transaksi);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_transaksi.setLayoutManager(layoutManager);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        mShimmerViewContainer.startShimmer();

        Call<TransaksiResponse> call = apiClient.getApiService(getActivity()).getTransaksi();
        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                TransaksiResponse transaksiResponse = response.body();
                if (transaksiResponse.getSuccess()) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    rv_transaksi.setVisibility(View.VISIBLE);
                    data = new ArrayList<>(Arrays.asList(transaksiResponse.getData()));
                    adapter = new SetoranAdapter(data);
                    adapter.notifyItemRangeInserted(0, data.size());
                    rv_transaksi.scrollToPosition(data.size() - 1);
                    rv_transaksi.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Setoran Gagal di Muat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}