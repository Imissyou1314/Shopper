package com.boyapp.missyou.shopper.activity;

import android.app.Activity;
import android.os.Bundle;

import com.boyapp.missyou.shopper.R;
import com.boyapp.missyou.shopper.view.ShopperInfoView;

import butterknife.ButterKnife;

/**
 * Created by MissYou on 2016/12/15.
 */
public class ShopperInfoActivity extends Activity implements ShopperInfoView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_info);
        ButterKnife.bind(this);
    }

    @Override
    public void setErrorView(String errorMsg) {

    }

    @Override
    public void setSuccessView(String successMsg) {

    }
}
