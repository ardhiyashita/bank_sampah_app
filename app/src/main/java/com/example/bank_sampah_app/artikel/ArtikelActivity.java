package com.example.bank_sampah_app.artikel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.ArtikelResponse;
import com.example.bank_sampah_app.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private RecyclerView rv_artikel;
    private ArrayList<Artikel> listArtikel;
    ArtikelAdapter artikelAdapter;

    private ShimmerFrameLayout mShimmerViewContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        mShimmerViewContainer = findViewById(R.id.shimmer_layout);

        apiClient = new ApiClient();

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Artikel");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        rv_artikel = findViewById(R.id.rv_article_activity);
        mShimmerViewContainer.startShimmer();
        showRecyclerArtikel();
    }

    private void showRecyclerArtikel(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ArtikelActivity.this);
        rv_artikel.setLayoutManager(layoutManager);
        getArtikel();

    }

    private void getArtikel(){
        Call<ArtikelResponse> artikelResponseCall = apiClient.getApiService(this).getArtikel();
        artikelResponseCall.enqueue(new Callback<ArtikelResponse>() {
            @Override
            public void onResponse(Call<ArtikelResponse> call, Response<ArtikelResponse> response) {
                ArtikelResponse artikelResponse = response.body();
                if (artikelResponse.getSuccess()) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    rv_artikel.setVisibility(View.VISIBLE);
                    listArtikel = new ArrayList<>(Arrays.asList(artikelResponse.getData()));
                    artikelAdapter = new ArtikelAdapter(ArtikelActivity.this,listArtikel);
                    rv_artikel.setAdapter(artikelAdapter);
                } else {
                    Toast.makeText(ArtikelActivity.this,"Gagal Memuat Artikel", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArtikelResponse> call, Throwable t) {
                Toast.makeText(ArtikelActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}