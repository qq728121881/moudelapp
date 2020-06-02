package com.zzn.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.demo.home.message.R;
import com.demo.module.common.utils.ACache;
import com.demo.module.common.utils.ToastUtils;


public class MainActivity extends AppCompatActivity {
    ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aCache=ACache.get(this);
        String test = aCache.getAsString("test");
        if(TextUtils.isEmpty(test)){
            return;
        }
        ToastUtils.showLongToast(test);
    }
}
