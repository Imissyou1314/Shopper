package com.boyapp.missyou.shopper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.boyapp.missyou.shopper.R;
import com.boyapp.missyou.shopper.entity.OrderDetail;
import com.boyapp.missyou.shopper.entity.Orders;
import com.boyapp.missyou.shopper.presenter.OrderDetailPresenter;
import com.boyapp.missyou.shopper.presenter.impl.OrderDetaliPresenterImpl;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.OrderDetailView;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MissYou on 2016/12/12.
 */
public class OrderDetailActivity extends Activity implements OrderDetailView {

    @BindView(R.id.order_detail_userName)
    TextView userName;
    @BindView(R.id.order_detail_address)
    TextView userAddress;
    @BindView(R.id.order_detail_phone)
    TextView userPhone;
    @BindView(R.id.order_detail_totle)
    TextView totlePrice;
    @BindView(R.id.order_detail_goods)
    RecyclerView goodsListView;

    private OrderDetailPresenter mOrderDetailPresenter;
    private Orders orders;
    private List<OrderDetail> mOrderDetail;
    private CommonAdapter<OrderDetail> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

        initData();

        initView();
    }

    private void initView() {
        userName.setText(userName.getText().toString() + orders.getName());
        userAddress.setText(userAddress.getText().toString() + orders.getAddress());
        userPhone.setText(userPhone.getText().toString() + orders.getPhone());
        totlePrice.setText(totlePrice.getText().toString() + orders.getTotalPrice());

        mAdapter = new CommonAdapter<OrderDetail>(this, R.layout.item_order_detail, mOrderDetail) {
            @Override
            protected void convert(ViewHolder holder, OrderDetail orderDetail, int position) {
                holder.setText(R.id.item_order_detail_goodname, orderDetail.getGoodName());
                holder.setText(R.id.item_order_detail_price, "单价 :" + orderDetail.getPrice() + "");
                holder.setText(R.id.item_order_detail_totle, "合计 :" + orderDetail.getTotalPrice() + "");
                holder.setText(R.id.item_order_detail_number, "数量 :" + orderDetail.getNumber() + "");
            }
        };

        goodsListView.setAdapter(mAdapter);
        goodsListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        mOrderDetailPresenter = new OrderDetaliPresenterImpl(this);
        mOrderDetail = new ArrayList<>();
        if (null != getIntent().getStringExtra("orders")) {
            orders = GsonUtils.parseJsonWithGson(getIntent().getStringExtra("orders"), Orders.class);

            mOrderDetailPresenter.initData(orders.getId());
        } else {
            setErrorView("获取的对象不存在");
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

    @Override
    public void setInitView(List<OrderDetail> orderDetailList) {
        mOrderDetail.addAll(orderDetailList);
        mAdapter.notifyDataSetChanged();
    }
}
