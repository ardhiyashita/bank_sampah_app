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
import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.API.responses.TransaksiResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.authentication.SessionManager;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetoranFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetoranFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private RecyclerView rv_transaksi;
    private ArrayList<DataTransaksi> data;
    private SetoranAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetoranFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetoranFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetoranFragment newInstance(String param1, String param2) {
        SetoranFragment fragment = new SetoranFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setoran, container, false);
        rv_transaksi = v.findViewById(R.id.rv_transaksi);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_transaksi.setLayoutManager(layoutManager);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading...");
        pd.setView(view);
        pd.show();

        Call<TransaksiResponse> call = apiClient.getApiService(getActivity()).getTransaksi();
        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                TransaksiResponse transaksiResponse = response.body();
                if (transaksiResponse.getSuccess()) {
                    data = new ArrayList<>(Arrays.asList(transaksiResponse.getData()));
                    adapter = new SetoranAdapter(data);
                    adapter.notifyItemRangeInserted(0, data.size());
                    rv_transaksi.scrollToPosition(data.size() - 1);
                    rv_transaksi.setAdapter(adapter);
//                    Toast.makeText(getActivity(), "Setoran Berhasil di Muat", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Setoran Gagal di Muat", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}