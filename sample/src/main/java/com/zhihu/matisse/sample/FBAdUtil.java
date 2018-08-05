package com.zhihu.matisse.sample;


import android.content.Context;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

/**
 * Created by liyanju on 2018/8/5.
 */

public class FBAdUtil {

    private static InterstitialAd sInterstitialAd;

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static void destoryInterstitial() {
        try {
            if (sInterstitialAd != null) {
                sInterstitialAd.destroy();
                sInterstitialAd = null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void showInterstitial() {
        try {
            if (sInterstitialAd != null && sInterstitialAd.isAdLoaded()) {
                sInterstitialAd.show();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void interstitialLoad(String aid, final FBInterstitialAdListener listener) {
        sInterstitialAd = new InterstitialAd(sContext, aid);
        sInterstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                if (listener != null) {
                    listener.onInterstitialDisplayed(ad);
                }
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                if (listener != null) {
                    listener.onInterstitialDismissed(ad);
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (listener != null) {
                    listener.onError(ad, adError);
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (listener != null && sInterstitialAd != null) {
                    listener.onLoaded(sInterstitialAd);
                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                if (listener != null) {
                    listener.onAdClicked(ad);
                }
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                if (listener != null) {
                    listener.onLoggingImpression(ad);
                }
            }

        });
        sInterstitialAd.loadAd();
    }

    public static class FBInterstitialAdListener implements InterstitialAdListener {

        public void onLoaded(InterstitialAd interstitialAd){

        }

        @Override
        public void onInterstitialDisplayed(Ad ad) {

        }

        @Override
        public void onInterstitialDismissed(Ad ad) {

        }

        @Override
        public void onError(Ad ad, AdError adError) {

        }

        @Override
        public void onAdLoaded(Ad ad) {

        }

        @Override
        public void onAdClicked(Ad ad) {

        }

        @Override
        public void onLoggingImpression(Ad ad) {

        }
    }
}
