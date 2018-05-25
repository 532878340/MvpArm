package com.mvp.arm.commonmodule.util.sharedpreference;

import android.content.Context;

import com.mvp.arm.commonmodule.app.BaseApplication;

/**
 *
 * @author Zijin
 * @date 2017/7/27
 * SharedPreference管理
 */

@SuppressWarnings("unused")
public class SharedPreferenceManager {
    private static volatile SharedPreferenceManager mInstance;

    private SecuritySharedPreference mSharedPreference;
    private SecuritySharedPreference.Editor mEditor;

    private SharedPreferenceManager() {
        mSharedPreference = new SecuritySharedPreference(BaseApplication.get(), "config", Context.MODE_PRIVATE);
        mEditor = mSharedPreference.edit();
    }

    public static SharedPreferenceManager get() {
        if (mInstance == null) {
            synchronized (SharedPreferenceManager.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferenceManager();
                }
            }
        }

        return mInstance;
    }

    /**********************  获取数据  ************************/

    public String getString(String key) {
        return mSharedPreference.getString(key, "");
    }

    public int getInt(String key) {
        return mSharedPreference.getInt(key, 0);
    }

    public long getLong(String key) {
        return mSharedPreference.getLong(key, 0);
    }

    public boolean getBoolean(String key) {
        return mSharedPreference.getBoolean(key, false);
    }

    public float getFloat(String key) {
        return mSharedPreference.getFloat(key, 0);
    }

    /**********************  设置数据  ************************/

    public void setString(String key,String value){
        mEditor.putString(key,value).commit();
    }

    public void setInt(String key,int value){
        mEditor.putInt(key,value).commit();
    }

    public void setLong(String key,long value){
        mEditor.putLong(key,value).commit();
    }

    public void setBoolean(String key,boolean value){
        mEditor.putBoolean(key,value).commit();
    }

    public void setFloat(String key,float value){
        mEditor.putFloat(key,value).commit();
    }
}
