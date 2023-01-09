package com.example.bank_sampah_app.transaksi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bank_sampah_app.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class TransaksiFragment extends Fragment {

    private TabLayout tabLayout;
    private TabItem tabItem;
    private ViewPager viewPager;


    public TransaksiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaksi, container, false);
        tabLayout = v.findViewById(R.id.tablayouttransaksi);
        tabItem = v.findViewById(R.id.tablayoutdiproses);
        tabItem = v.findViewById(R.id.tablayoutriwayat);
        viewPager = v.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);

        ViewPagerTransaksiAdapter viewPagerAdapter = new ViewPagerTransaksiAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setAdapter(viewPagerAdapter);



        return v;
    }
}