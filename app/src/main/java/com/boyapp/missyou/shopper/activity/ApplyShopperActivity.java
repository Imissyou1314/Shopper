package com.boyapp.missyou.shopper.activity;

import android.app.Activity;
import android.os.Bundle;

import com.boyapp.missyou.shopper.R;
import com.boyapp.missyou.shopper.view.ApplyShopperView;

import butterknife.ButterKnife;

/**
 * Created by MissYou on 2016/12/15.
 */
public class ApplyShopperActivity  extends Activity implements ApplyShopperView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_shopper);

        ButterKnife.bind(this);
    }

    @Override
    public void setErrorView(String errorMsg) {

    }

    @Override
    public void setSuccessView(String successMsg) {

    }
}
