package ru.helen.movie.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.helen.movie.repository.NetworkRepository;
import ru.helen.movie.repository.NetworkRepositoryImpl;
import ru.helen.movie.repository.ThemoviedbAPI;

/**
 * DI Module for AppComponent
 */
@Module
public class AppModule {
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()

                .addInterceptor(interceptor)
                .readTimeout(600, TimeUnit.SECONDS)
                .connectTimeout(600, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Named("token")
    String provideToken() {
        return "71c966801175b02314fe48cb8d4853a2";
    }

    @Provides
    @Named("language")
    String provideLanguage() {
        return "ru";
    }

    @Provides
    @Singleton
    Gson gson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setLenient()
                .create();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ThemoviedbAPI provideThemoviedbAPIService(Retrofit retrofit) {
        return retrofit.create(ThemoviedbAPI.class);
    }

    @Provides
    @Singleton
    NetworkRepository provideNetworkRepository(ThemoviedbAPI themoviedbAPI) {
        return new NetworkRepositoryImpl(themoviedbAPI);
    }

}
