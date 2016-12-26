package com.whapprapphck.view.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.whapprapphck.R;
import com.whapprapphck.other.App;
import com.whapprapphck.other.Const;
import com.whapprapphck.other.di.view.ViewComponent;
import com.whapprapphck.presenter.HackWhatsAppPresenter;
import com.whapprapphck.presenter.Presenter;
import com.whapprapphck.view.activities.ActivityCallback;
import com.whapprapphck.view.cutomviews.PercentView;

import java.util.Hashtable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tikho on 21.12.2016.
 */

public class HackWhatsAppFragment extends BaseFragment implements IHackWhatsappView {

    @Inject
    protected HackWhatsAppPresenter presenter;

    private ActivityCallback activityCallback;

    //private ViewComponent viewComponent;

    @Bind(R.id.percentview)
    public PercentView percentView;


    public static HackWhatsAppFragment newInstance() {
        HackWhatsAppFragment myFragment = new HackWhatsAppFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        presenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hack_whatsapp, container, false);
        ButterKnife.bind(this, view);

        presenter.onCreateView(savedInstanceState);

        return view;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setProgress(int progress) {
        percentView.setPercentage(progress);
    }


    @Override
    public void showPopUpDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_dialog);

        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(view -> {
            dialog.dismiss();
            showOfferWall();

        });

        dialog.show();
    }


    private void showOfferWall() {
        activityCallback.showOfferWall();
    }

}
