package com.na2te.network.di

import com.na2te.data.BuildConfig
import com.na2te.network.api.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {
    private const val TIME_OUT = 5000L
    private const val BASE_URL = BuildConfig.BACKEND_URL

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        networkJson: Json
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        /*
        * "application/json"에서 json은 Http 통신에서 데이터 형식이 Json임을 명시하는 미디어 타입
        * application 부분은 서버 간 통신을 위한 특별한 데이터 포맷임을 나타냄
        * MediaType이란 Http 요청/응답에서 콘텐츠의 포맷을 나타냄
        * 그래서 문자열로 전달한 "application/json"이라는 걸 application/json의 MediaType으로 변환
        * */
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        /*
        Json에서 객체의 속성 값으로 모르는 값이 들어왔을 때 Serialization Exception 터트리지 않고
        그냥 무시함
        디폴트 값은 fasle
         */
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): TestApi = retrofit.create(TestApi::class.java)
}
