package com.mvp.arm.commonmodule.bus;

import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * RxBus 事件总线
 * 1、事件总线，解耦
 * 2、异常处理
 * 3、允许发送粘性事件
 *
 * @author Gjm
 * @date 2018/3/7
 */
public class RxBus {
    private final FlowableProcessor<Object> mBus;
    private final Map<Class<?>,Object> mStickyEventMap;

    private static volatile RxBus mInstance;

    private RxBus(){
        mBus = PublishProcessor.create().toSerialized();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    public static RxBus get(){
        if(mInstance == null){
            synchronized (RxBus.class){
                if(mInstance == null){
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送事件
     */
    public void post(Object o){
        mBus.onNext(o);
    }

    /**
     * 观察者监听 处理异常
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType){
        return mBus.ofType(eventType)
                .doOnError(throwable -> Logger.e("RxBus处理异常: " + throwable.getMessage()))
                .onErrorResumeNext(Flowable.never());
    }

    /**
     * 发送粘性事件
     */
    public void postSticky(Object o){
        synchronized (mStickyEventMap){
            mStickyEventMap.put(o.getClass(),o);
        }
        post(o);
    }

    /**
     * 粘性事件观察者监听
     */
    public <T> Flowable<T> toFlowableSticky(Class<T> eventType){
        synchronized (mStickyEventMap){
            Flowable<T> flowable = toFlowable(eventType);
            final Object event = mStickyEventMap.get(eventType);
            if(event != null){
                return flowable.mergeWith(Flowable.create(e -> e.onNext(eventType.cast(event)),BackpressureStrategy.BUFFER));
            }else{
                return flowable;
            }
        }
    }

    /**
     * 获取粘性事件
     */
    public <T> T getStickyEvent(Class<T> eventType){
        synchronized (mStickyEventMap){
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    /**
     * 移除粘性事件
     */
    public <T> T removeStickyEvent(Class<T> eventType){
        synchronized (mStickyEventMap){
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    /**
     * 移除所有粘性事件
     */
    public void removeAllStickyEvent(){
        synchronized (mStickyEventMap){
            mStickyEventMap.clear();
        }
    }
}
