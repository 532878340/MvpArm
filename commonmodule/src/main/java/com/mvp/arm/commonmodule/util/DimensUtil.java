package com.mvp.arm.commonmodule.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author Zijin
 * @date 2017/7/27
 */

public final class DimensUtil {
    /**
     * dp转px
     */
    public static float dpToPx(float value) {
        return Resources.getSystem().getDisplayMetrics().density * value;
    }

    /**
     * sp转px
     */
    public static float spToPx(float value) {
        return Resources.getSystem().getDisplayMetrics().scaledDensity * value;
    }

    /**
     * px转dp
     */
    public static float pxToDp(float value) {
        return value / Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * px转sp
     */
    public static float pxToSp(float value) {
        return value / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * pt转px 此处不能使用 Resources.getSystem()
     */
    public static int ptToPx(Context context, float value){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, metrics);
    }
}
