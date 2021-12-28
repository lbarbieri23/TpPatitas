package com.dds.services.hogares;

import com.dds.services.hogares.model.Hogar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HogarService {

    private IHogarService service;

    public HogarService(String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.refugiosdds.com.ar/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(IHogarService.class);
    }

    //FIXME EXCEPTIONS
    private HogarResponse getPage(Integer page) throws Exception {
        Call<HogarResponse>  command = service.getHogares(page);
        retrofit2.Response<HogarResponse> response = command.execute();
        if (!response.isSuccessful()) {
            throw new Exception("No se pudieron obtener los hogares");
        }
        return response.body();
    }

    private Integer getMaxPages(Integer registrosTotales, Integer registrosPorPagina) {
        if (registrosPorPagina == 0) {
            return 0;
        }
        Integer maxPages = registrosTotales / registrosPorPagina;
        if (maxPages * registrosPorPagina < registrosTotales) {
            maxPages++;
        }
        return  maxPages;
    }

    public List<Hogar> getHogares() throws Exception {
        List<Hogar> results = new ArrayList<>();

        HogarResponse response = getPage(1);
        results.addAll(response.getHogares());
        Integer maxPages = getMaxPages(response.getTotal(), response.getHogares().size());
        for (int page = 2; page <= maxPages ; page++) {
            response = getPage(page);
            results.addAll(response.getHogares());
        }

        return  results;
    }



}