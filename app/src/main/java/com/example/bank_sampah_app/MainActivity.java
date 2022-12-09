package com.example.bank_sampah_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.article.ArticleAdapter;
import com.example.bank_sampah_app.article.ArticleModelClass;
import com.example.bank_sampah_app.help.HelpFragment;
import com.example.bank_sampah_app.informasi.InformasiFragment;
import com.example.bank_sampah_app.profile.ProfileFragment;
import com.example.bank_sampah_app.Transaksi.TransaksiFragment;
import com.example.bank_sampah_app.databinding.MainActivityBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ArticleModelClass> articleList;
    ArticleAdapter adapter;

    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.informasi:
                    replaceFragment(new InformasiFragment());
                    break;
                case R.id.transaksi:
                    replaceFragment(new TransaksiFragment());
                    break;
                case R.id.bantuan:
                    replaceFragment(new HelpFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });

        initData();
        initRecyclerView();
    }

    private void initData() {

        articleList = new ArrayList<>();

        articleList.add(new ArticleModelClass(R.drawable.asset_onboarding1, "5 Hal Menarik Bagi Pecinta Lingkungan", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.", ""));
        articleList.add(new ArticleModelClass(R.drawable.asset_onboarding3, "6 Hal Menarik Bagi Pecinta Lingkungan", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.", ""));
        articleList.add(new ArticleModelClass(R.drawable.asset_onboarding2, "4 Hal Menarik Bagi Pecinta Lingkungan", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.", ""));
        articleList.add(new ArticleModelClass(R.drawable.asset_onboarding1, "6 Hal Menarik Bagi Pecinta Lingkungan", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.", ""));

    }

    private void initRecyclerView() {

        recyclerView= findViewById(R.id.recyclerView_article);
        layoutManager= new LinearLayoutManager( this );
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new ArticleAdapter(articleList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}