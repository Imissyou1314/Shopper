package com.boyapp.missyou.shopper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.boyapp.missyou.shopper.R;
import com.boyapp.missyou.shopper.entity.Goods;
import com.boyapp.missyou.shopper.presenter.GoodsPresenter;
import com.boyapp.missyou.shopper.presenter.impl.GoodsPresenterImpl;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.GoodView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MissYou on 2016/12/13.
 */
public class GoodActivity extends Activity implements GoodView {

    @BindView(R.id.good_good_name)
    EditText goodName;
    @BindView(R.id.good_good_desc)
    EditText goodDesc;
    @BindView(R.id.good_good_number)
    EditText goodNumber;
    @BindView(R.id.good_good_price)
    EditText goodPrice;
    @BindView(R.id.good_update_time)
    TextView goodCheckTime;

    @BindView(R.id.good_chekc_btn)
    Button checkBtn;

    @BindView(R.id.good_good_image)
    ImageView goodImage;

    private Goods goods;
    private GoodsPresenter mGoodsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initView() {
        goodName.setHint(goods.getName());
        goodName.setHint(goods.getName());
        goodDesc.setHint(goods.getIntroduce());
        goodNumber.setHint(goods.getNumber() + "");
        goodPrice.setHint(goods.getPrice() + "");

        if (goods.getStatus() == 1) {
            checkBtn.setText("下架商品");
        } else {
            checkBtn.setText("上架商品");
        }
//        goodCheckTime.setText(goods.ge);



    }

    private void initData() {
        mGoodsPresenter = new GoodsPresenterImpl(this);
        if (null != getIntent().getStringExtra("goods")) {
            this.goods = GsonUtils.parseJsonWithGson(getIntent().getStringExtra("goods"), Goods.class);
        } else {
            setErrorView("获取Goods对象失败");
        }
    }

    @Override
    public void setErrorView(String errorMsg) {
        Logger.e(errorMsg);
    }

    @Override
    public void setSuccessView(String successMsg) {
        Logger.d(successMsg);
    }

    @OnClick(R.id.good_delete_btn)
    protected void deleteGoods() {
        mGoodsPresenter.deleteGoods(goods.getId());
    }

    @OnClick(R.id.good_chekc_btn)
    protected  void checkGoods() {
            mGoodsPresenter.checkGoods(goods.getId(), goods.getStatus() == 1 ? 2 : 1);
    }

    @OnClick(R.id.good_update_btn)
    protected void updateGoods() {
        goods.setName(goodName.getText().toString());
        goods.setNumber(Integer.valueOf(goodNumber.getText().toString()));
        mGoodsPresenter.updateGoods(goods);
    }
}
