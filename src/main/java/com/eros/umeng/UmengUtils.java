package com.eros.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by liuyuanxiao on 2017/1/22.
 */

public class UmengUtils {
    /**
     * activity的友盟统计 & 访问时长,放在onResume方法里,注意不要重复添加此方法,并且如果父类调用了此方法,那么子类就不能在调用
     * 这个方法了
     *
     * @param activity 当前activity对象,传this
     * @param name     当前activity的页面描述,目前传空就好了
     */
    public static void umengOnActivityResume(Context activity, String name) {
        if (activity == null)
            return;

        if (name != null) {
            umengClick(activity, name);
        }
        MobclickAgent.onPageStart((name == null) ? activity.getClass().getName() : name);
        MobclickAgent.onResume(activity);
    }

    /**
     * activity的友盟统计 & 访问时长,放在onPause方法里,注意不要重复添加此方法,并且如果父类调用了此方法,那么子类就不能在调用
     * 这个方法了
     *
     * @param activity 当前activity对象,传this
     * @param name     当前activity的页面描述,目前传空就好了
     */
    public static void umengOnActivityPause(Context activity, String name) {
        if (activity == null)
            return;
        MobclickAgent.onPageEnd((name == null) ? activity.getClass().getName() : name);
        MobclickAgent.onPause(activity);
    }

    /**
     * fragment的友盟统计 & 访问时长,放在fragment的onResume中,这个方法不要重复添加,如果父类调用了此方法,那么子类无需再次调用
     *
     * @param fragment 传单前的fragment,传this就好了
     * @param name     目前传空
     */
    public static void umengOnPageResume(Context fragment, String name) {
        if (fragment == null)
            return;
        MobclickAgent.onPageStart((name == null) ? fragment.getClass().getName() : name);
    }

    /**
     * fragment的友盟统计 & 访问时长,放在fragment的onPause中,这个方法不要重复添加,如果父类调用了此方法,那么子类无需再次调用
     *
     * @param fragment 传单前的fragment,传this就好了
     * @param name     目前传空
     */
    public static void umengOnPagePause(Context fragment, String name) {
        if (fragment == null)
            return;
        MobclickAgent.onPageEnd((name == null) ? fragment.getClass().getName() : name);
    }

    /**
     * @param context
     * @param pointName 需要埋的点(点击事件）的描述,写在Umeng类中.
     */
    public static void umengClick(Context context, String pointName) {
        MobclickAgent.onEvent(context, pointName);
    }
}
