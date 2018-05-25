package com.mvp.arm.mallmodule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * $desc$
 *
 * @author Gjm
 * @date 2018/5/21
 */
public class MallActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mall);
    }
}
