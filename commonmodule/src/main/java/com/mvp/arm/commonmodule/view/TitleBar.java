package com.mvp.arm.commonmodule.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvp.arm.commonmodule.R;
import com.mvp.arm.commonmodule.util.DimensUtil;

/**
 * 标题栏
 *
 * @author Gjm
 * @date 2018/4/19
 */
public class TitleBar extends RelativeLayout{
    /**
     * 标题栏
     */
    private TextView mTitleTv;
    /**
     * 左侧按钮
     */
    private TextView mLeftTv;
    /**
     * 右侧按钮
     */
    private TextView mRightTv;

    private Context mCtx;

    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        View.inflate(context, R.layout.layout_titlebar,this);

        setMinimumHeight(DimensUtil.ptToPx(context,100));
        initView();
    }

    private void initView(){
        mTitleTv = findViewById(R.id.tvTitle);
        mLeftTv = findViewById(R.id.tvTitleLeft);
        mRightTv = findViewById(R.id.tvTitleRight);
        if(getBackground() == null){
            setBackgroundResource(R.color.colorPrimary);
        }
    }

    /**
     * base
     */
    public TextView getLeftCtv(){
        return mLeftTv;
    }

    public TextView getRightCtv(){
        return mRightTv;
    }

    public TextView getTitleTv(){
        return mTitleTv;
    }

    public TitleBar setBgColor(int colorRes){
        setBackgroundColor(getResources().getColor(colorRes));
        return this;
    }

    public TitleBar setBgResource(int res){
        setBackgroundResource(res);
        return this;
    }

    /**
     * 标题
     */
    public TitleBar setTitle(int title){
        return setTitle(mCtx.getString(title));
    }

    public TitleBar setTitle(CharSequence charSequence){
        setVisibility(VISIBLE);
        mTitleTv.setText(charSequence);
        return this;
    }

    public TitleBar setTitleTextColor(int color){
        mTitleTv.setTextColor(color);
        return this;
    }

    public TitleBar setTitleSizePt(int size){
        return setTitleSizePt(TypedValue.COMPLEX_UNIT_PT,size);
    }

    public TitleBar setTitleSizePt(int unit,int size){
        mTitleTv.setTextSize(unit,size);
        return this;
    }

    /**
     * 左侧
     */
    public TitleBar setLeftCtv(int labelRes){
        return setLeftCtv(mCtx.getString(labelRes));
    }

    public TitleBar setLeftCtv(CharSequence charSequence){
        mLeftTv.setVisibility(VISIBLE);
        mLeftTv.setText(charSequence);
        return this;
    }

    public TitleBar setLeftCtvVisibility(int visibility){
        mLeftTv.setVisibility(visibility);
        return this;
    }

    public TitleBar setLeftTextColor(int color){
        mLeftTv.setTextColor(color);
        return this;
    }

    public TitleBar setLeftCtvSizePt(int size){
        return setLeftCtvSizePt(TypedValue.COMPLEX_UNIT_PT,size);
    }

    public TitleBar setLeftCtvSizePt(int unit,int size){
        mLeftTv.setTextSize(unit,size);
        return this;
    }

    public TitleBar withLeftIndicate(int... drawableRes){
        int drawable = 0;

        int len = drawableRes.length;
        switch (len){
            case 0://默认显示返回按钮
                drawable = R.drawable.icon_back;
                break;
            case 1://设置图片
                drawable = drawableRes[0];
                break;
        }
        mLeftTv.setCompoundDrawablesWithIntrinsicBounds(drawable,0,0,0);
        return this;
    }

    public TitleBar setOnLeftClickListener(OnClickListener listener){
        mLeftTv.setOnClickListener(listener);
        return this;
    }

    /**
     * 右侧
     */
    public TitleBar setRightCtv(int res){
        return setRightCtv(mCtx.getString(res));
    }

    public TitleBar setRightCtv(CharSequence charSequence){
        mRightTv.setVisibility(VISIBLE);
        mRightTv.setText(charSequence);
        return this;
    }

    public TitleBar withRightIndicate(int drawableRes){
        mRightTv.setCompoundDrawablesWithIntrinsicBounds(0,0,drawableRes,0);
        return this;
    }

    public TitleBar setRightCtvVisibility(int visibility){
        mRightTv.setVisibility(visibility);
        return this;
    }

    public TitleBar setRightTextColor(int color){
        mRightTv.setTextColor(color);
        return this;
    }

    public TitleBar setRightCtvSizePt(int size){
        return setLeftCtvSizePt(TypedValue.COMPLEX_UNIT_PT,size);
    }

    public TitleBar setRightCtvSizePt(int unit,int size){
        mRightTv.setTextSize(unit,size);
        return this;
    }

    public TitleBar setOnRightClickListener(OnClickListener listener){
        mRightTv.setOnClickListener(listener);
        return this;
    }
}
