package com.whapprapphck.view.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyLog;
import com.whapprapphck.R;
import com.whapprapphck.other.Const;
import com.whapprapphck.view.fragments.HackWhatsAppFragment;
import com.whapprapphck.view.fragments.StartFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ActivityCallback, TJGetCurrencyBalanceListener {

    private static final String TAG = "TAG";
    private static final String TAG_TAPJOY = "TAPJOY";

    private FragmentManager fragmentManager;

    private TJPlacement offerwallPlacement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new StartFragment(), false);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showOfferWall() {

        TJPlacementListener placementListener = new TJPlacementListener() {
            @Override
            public void onRequestSuccess(TJPlacement placement) {

                if (!placement.isContentAvailable()) {

                }
            }

            @Override
            public void onRequestFailure(TJPlacement placement, TJError error) {
                TapjoyLog.i(TAG, placement.getName() + ": " + error.message);
            }

            @Override
            public void onContentReady(TJPlacement placement) {
                TapjoyLog.i(TAG, "onContentReady for placement " + placement.getName());
                placement.showContent();
            }

            @Override
            public void onContentShow(TJPlacement placement) {
                TapjoyLog.i(TAG, "onContentShow for placement " + placement.getName());

                // Best Practice: We recommend calling getCurrencyBalance as often as possible so the user's balance is always up-to-date.
                Tapjoy.getCurrencyBalance(MainActivity.this);
            }

            @Override
            public void onContentDismiss(TJPlacement placement) {
                TapjoyLog.i(TAG_TAPJOY, "onContentDismiss for placement " + placement.getName());

                // Best Practice: We recommend calling getCurrencyBalance as often as possible so the user's balance is always up-to-date.
                Tapjoy.getCurrencyBalance(MainActivity.this);
            }

            @Override
            public void onPurchaseRequest(TJPlacement placement, TJActionRequest request, String productId) {

            }

            @Override
            public void onRewardRequest(TJPlacement placement, TJActionRequest request, String itemId, int quantity) {

            }
        };

        offerwallPlacement = new TJPlacement(this, Const.TAP_OFFER_WALL, placementListener);
        // Construct TJPlacement to show Offers web view from where users can download the latest offers for virtual currency.
        offerwallPlacement.requestContent();
    }

    @Override
    public void startFragmentHackWhatsApp() {
        replaceFragment(HackWhatsAppFragment.newInstance(), true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Tapjoy.onActivityStart(this);
    }

    @Override
    protected void onStop() {
        Tapjoy.onActivityStop(this);
        super.onStop();
    }

    @Override
    public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
        Log.i(TAG_TAPJOY, "currencyName: " + currencyName);
        Log.i(TAG_TAPJOY, "balance: " + balance);

    }

    @Override
    public void onGetCurrencyBalanceResponseFailure(String error) {
        Log.i(TAG_TAPJOY, "getCurrencyBalance error: " + error);
    }

}
