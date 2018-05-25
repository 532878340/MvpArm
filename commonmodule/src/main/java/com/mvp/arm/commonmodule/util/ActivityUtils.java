package com.mvp.arm.commonmodule.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mvp.arm.commonmodule.R;

import java.lang.reflect.Field;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.KITKAT;

/**
 *
 * @author Zijin
 * @date 2017/7/25
 */
public final class ActivityUtils {
    public static void startAct(Context ctx,Class<?> clazz){
        startAct(ctx,clazz,null,false);
    }

    /**
     * Activity跳转
     */
    public static void startAct(Context ctx, Class<?> clazz, Bundle bundle,boolean finish){
        Intent intent = new Intent(ctx,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        ctx.startActivity(intent);
        if(ctx instanceof Activity){
            ((Activity)ctx).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            if(finish){
                ((Activity)ctx).finish();
            }
        }
    }

    /**
     * 解决InputMethodManager造成的内存泄漏问题
     * @param destContext 上下文
     */
    public static void fixInputMethodManagerLeak(Context destContext) {

        if (SDK_INT < KITKAT || SDK_INT > 22) {
            return;
        }
        if (destContext == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f == null) {
                    return;
                }
                f.setAccessible(true);
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    // 被InputMethodManager持有引用的context是想要目标销毁的
                    if (v_get.getContext() == destContext) {
                        // 置空，破坏掉path to gc节点
                        f.set(imm, null);
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
