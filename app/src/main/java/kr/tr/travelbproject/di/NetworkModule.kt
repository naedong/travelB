package kr.tr.travelbproject.di

import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.tr.data.api.BusanApiService
import kr.tr.data.api.SubWayApiSerivce
import kr.tr.data.api.TourismApiService
import kr.tr.travelbproject.BuildConfig
import okhttp3.Call
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString.Companion.encode
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 * TravelBAppProject
 * Created by Naedong
 * Date: 2023-06-28
 * Time: 오전 11:10
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                    .newBuilder()
                    .build()

                val originalHttpUrl: HttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("serviceKey",
                      BuildConfig.API_KEY
                    )
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val newRequest: Request = requestBuilder.build()

                val client = OkHttpClient.Builder()
                    .dispatcher(Dispatcher().apply { maxRequestsPerHost = 1 })
                    .build()

                client.newCall(newRequest).enqueue(object : okhttp3.Callback {

                    override fun onFailure(call: Call, e: IOException) {

                        Log.e("travelB", "onFailure $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.e("travelB", "onResponseCheck $response")

                    }
                })
                 chain.proceed(newRequest)

            }
            .build()
    }
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideTravelBService(retrofitClient: Retrofit): BusanApiService {
        return retrofitClient.create(BusanApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTravelBTourismService(retrofitClient: Retrofit): TourismApiService {
        return retrofitClient.create(TourismApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTravelBSubWayService(retrofitClient: Retrofit): SubWayApiSerivce {
       return retrofitClient
            .newBuilder()
            .baseUrl(BuildConfig.URL_SUbWAY_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(
                SubWayApiSerivce::class.java
            )
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
         return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}