package com.mvp.arm.mall.di.module;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.manager.constants.Configs;
import com.mvp.arm.mall.constant.Constant;
import com.mvp.arm.mall.model.MallRepositoryManager;
import com.mvp.arm.mall.model.db.DbHelper;
import com.mvp.arm.mall.model.http.HttpHelper;
import com.mvp.arm.mall.model.http.api.MallService;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * mall httpmodule
 *
 * @author Gjm
 * @date 2018/5/25
 */
@Module
public class HttpModule {
    @Provides
    @ApplicationScope
    @Named("mall")
    Retrofit provideRetrofit(@Named("mall") OkHttpClient client) {
        return createRetrofit(client, Constant.BASE_URL);
    }

    @Provides
    @ApplicationScope
    MallService provideService(@Named("mall") Retrofit retrofit) {
        return retrofit.create(MallService.class);
    }

    @ApplicationScope
    @Provides
    @Named("mall")
    OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Configs.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Configs.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(chain -> {
                    Request origin = chain.request();
                    Request request = origin.newBuilder()
                            .method(origin.method(), origin.body())
                            .build();

                    return chain.proceed(request);
                })
                .build();
    }

    /**
     * 创建对应的Retrofit，根据retrofit返回ApiService
     */
    protected Retrofit createRetrofit(OkHttpClient client, String rootUrl) {
        return new Retrofit.Builder()
                .baseUrl(rootUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
