package com.mvp.arm.commonmodule.di.module;

import com.mvp.arm.commonmodule.di.annotation.qualifier.ApiUrl;
import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.manager.constants.Configs;
import com.mvp.arm.commonmodule.model.http.api.ApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http module
 *
 * @author Gjm
 * @date 2018/01/12
 */

@Module
public class HttpModule {
    @Provides
    @ApplicationScope
    @ApiUrl
    Retrofit provideApiServiceRetrofit(OkHttpClient client) {
        return createRetrofit(client, Configs.BASE_URL);
    }

    @Provides
    @ApplicationScope
    ApiService provideApiService(@ApiUrl Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @ApplicationScope
    @Provides
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
    private Retrofit createRetrofit(OkHttpClient client, String rootUrl) {
        return new Retrofit.Builder()
                .baseUrl(rootUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
