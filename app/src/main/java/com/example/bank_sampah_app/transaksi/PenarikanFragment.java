package com.example.bank_sampah_app.transaksi;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.DataPenarikan;
import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.API.responses.PenarikanResponse;
import com.example.bank_sampah_app.API.responses.TransaksiResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenarikanFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private RecyclerView rv_penarikan;
    private ArrayList<DataPenarikan> data;
    private PenarikanAdapter adapter;
    private ShimmerFrameLayout mShimmerViewContainer;


    public PenarikanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_penarikan, container, false);
        mShimmerViewContainer = v.findViewById(R.id.shimmer_tarik);

        rv_penarikan = v.findViewById(R.id.rv_penarikan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_penarikan.setLayoutManager(layoutManager);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        mShimmerViewContainer.startShimmer();

        Call<PenarikanResponse> call = apiClient.getApiService(getActivity()).getPenarikan();
        call.enqueue(new Callback<PenarikanResponse>() {
            @Override
            public void onResponse(Call<PenarikanResponse> call, Response<PenarikanResponse> response) {
                PenarikanResponse penarikanResponse = response.body();
                if (penarikanResponse.getSuccess()) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    rv_penarikan.setVisibility(View.VISIBLE);
                    data = new ArrayList<>(Arrays.asList(penarikanResponse.getData()));
                    adapter = new PenarikanAdapter(data);
                    adapter.notifyItemRangeInserted(0, data.size());
                    rv_penarikan.scrollToPosition(data.size() - 1);
                    rv_penarikan.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Penarikan Gagal di Muat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PenarikanResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}