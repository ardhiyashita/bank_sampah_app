package com.bps.pesanpede.artikel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bps.pesanpede.API.ApiClient;
import com.bps.pesanpede.API.responses.DetailArtikelResponse;
import com.bps.pesanpede.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelDetailActivity extends AppCompatActivity {
    private ApiClient apiClient;
    TextView judul, tanggal, konten;
    ImageView gambar;
    ScrollView artikel;

    String slug;
    private Artikel detailArtikel;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_detail);

        mShimmerViewContainer = findViewById(R.id.shimmer_detail_article);

        apiClient = new ApiClient();

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Artikel");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        judul = findViewById(R.id.detail_artikel_judul);
        tanggal = findViewById(R.id.detail_artikel_tanggal);
        konten = findViewById(R.id.detail_artikel_konten);
        gambar = findViewById(R.id.detail_artikel_gambar);

        if(getIntent().hasExtra("slug")){
            slug = getIntent().getStringExtra("slug");
        }

        artikel = findViewById(R.id.artikel_konten);
        mShimmerViewContainer.startShimmer();
        getArtikel();
    }

    private void getArtikel(){
        Call<DetailArtikelResponse> detailArtikelResponseCall = apiClient.getApiService(this).getDetailArtikel(slug);
        detailArtikelResponseCall.enqueue(new Callback<DetailArtikelResponse>() {
            @Override
            public void onResponse(Call<DetailArtikelResponse> call, Response<DetailArtikelResponse> response) {
                DetailArtikelResponse detailArtikelResponse = response.body();
                if (detailArtikelResponse.getSuccess()==true) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    artikel.setVisibility(View.VISIBLE);
                    detailArtikel = detailArtikelResponse.getData();
                    judul.setText(detailArtikel.getJudul());
                    tanggal.setText(dateFormatter(detailArtikel.getTanggal()));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        konten.setText(Html.fromHtml(detailArtikel.getKonten(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        konten.setText(Html.fromHtml(detailArtikel.getKonten()));
                    }
                    Picasso.get().load("https://pesanpede.com/" + detailArtikel.getGambar()).into(gambar);

                } else {
                    Toast.makeText(ArtikelDetailActivity.this,"Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailArtikelResponse> call, Throwable t) {
                Toast.makeText(ArtikelDetailActivity.this, "Gagal memuat, coba lagi" , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String dateFormatter(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date d = sdf.parse(date);

            SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            output.setTimeZone(TimeZone.getDefault());
            String formattedTime = output.format(d);
            return formattedTime;
        }catch (ParseException e) {
            return null;
        }
    }
}