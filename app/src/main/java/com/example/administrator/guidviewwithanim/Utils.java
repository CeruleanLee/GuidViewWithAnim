package com.example.administrator.guidviewwithanim;

import android.content.Context;

/**
 * Created by Lee_yting on 2016/11/23 0023.
 * 说明：
 */
public class Utils {

    public static int  dp2px(Context context, float dp){
        //getDisplayMetrics()  用来获取屏幕参数
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
