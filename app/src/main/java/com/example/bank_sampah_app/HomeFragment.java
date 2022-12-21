package com.example.bank_sampah_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.ArtikelResponse;
import com.example.bank_sampah_app.API.responses.UserDataResponse;
import com.example.bank_sampah_app.artikel.Artikel;
import com.example.bank_sampah_app.artikel.ArtikelActivity;
import com.example.bank_sampah_app.artikel.ArtikelAdapterHome;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.help.HelpFragment;
import com.example.bank_sampah_app.panduan.PanduanAdapter;
import com.example.bank_sampah_app.panduan.PanduanData;
import com.example.bank_sampah_app.panduan.PanduanDetailActivity;
import com.example.bank_sampah_app.panduan.PanduanItem;
import com.example.bank_sampah_app.setorSampah.SetorSampahActivity;
import com.example.bank_sampah_app.tarikSaldo.TarikSaldoActivity;
import com.example.bank_sampah_app.transaksi.TransaksiFragment;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private ApiClient apiClient;
    private SessionManager sessionManager;
    ImageView setorSampahImg, tarikSaldoImg, celengan, transaksiImg;
    TextView usernameTv, saldoTv, allArtikel;
    SwipeRefreshLayout swipeContainer;

    private RecyclerView rv_panduan, rv_artikel;
    private ArrayList<PanduanItem> list = new ArrayList<>();
    private ArrayList<Artikel> listArtikel;

    ArtikelAdapterHome artikelAdapterHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        User user = sessionManager.fetchUser();

        swipeContainer =v.findViewById(R.id.refresh_home);
        setorSampahImg = v.findViewById(R.id.setorSampahImg);
        usernameTv = v.findViewById(R.id.username);
        saldoTv = v.findViewById(R.id.saldo);
        allArtikel =v.findViewById(R.id.tv_allArtikel);

        usernameTv.setText(user.getName());
        saldoTv.setText(Integer.toString(user.getSaldo()));

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshUser();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_light);


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

        //intent to transaksi
        transaksiImg = v.findViewById(R.id.transaksiImg);

        transaksiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TransaksiFragment());
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

        allArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentArtikel = new Intent(getActivity(), ArtikelActivity.class);
                startActivity(intentArtikel);

            }
        });

        //panduan
        rv_panduan = v.findViewById(R.id.rv_panduan);
        list.addAll(PanduanData.getListData());
        showRecyclerPanduan();

        rv_artikel = v.findViewById(R.id.rv_article);
        showRecyclerArtikel();

        return v;
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void showRecyclerPanduan(){
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

    private void showRecyclerArtikel(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        rv_artikel.setLayoutManager(layoutManager);
        getArtikel();
    }

    private void getArtikel(){
        Call<ArtikelResponse> artikelResponseCall = apiClient.getApiService(getActivity().getApplicationContext()).getArtikel();
        artikelResponseCall.enqueue(new Callback<ArtikelResponse>() {
            @Override
            public void onResponse(Call<ArtikelResponse> call, Response<ArtikelResponse> response) {
                ArtikelResponse artikelResponse = response.body();
                if (artikelResponse.getSuccess()==true) {
                    listArtikel = new ArrayList<>(Arrays.asList(artikelResponse.getData()));
                    artikelAdapterHome = new ArtikelAdapterHome(getContext(),listArtikel);
                    rv_artikel.setAdapter(artikelAdapterHome);
                } else {
                    Toast.makeText(getActivity(),"Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArtikelResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void refreshUser() {
        Call<UserDataResponse> userDataResponseCall = apiClient.getApiService(getActivity()).getUserData();
        userDataResponseCall.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                UserDataResponse userDataResponse = response.body();
                if (userDataResponse.getSuccess()==true) {
                    sessionManager.saveUser(userDataResponse.getUser());
                    usernameTv.setText(userDataResponse.getUser().getName());
                    saldoTv.setText(Integer.toString(userDataResponse.getUser().getSaldo()));
                    reLoadFragment();
                    Toast.makeText(getActivity(), "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Data gagal diperbarui", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", "Fetch timeline error: " + t.toString());
            }
        });
        swipeContainer.setRefreshing(false);
    }

    public void reLoadFragment() {
        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.refresh_profile);
        FragmentTransaction fragTransaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fragTransaction.detach(this).commitNow();
            fragTransaction.attach(this).commitNow();
//            Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
        } else {
            fragTransaction.detach(this).attach(this).commit();
//            Toast.makeText(getActivity(), "Gagallll", Toast.LENGTH_SHORT).show();
        }
    }
}