package com.benmu.umeng;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.eros.framework.utils.DebugableUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Umeng 埋点 Module
 * Created by liuyuanxiao on 2018/7/18.
 */
@WeexModule(name = "bmUMAnalytics", lazyLoad = true)
public class UmengModule extends WXModule {
    @JSMethod
    public void initUM(String umengAppKey) {
        Context context = mWXSDKInstance.getContext();
        if (!TextUtils.isEmpty(umengAppKey)) {
            UMConfigure.init(context, umengAppKey, "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
            MobclickAgent.setDebugMode(DebugableUtil.isDebug());
            MobclickAgent.openActivityDurationTrack(false);
            MobclickAgent.setCatchUncaughtExceptions(!DebugableUtil.isDebug());
            MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        }
    }

    @JSMethod(uiThread = true)
    public void beginPage(String name) {
        UmengUtils.umengOnActivityResume(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void endPage(String name) {
        UmengUtils.umengOnActivityPause(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void event(String name) {
        UmengUtils.umengClick(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void beginEvent(String name) {
        UmengUtils.umengOnPageResume(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void endEvent(String name) {
        UmengUtils.umengOnPagePause(mWXSDKInstance.getContext(), name);
    }


}
