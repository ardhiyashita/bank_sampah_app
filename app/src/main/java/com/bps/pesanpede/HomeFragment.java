package com.bps.pesanpede;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bps.pesanpede.API.ApiClient;
import com.bps.pesanpede.API.responses.ArtikelResponse;
import com.bps.pesanpede.API.responses.UserDataResponse;
import com.bps.pesanpede.artikel.Artikel;
import com.bps.pesanpede.artikel.ArtikelActivity;
import com.bps.pesanpede.artikel.ArtikelAdapterHome;
import com.bps.pesanpede.authentication.SessionManager;
import com.bps.pesanpede.panduan.PanduanAdapter;
import com.bps.pesanpede.panduan.PanduanData;
import com.bps.pesanpede.panduan.PanduanDetailActivity;
import com.bps.pesanpede.panduan.PanduanItem;
import com.bps.pesanpede.setorSampah.SetorSampahActivity;
import com.bps.pesanpede.tarikSaldo.TarikSaldoActivity;
import com.bps.pesanpede.tentangKami.TentangData;
import com.bps.pesanpede.tentangKami.TentangDetailActivity;
import com.bps.pesanpede.tentangKami.TentangKamiAdapter;
import com.bps.pesanpede.tentangKami.TentangKamiItem;
import com.bps.pesanpede.transaksi.TransaksiFragment;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import com.bps.pesanpede.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private ApiClient apiClient;
    private SessionManager sessionManager;
    ImageView setorSampahImg, tarikSaldoImg, celengan, transaksiImg;
    TextView usernameTv, saldoTv, allArtikel;
    SwipeRefreshLayout swipeContainer;

    private RecyclerView rv_panduan, rv_artikel, rv_tentang_kami;
    private final ArrayList<TentangKamiItem> listTentang = new ArrayList<>();
    private final ArrayList<PanduanItem> list = new ArrayList<>();
    private ArrayList<Artikel> listArtikel;

    ArtikelAdapterHome artikelAdapterHome;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mShimmerViewContainer = v.findViewById(R.id.shimmer_home_article);

        apiClient = new ApiClient();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        User user = sessionManager.fetchUser();

        swipeContainer =v.findViewById(R.id.refresh_home);
        setorSampahImg = v.findViewById(R.id.setorSampahImg);
        usernameTv = v.findViewById(R.id.username);
        saldoTv = v.findViewById(R.id.saldo);
        allArtikel =v.findViewById(R.id.tv_allArtikel);

        usernameTv.setText(user.getName());
        saldoTv.setText(formatRupiah(user.getSaldo()));

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

        //artikel
        rv_artikel = v.findViewById(R.id.rv_article);
        mShimmerViewContainer.startShimmer();
        showRecyclerArtikel();

        //tentang kami
        rv_tentang_kami = v.findViewById(R.id.rv_tentang_kami);
        listTentang.addAll(TentangData.getListTentang());
        showRecyclerTentang();

        return v;
    }

    private void showRecyclerTentang() {
        rv_tentang_kami.setLayoutManager(new LinearLayoutManager(getActivity()));
        TentangKamiAdapter tentangKamiAdapter = new TentangKamiAdapter(listTentang);
        rv_tentang_kami.setAdapter(tentangKamiAdapter);

        tentangKamiAdapter.setOnItemClickListener(new TentangKamiAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TentangKamiItem tentangKamiItem) {
                Intent moveToDetailQuestion = new Intent(getActivity(), TentangDetailActivity.class);
                moveToDetailQuestion.putExtra(TentangDetailActivity.ITEM_EXTRA, tentangKamiItem);
                startActivity(moveToDetailQuestion);
            }
        });
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
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    rv_artikel.setVisibility(View.VISIBLE);
                    allArtikel.setVisibility(View.VISIBLE);
                    listArtikel = new ArrayList<>(Arrays.asList(artikelResponse.getData()));
                    artikelAdapterHome = new ArtikelAdapterHome(getContext(),listArtikel);
                    rv_artikel.setAdapter(artikelAdapterHome);
                } else {
                    Toast.makeText(getActivity(),"Gagal memuat artikel", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArtikelResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();

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
                    saldoTv.setText(formatRupiah(userDataResponse.getUser().getSaldo()));
                    reLoadFragment();
                    Toast.makeText(getActivity(), "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Data gagal diperbarui", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
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
        } else {
            fragTransaction.detach(this).attach(this).commit();
        }
    }

    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}