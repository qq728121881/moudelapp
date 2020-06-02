package com.demo.module.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.demo.module.common.base.BaseActivity;
import com.demo.module.common.utils.ToastUtils;
import com.demo.module.main.MainActivity;
import com.demo.module.main.contract.LoginContract;
import com.demo.module.main.presenter.LoginPresenter;
import com.guiying.module.main.R;
import com.uber.autodispose.AutoDisposeConverter;


public class LoginActivity extends BaseActivity implements LoginContract.View {


    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter();
        presenter.attachView(this);

        findViewById(R.id.login_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLongToast("888");

                presenter.login("17778157270","123456");

            }
        });

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public AutoDisposeConverter bindAutoDispose() {
        return null;
    }

    @Override
    public void onSuccess(String bean) {


        ToastUtils.showLongToast(bean);


        startActivity(new Intent(this, MainActivity.class));
    }


}
