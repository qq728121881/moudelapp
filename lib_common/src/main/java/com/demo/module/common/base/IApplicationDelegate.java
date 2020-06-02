package com.demo.module.common.base;

import android.support.annotation.Keep;

/**
 * <p>类说明</p>
 *
 * @version V2.8.3
 * @name ApplicationDelegate
 */
@Keep
public interface IApplicationDelegate {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

}
