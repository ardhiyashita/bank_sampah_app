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

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PenarikanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PenarikanFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private RecyclerView rv_penarikan;
    private ArrayList<DataPenarikan> data;
    private PenarikanAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PenarikanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PenarikanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PenarikanFragment newInstance(String param1, String param2) {
        PenarikanFragment fragment = new PenarikanFragment();
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
        View v =  inflater.inflate(R.layout.fragment_penarikan, container, false);
        rv_penarikan = v.findViewById(R.id.rv_penarikan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_penarikan.setLayoutManager(layoutManager);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading...");
        pd.setView(view);
        pd.show();

        Call<PenarikanResponse> call = apiClient.getApiService(getActivity()).getPenarikan();
        call.enqueue(new Callback<PenarikanResponse>() {
            @Override
            public void onResponse(Call<PenarikanResponse> call, Response<PenarikanResponse> response) {
                PenarikanResponse penarikanResponse = response.body();
                if (penarikanResponse.getSuccess()) {
                    data = new ArrayList<>(Arrays.asList(penarikanResponse.getData()));
                    adapter = new PenarikanAdapter(data);
                    adapter.notifyItemRangeInserted(0, data.size());
                    rv_penarikan.scrollToPosition(data.size() - 1);
                    rv_penarikan.setAdapter(adapter);
//                    Toast.makeText(getActivity(), "Setoran Berhasil di Muat", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Penarikan Gagal di Muat", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PenarikanResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}