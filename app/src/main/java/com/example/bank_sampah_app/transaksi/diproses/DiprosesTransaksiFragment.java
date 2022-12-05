package com.example.bank_sampah_app.transaksi.diproses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_sampah_app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiprosesTransaksiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiprosesTransaksiFragment extends Fragment {

    RecyclerView recyclerViewDiproses;
    ArrayList<DiprosesItem> diprosesItemArrayList;
    DiprosesAdapter diprosesAdapter;
    String[] jenisTransaksi, statusTransaksi, tanggalTransaksi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiprosesTransaksiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiprosesTransaksiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiprosesTransaksiFragment newInstance(String param1, String param2) {
        DiprosesTransaksiFragment fragment = new DiprosesTransaksiFragment();
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
        View v = inflater.inflate(R.layout.fragment_diproses_transaksi, container, false);

        recyclerViewDiproses = v.findViewById(R.id.recyclerView_diproses);
        recyclerViewDiproses.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewDiproses.setHasFixedSize(true);

        diprosesItemArrayList = new ArrayList<DiprosesItem>();

        diprosesAdapter = new DiprosesAdapter(this, diprosesItemArrayList);
        recyclerViewDiproses.setAdapter(diprosesAdapter);

        jenisTransaksi = new String[]{
               "Setoran", "Setoran", "Penarikan"
        };

        statusTransaksi = new String[]{
                "Penyetoran Sedang Diverifikasi", "Menunggu Penjemputan", "Penarikan Dana Sedang Diproses"
        };

        tanggalTransaksi = new String[]{
                "01 November 2022", "29 Oktober 2022", "30 Oktober 2022"
        };

        getData();

        return v;
    }

    private void getData() {

        for(int i=0;i<jenisTransaksi.length;i++){

            DiprosesItem diprosesItem = new DiprosesItem(jenisTransaksi[i], statusTransaksi[i], tanggalTransaksi[i]);
            diprosesItemArrayList.add(diprosesItem);
        }

        diprosesAdapter.notifyDataSetChanged();
    }
}