package com.boyapp.missyou.shopper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.boyapp.missyou.shopper.R;
import com.boyapp.missyou.shopper.presenter.AddGoodsPresenter;
import com.boyapp.missyou.shopper.presenter.impl.AddGoodsPresenterImpl;
import com.boyapp.missyou.shopper.view.AddGoodView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MissYou on 2016/12/12.
 */
public class AddGoodsActivity extends Activity implements AddGoodView {


    @BindView(R.id.add_good_name)
    EditText goodName;
    @BindView(R.id.add_good_number)
    EditText number;
    @BindView(R.id.add_good__default_number)
    EditText defaultNumber;
    @BindView(R.id.add_good_price)
    EditText price;
    @BindView(R.id.add_good_desc)
    EditText goodIntroduce;
    @BindView(R.id.add_good_image)
    ImageView uploadFile;

    private AddGoodsPresenter mAddGoodsPresenter;
    private Integer shopId;     //商店ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mAddGoodsPresenter = new AddGoodsPresenterImpl(this);
        shopId = getIntent().getIntExtra("shopId", 2);
        if (shopId == 0) {
            setErrorView("获取商店错误");
        }
    }

    @Override
    public void setErrorView(String errorMsg) {
        Logger.e(errorMsg);

    }

    @Override
    public void setSuccessView(String successMsg) {
        Logger.d(successMsg);
        this.finish();
    }

    @OnClick(R.id.add_good_submit)
    public void submit() {
        mAddGoodsPresenter.addGoods(
                goodName.getText().toString(),
                number.getText().toString() ,
                defaultNumber.getText().toString(),
                goodIntroduce.getText().toString(),
                shopId,
                price.getText().toString());
    }
}
