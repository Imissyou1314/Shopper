package com.boyapp.missyou.shopper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.boyapp.missyou.shopper.activity.OrderDetailActivity;
import com.boyapp.missyou.shopper.activity.ShopActivity;
import com.boyapp.missyou.shopper.entity.Orders;
import com.boyapp.missyou.shopper.presenter.OrdersPresenter;
import com.boyapp.missyou.shopper.presenter.impl.OrdersPresenterImpl;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.MainView;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {


    @BindView(R.id.main_order_list)
    public RecyclerView orderListView;     //订单列表

    private List<Orders> mOrdersList;
    private CommonAdapter mAdapter;

    private OrdersPresenter mPresenter;
    private View.OnClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initData();

        initView();
    }

    private void initView() {

        mAdapter = new CommonAdapter<Orders>(this, R.layout.item_order, mOrdersList) {
            @Override
            protected void convert(ViewHolder holder, final Orders orders, int position) {
                holder.setText(R.id.order_address, orders.getAddress());
                holder.setText(R.id.order_user_name, orders.getName());
                holder.setText(R.id.order_user_phone, orders.getPhone());
                holder.setText(R.id.order_totle_price, orders.getTotalPrice() + "");
                holder.setOnClickListener(R.id.order_rece_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        receOrders(orders.getId());
                    }
                });
                holder.setOnClickListener(R.id.order_refuse_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refuseOrders(orders.getId());
                    }
                });
                holder.setOnClickListener(R.id.order_send_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendOrders(orders.getId());
                    }
                });
            }
        };

        orderListView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(getOnItemClick());
        orderListView.setLayoutManager(new LinearLayoutManager(this));

    }

    private MultiItemTypeAdapter.OnItemClickListener getOnItemClick() {
        return new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Logger.d(position + "");
                toOrderDetail(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        };
    }

    /**
     * 跳转到OrderDetail
     * @param position
     */
    private void toOrderDetail(int position) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("orders", GsonUtils.getJsonStr(mOrdersList.get(position)));
        startActivity(intent);
    }

    /**
     * 配送
     */
    private void sendOrders(Integer ordersId) {
        mPresenter.sendOrders(ordersId);
        Logger.d("sendOrders" + ordersId);
    }

    /**
     * 拒绝接单
     */
    private void refuseOrders(Integer ordersId) {
        mPresenter.refuseOrders(ordersId);
        Logger.d("refuseOrders" + ordersId);
    }

    /**
     * 接单
     * @param ordersId
     */
    private void receOrders(Integer ordersId) {
        mPresenter.receOrders(ordersId);
        Logger.d("receOrders" + ordersId);
    }


    private void initData() {
        mOrdersList = new ArrayList<>();
        mPresenter = new OrdersPresenterImpl(this);
        mPresenter.initData();
    }

    @OnClick(R.id.main_shop)
    void goShop() {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_home)
    void goHome() {
        Logger.d("刷新数据");
        initData();
    }

    @OnClick(R.id.main_user)
    void goUser() {
//        Intent intent = new Intent(this,)
    }

    @Override
    public void setInitView(List ordersList) {
        if (null != ordersList && ordersList.size() != 0) {
            mOrdersList.clear();
            mOrdersList.addAll(ordersList);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setErrorView(String errorMsg) {
        Logger.d(errorMsg);
    }

    @Override
    public void setSuccessView(String successMsg) {
        Logger.d(successMsg);
    }
}
