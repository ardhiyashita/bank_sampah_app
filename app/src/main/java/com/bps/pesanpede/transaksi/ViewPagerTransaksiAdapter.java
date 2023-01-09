package com.bps.pesanpede.transaksi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerTransaksiAdapter extends FragmentStatePagerAdapter {
    int tabcount;
    public ViewPagerTransaksiAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SetoranFragment setoranFragment= new SetoranFragment();
                return setoranFragment;
            case 1:
                PenarikanFragment penarikanFragment= new PenarikanFragment();
                return penarikanFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
