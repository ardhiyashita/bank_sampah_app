package com.example.bank_sampah_app.Transaksi.Diproses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bank_sampah_app.Transaksi.RiwayatTransaksiFragment;

public class ViewPagerDiprosesAdapter extends FragmentPagerAdapter {

//    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
//    private final ArrayList<String> fragmentTitle = new ArrayList<>();
    private int numOfTabs;

    public ViewPagerDiprosesAdapter(@NonNull FragmentManager fm, int numOfTabs) {

        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

//        return fragmentArrayList.get(position);
        switch (position){
            case 0:
                return new DiprosesTransaksiFragment();
            case 1:
                return new RiwayatTransaksiFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

//    public void addFragment(Fragment fragment, String title){
//        fragmentArrayList.add(fragment);
//        fragmentTitle.add(title);
//
//    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fragmentTitle.get(position);
//    }
}
