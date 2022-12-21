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
import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.API.responses.KategoriResponse;
import com.example.bank_sampah_app.API.responses.TransaksiResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.transaksi.SetoranAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformasiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformasiFragment extends Fragment {

    LinearLayout jadwalPengumpulan, jadwalPenjemputan;
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private RecyclerView rv_kategori;
    private ArrayList<DataKategori> data;
    private KategoriAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformasiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformasiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformasiFragment newInstance(String param1, String param2) {
        InformasiFragment fragment = new InformasiFragment();
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
        View v = inflater.inflate(R.layout.fragment_informasi, container, false);

        jadwalPengumpulan = v.findViewById(R.id.jadwalPengumpulan);
        jadwalPenjemputan = v.findViewById(R.id.jadwalPenjemputan);
        rv_kategori = v.findViewById(R.id.rv_kategori);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_kategori.setLayoutManager(layoutManager);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        jadwalPengumpulan.setOnClickListener(v1 -> {
            Intent intentPengumpulan = new Intent(getActivity(),JadwalPengumpulanSampahActivity.class);
            startActivity(intentPengumpulan);
        });

        jadwalPenjemputan.setOnClickListener(v12 -> {
            Intent intentPenjemputan = new Intent(getActivity(),JadwalPenjemputanSampahActivity.class);
            startActivity(intentPenjemputan);
        });

        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading...");
        pd.setView(view);
        pd.show();

        Call<KategoriResponse> call = apiClient.getApiService(getActivity()).getKategori();
        call.enqueue(new Callback<KategoriResponse>() {
            @Override
            public void onResponse(Call<KategoriResponse> call, Response<KategoriResponse> response) {
                KategoriResponse kategoriResponse = response.body();
                if (kategoriResponse.getSuccess()) {
                    data = new ArrayList<>(Arrays.asList(kategoriResponse.getData()));
                    adapter = new KategoriAdapter(data);
                    adapter.notifyItemRangeInserted(0, data.size());
                    rv_kategori.scrollToPosition(data.size() - 1);
                    rv_kategori.setAdapter(adapter);
//                    Toast.makeText(getActivity(), "Setoran Berhasil di Muat", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Kategori Gagal di Muat", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<KategoriResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}