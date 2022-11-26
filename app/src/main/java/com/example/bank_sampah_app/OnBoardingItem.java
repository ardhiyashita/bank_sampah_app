package com.example.bank_sampah_app;

public class OnBoardingItem {
    String Title, Description;
    int OnboardingImg;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getOnboardingImg() {
        return OnboardingImg;
    }

    public void setOnboardingImg(int onboardingImg) {
        OnboardingImg = onboardingImg;
    }

    public OnBoardingItem(String title, String description, int onboardingImg){
        Title = title;
        Description = description;
        OnboardingImg = onboardingImg;
    }
}
