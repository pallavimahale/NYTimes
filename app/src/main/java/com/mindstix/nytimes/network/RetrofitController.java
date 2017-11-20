package com.mindstix.nytimes.network;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindstix.nytimes.network.api.NyTimeApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitController {

    private static RetrofitController retrofitInstance;
    private JacksonConverterFactory jacksonConverterFactory;
    private final OkHttpClient okHttpClient;
    private static final int TIMEOUT = 60;
    private static Retrofit builder;
    private NyTimeApi nyTimeApi;
    private static final String baseUrl = "https://api.nytimes.com";

    public static RetrofitController getInstance() {
        if (retrofitInstance == null) {
            synchronized (RetrofitController.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new RetrofitController();
                }
            }
        }
        return retrofitInstance;
    }

    private RetrofitController() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS).followRedirects(false).build();

        //Make JSON converter
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);
    }

    public NyTimeApi getNyTimesApi() {
        if (nyTimeApi != null) {
            return nyTimeApi;
        }

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(jacksonConverterFactory).build();
        nyTimeApi = retrofit.create(NyTimeApi.class);
        return nyTimeApi;
    }
}
