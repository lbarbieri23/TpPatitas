package com.dds.services.whatsapp.ChatApiImpl;

import retrofit2.Call;
import retrofit2.http.*;

public interface IChatApiService {

    @FormUrlEncoded
    @POST("sendFile?")
    Call<ChatApiResponse> sendFile(@Query("token") String token, @Field("phone") String phone, @Field("body") String linkImage, @Field("filename") String fileName, @Field("caption") String text );

    @FormUrlEncoded
    @POST("sendMessage?")
    Call<ChatApiResponse> sendText(@Query ("token") String token, @Field("phone") String phone, @Field("caption") String text );
}
