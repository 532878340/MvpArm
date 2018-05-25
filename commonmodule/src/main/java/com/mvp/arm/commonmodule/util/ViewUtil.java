package com.mvp.arm.commonmodule.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * View工具类
 *
 * @author Gjm
 * @date 2018/2/24
 */
public class ViewUtil {

    /**
     * 获取view内容
     * @param textView view
     * @return 内容
     */
    public static String getText(TextView textView){
        return textView == null ? "" : textView.getText().toString().trim();
    }

    /**
     * 判断view是否为空
     * @param view view
     * @return result
     */
    public static boolean isEmpty(View view){
        return view == null || (view instanceof TextView && TextUtils.isEmpty(getText((TextView) view)));
    }
}
