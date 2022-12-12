package com.example.bank_sampah_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.authentication.LoginActivity;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.help.FaqData;
import com.example.bank_sampah_app.help.HelpAdapter;
import com.example.bank_sampah_app.help.HelpItem;
import com.example.bank_sampah_app.help.QuestionDetailActivity;
import com.example.bank_sampah_app.panduan.PanduanAdapter;
import com.example.bank_sampah_app.panduan.PanduanData;
import com.example.bank_sampah_app.panduan.PanduanDetailActivity;
import com.example.bank_sampah_app.panduan.PanduanItem;
import com.example.bank_sampah_app.setorSampah.SetorSampahActivity;
import com.example.bank_sampah_app.tarikSaldo.TarikSaldoActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private SessionManager sessionManager;
    ImageView setorSampahImg, tarikSaldoImg, celengan;
    TextView usernameTv, saldoTv;

    private RecyclerView rv_panduan;
    private ArrayList<PanduanItem> list = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        User user = sessionManager.fetchUser();

        setorSampahImg = v.findViewById(R.id.setorSampahImg);
        usernameTv = v.findViewById(R.id.username);
        saldoTv = v.findViewById(R.id.saldo);

        usernameTv.setText(user.getName());
        saldoTv.setText(Integer.toString(user.getSaldo()));


        if(user!=null){
            Toast.makeText(getActivity(), "Selamat datang kembali" + " " +user.getName(), Toast.LENGTH_SHORT).show();
        };

        setorSampahImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsetorsampah = new Intent(getActivity(), SetorSampahActivity.class);
                startActivity(intentsetorsampah);

            }
        });

        //intent to tarik saldo
        tarikSaldoImg = v.findViewById(R.id.tarikSaldoImg);

        tarikSaldoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenttariksaldo = new Intent(getActivity(), TarikSaldoActivity.class);
                startActivity(intenttariksaldo);

            }
        });

        //intent to tarik saldo from celengan
        celengan = v.findViewById(R.id.celengan);

        celengan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsetorfromcelengan = new Intent(getActivity(), SetorSampahActivity.class);
                startActivity(intentsetorfromcelengan);

            }
        });

        //panduan
        rv_panduan = v.findViewById(R.id.rv_panduan);
        list.addAll(PanduanData.getListData());
        showRecyclerList();

        return v;
    }

    private void showRecyclerList(){
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_panduan.setLayoutManager(horizontalLayoutManager);
        PanduanAdapter panduanAdapter = new PanduanAdapter(list);
        rv_panduan.setAdapter(panduanAdapter);

        panduanAdapter.setOnItemClickListener(new PanduanAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(PanduanItem panduanItem) {
                Intent moveToDetailPanduan = new Intent(getActivity(), PanduanDetailActivity.class);
                moveToDetailPanduan.putExtra(PanduanDetailActivity.ITEM_EXTRA, panduanItem);
                startActivity(moveToDetailPanduan);
            }
        });
    }
}