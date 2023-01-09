package com.example.bank_sampah_app.help;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.profile.EditProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class HelpFragment extends Fragment {
    private SessionManager sessionManager;

    private RecyclerView rv_faq;
    private final ArrayList<HelpItem> list = new ArrayList<>();
    TextView usernameTv;
    LinearLayout alamat, telp;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_help, container, false);

        sessionManager = new SessionManager(getActivity().getApplicationContext());

        User user = sessionManager.fetchUser();

        usernameTv = v.findViewById(R.id.tv_username_help);
        rv_faq = v.findViewById(R.id.rv_faq);
        alamat = v.findViewById(R.id.alamat_bps);
        telp = v.findViewById(R.id.telp_bps);

        usernameTv.setText(user.getName());

        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://goo.gl/maps/d7kjrZJMdXeiDiNB6"));
                startActivity(intent);
            }
        });

        telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://wa.me/6282266071706"));
                startActivity(intent);
            }
        });

        list.addAll(FaqData.getListData());
        showRecyclerList();
        return v;
    }


    private void showRecyclerList(){
        rv_faq.setLayoutManager(new LinearLayoutManager(getActivity()));
        HelpAdapter helpAdapter = new HelpAdapter(list);
        rv_faq.setAdapter(helpAdapter);

        helpAdapter.setOnItemClickListener(new HelpAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(HelpItem helpItem) {
                Intent moveToDetailQuestion = new Intent(getActivity(), QuestionDetailActivity.class);
                moveToDetailQuestion.putExtra(QuestionDetailActivity.ITEM_EXTRA, helpItem);
                startActivity(moveToDetailQuestion);
            }
        });
    }
}