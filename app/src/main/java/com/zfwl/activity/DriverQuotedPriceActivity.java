package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.DriverQuotedModel;
import com.zfwl.mvp.logistics.DriverQuotedMvpView;
import com.zfwl.mvp.logistics.DriverQuotedPresenter;
import com.zfwl.util.ViewHub;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

public class DriverQuotedPriceActivity extends AppCompatActivity implements DriverQuotedMvpView {

    @BindView(R.id.title_bar)
    BGATitleBar titleBar;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.del)
    ImageView del;
    @BindView(R.id.car_weight)
    EditText etCarWeight;
    @BindView(R.id.tv_price_by_weight)
    TextView tvPriceByWeight;
    @BindView(R.id.tv_price_by_car)
    TextView tvPriceByCar;
    @BindView(R.id.price)
    EditText tvPrice;
    @BindView(R.id.tv_price_total)
    TextView tvPriceTotal;
    @BindView(R.id.submit)
    Button submit;

    private int carCount = 1;
    private double carWeight = 1;
    private boolean isCarCountPrice = false;
    private double price = 0;

    private DriverQuotedPresenter mPresenter;
    private String logisticsId;

    public static void launch(Context context, String id) {
        Intent intent = new Intent(context, DriverQuotedPriceActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    private void initView() {
        tvCarNum.setText(carCount+"");
        if (etCarWeight.getText().toString().length()==0){
            etCarWeight.setText("1");
        }
        carWeight = Double.parseDouble(etCarWeight.getText().toString());
        if (tvPrice.getText().toString().length()==0){
            tvPrice.setText("1");
        }
        price = Double.parseDouble(tvPrice.getText().toString());
//        etCarWeight.setText(carWeight+"");
//        tvPrice.setText(price+"");
        double total = 0;
        Drawable img,img1;
        Resources res = getResources();
        img = res.getDrawable(R.drawable.cb_un_check_1);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        img1 = res.getDrawable(R.drawable.cb_check_1);
        img1.setBounds(0, 0, img1.getMinimumWidth(), img.getMinimumHeight());

        if (isCarCountPrice) {
            tvPriceByWeight.setCompoundDrawables(img,null,null,null);
            tvPriceByWeight.setTextColor(Color.parseColor("#333333"));
            tvPriceByCar.setCompoundDrawables(img1,null,null,null);
            tvPriceByCar.setTextColor(Color.parseColor("#1379fa"));
            total = carCount*price;
        } else {
            tvPriceByWeight.setCompoundDrawables(img1,null,null,null);
            tvPriceByWeight.setTextColor(Color.parseColor("#1379fa"));
            tvPriceByCar.setCompoundDrawables(img,null,null,null);
            tvPriceByCar.setTextColor(Color.parseColor("#333333"));
            total = carWeight*price;
        }
        tvPriceTotal.setText("共计："+total+"元");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_quoted_price);
        ButterKnife.bind(this);
        mPresenter = new DriverQuotedPresenter(this);
        mPresenter.attachView(this);
        logisticsId = getIntent().getStringExtra("id");

        etCarWeight.addTextChangedListener(watcher);
        tvPrice.addTextChangedListener(watcher);
        titleBar.setDelegate(new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                onBackPressed();
            }
        });
        initView();
    }
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            initView();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onAddSuccess(DriverQuotedModel d) {
        DriverQuotedSuccessActivity.launch(this);
        finish();
    }

    @Override
    public void onAddFailed(String errorMsg) {
        ViewHub.showLongToast(this, errorMsg);
    }

    @OnClick({R.id.add, R.id.del, R.id.tv_price_by_weight, R.id.tv_price_by_car, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                carCount++;
                initView();
                break;
            case R.id.del:
                carCount--;
                if (carCount<=0) {
                    carCount = 1;
                }
                initView();
                break;
            case R.id.tv_price_by_weight:
                isCarCountPrice = false;
                initView();
                break;
            case R.id.tv_price_by_car:
                isCarCountPrice = true;
                initView();
                break;
            case R.id.submit:
                mPresenter.add(logisticsId,
                        carCount,
                        carWeight,
                        isCarCountPrice?1:0,
                        price);
                break;
        }
    }
}
