package com.mvp.arm.commonmodule.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity 容器
 *
 * @author Gjm
 * @date 2018/5/22
 */
public final class ActivityContainer {
    private static ActivityContainer sInstance;

    public static ActivityContainer get(){
        if(sInstance == null){
            synchronized (ActivityContainer.class){
                if(sInstance == null){
                    sInstance = new ActivityContainer();
                }
            }
        }
        return sInstance;
    }

    private List<Activity> mActivityList;

    private ActivityContainer(){
        mActivityList = new ArrayList<>();
    }

    public void add(Activity activity){
        mActivityList.add(activity);
    }

    public void remove(Activity activity){
        mActivityList.remove(activity);
    }

    /**
     * 清除所有activity
     */
    public void finishAll(){
        for (Activity activity : mActivityList){
            if(activity != null && !activity.isFinishing()){
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
