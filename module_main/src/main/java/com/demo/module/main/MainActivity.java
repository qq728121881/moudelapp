package com.demo.module.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.demo.module.common.base.BaseActivity;
import com.demo.module.common.base.BaseFragment;
import com.demo.module.common.base.BaseFragmentAdapter;
import com.demo.module.common.base.ViewManager;
import com.demo.module.common.utils.ACache;
import com.demo.module.common.utils.ToastUtils;
import com.demo.module.common.widget.NoScrollViewPager;
import com.guiying.module.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>类说明</p>
 *
 * @version V1.2.0
 * @name MainActivity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long mExitTime = 0;

    private NoScrollViewPager mPager;
    ACache aCache;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };
    private BottomNavigationView navigation;
    private BaseFragmentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aCache=ACache.get(this);
        aCache.put("test","1");

        mPager = (NoScrollViewPager) findViewById(R.id.container_pager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        initViewPager();

    }

    @Override
    public void onClick(View view) {

    }


    private void initViewPager() {
        Fragment fragment =(Fragment)ARouter.getInstance().build("/home/homefragment").navigation();
        Fragment fragment1 =(Fragment)ARouter.getInstance().build("/message/messagefragment").navigation();
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(fragment );
        mFragments.add(fragment1 );
        mAdapter = new BaseFragmentAdapter(mFragments,getSupportFragmentManager());
        mPager.setPagerEnabled(false);
        mPager.setAdapter(mAdapter);

    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShortToast(getString(R.string.app_exit_hint));
                mExitTime = System.currentTimeMillis();
            } else {
                ViewManager.getInstance().exitApp(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
