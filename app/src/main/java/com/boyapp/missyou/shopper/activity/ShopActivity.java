package com.boyapp.missyou.shopper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.boyapp.missyou.shopper.R;
import com.boyapp.missyou.shopper.entity.Goods;
import com.boyapp.missyou.shopper.presenter.ShopPresenter;
import com.boyapp.missyou.shopper.presenter.impl.ShopPresenterImpl;
import com.boyapp.missyou.shopper.utils.Comment;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.ShopView;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MissYou on 2016/12/12.
 * 商店页面
 */

public class ShopActivity  extends Activity implements ShopView {

    @BindView(R.id.shop_good_list)
    RecyclerView goodsListView;
    @BindView(R.id.shop_add_good)
    FloatingActionButton addBtn;

    private ShopPresenter mShopPresenter;
    private List<Goods> mGoodsList;
    private CommonAdapter<Goods> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        initData();

        initView();
    }

    private void initData() {
        mShopPresenter = new ShopPresenterImpl(this);
        mGoodsList = new ArrayList<>();
        mShopPresenter.initData(getIntent().getLongExtra("shopId", 2l));
    }

    private void initView() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddGoods();
            }
        });

        mAdapter = new CommonAdapter<Goods>(this, R.layout.item_goods, mGoodsList) {
            @Override
            protected void convert(ViewHolder holder, Goods goods, int position) {
                holder.setText(R.id.item_goods_name, goods.getName());
                holder.setText(R.id.item_goods_price, "单价 :" + goods.getPrice());
                holder.setText(R.id.item_goods_number, "数量 :" + goods.getNumber());

                Glide
                        .with(holder.getConvertView().getContext())
                        .load(Comment.BASE_URL + Comment.IMAGE_URL + goods.getGoodImg())
                        .into((ImageView) holder.getView(R.id.item_goods_image));

            }
        };

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                toGoodView(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        goodsListView.setAdapter(mAdapter);
        goodsListView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 去商品管理页面
     */
    private void toGoodView(int position) {
        Intent intent = new Intent(this, GoodActivity.class);
        intent.putExtra("goods", GsonUtils.getJsonStr(mGoodsList.get(position)));
        startActivity(intent);
    }

    /**
     * 去添加商品
     */
    private void toAddGoods() {
        startActivity(new Intent(this, AddGoodsActivity.class));
    }


    @Override
    public void setErrorView(String errorMsg) {

    }

    @Override
    public void setSuccessView(String successMsg) {

    }

    @Override
    public void setInitView(List<Goods> goodsList) {
        mGoodsList.addAll(goodsList);
        mAdapter.notifyDataSetChanged();
    }
}
