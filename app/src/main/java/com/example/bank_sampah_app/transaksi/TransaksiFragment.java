package com.example.bank_sampah_app.transaksi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.help.FaqData;
import com.example.bank_sampah_app.help.HelpAdapter;
import com.example.bank_sampah_app.help.HelpItem;
import com.example.bank_sampah_app.help.QuestionDetailActivity;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransaksiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransaksiFragment extends Fragment {

    private TabLayout tabLayout;
    private TabItem tabItem;
    private ViewPager viewPager;
    private RecyclerView rv_transaksi;
    private ArrayList<TransaksiItem> list = new ArrayList<>();

//  TODO: Rename parameter arguments, choose names that match
//  the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

//  TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransaksiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransaksiFragment.
     */
//  TODO: Rename and change types and number of parameters
    public static TransaksiFragment newInstance(String param1, String param2) {
        TransaksiFragment fragment = new TransaksiFragment();
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
        View v = inflater.inflate(R.layout.fragment_transaksi, container, false);
        rv_transaksi = v.findViewById(R.id.rv_transaksi);

//        list.addAll(FaqData.getListData());
        showRecyclerList();

        return v;

    }

    private void showRecyclerList(){
        rv_transaksi.setLayoutManager(new LinearLayoutManager(getActivity()));
        TransaksiAdapter transaksiAdapter = new TransaksiAdapter(list);
        rv_transaksi.setAdapter(transaksiAdapter);
    }
}