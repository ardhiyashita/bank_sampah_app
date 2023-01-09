package com.bps.pesanpede.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bps.pesanpede.R;

import java.util.List;

public class OnBoardingViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<OnBoardingItem> mListOnboarding;

    public  OnBoardingViewPagerAdapter(Context mContext, List<OnBoardingItem> mListOnboarding){
        this.mContext = mContext;
        this.mListOnboarding = mListOnboarding;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutOnboarding = inflater.inflate(R.layout.layout_onboarding, null);

        ImageView imgSlide = layoutOnboarding.findViewById(R.id.img_onboarding);
        TextView title = layoutOnboarding.findViewById(R.id.title_onboarding);
        TextView description = layoutOnboarding.findViewById(R.id.desc_onboarding);

        title.setText(mListOnboarding.get(position).getTitle());
        description.setText(mListOnboarding.get(position).getDescription());
        imgSlide.setImageResource(mListOnboarding.get(position).getOnboardingImg());

        container.addView(layoutOnboarding);

        return layoutOnboarding;
    }

    @Override
    public int getCount(){

        return mListOnboarding.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,@NonNull Object o){

        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container,int position, @NonNull Object object){
        container.removeView((View) object);
    }
}
