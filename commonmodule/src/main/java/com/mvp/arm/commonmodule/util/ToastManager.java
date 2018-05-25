package com.mvp.arm.commonmodule.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.arm.commonmodule.R;

/**
 * Toast弹窗
 *
 * @author Gjm
 * @date 2018/5/22
 */
public class ToastManager {
    private static volatile ToastManager mInstance;

    public static ToastManager get(){
        if(mInstance != null){
            synchronized (ToastManager.class){
                if(mInstance == null){
                    mInstance = new ToastManager();
                }
            }
        }

        return mInstance;
    }

    private Toast mToast;

    public void showMessage(Context context,CharSequence message){
        View v = View.inflate(context, R.layout.layout_toast, null);
        TextView tv = v.findViewById(R.id.toastTv);
        tv.setText(message);

        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(v);
        mToast.show();
    }
}
