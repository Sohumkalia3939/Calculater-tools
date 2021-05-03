package com.simple.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {
private CardView card1,card2,card3,card4;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });












card1= (CardView) findViewById(R.id.c1);
card2=(CardView) findViewById(R.id.c2);
card3=(CardView) findViewById(R.id.c3);
card4=(CardView) findViewById(R.id.c4);
card1.setOnClickListener(this);
card2.setOnClickListener(this);
card3.setOnClickListener(this);
card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.c1:
                i=new Intent(this,GstCal.class);
                startActivity(i);
                break;
            case R.id.c2:
                i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.c3:
                i=new Intent(this,Emi.class);
                startActivity(i);
                break;
            case R.id.c4:
                i=new Intent(this,loan.class);
                startActivity(i);
        }
    }
}