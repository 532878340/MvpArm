package com.mvp.arm.commonmodule.util;

import java.io.Closeable;

/**
 * Description: 关闭工具
 * @author Zijin
 * @date 2017/8/1
 */

public class CloseUtils {
    public static void close(Closeable ...target){
        try {
            if(target != null){
                int len = target.length;
                for(int i = 0 ;i < len ; i ++){
                    if(target[i] != null){
                        target[i].close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
