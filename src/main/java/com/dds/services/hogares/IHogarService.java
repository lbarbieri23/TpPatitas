package com.dds.services.hogares;

import com.dds.services.whatsapp.ChatApiImpl.ChatApiResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface IHogarService {

    @GET("hogares?")
    Call<HogarResponse> getHogares(@Query ("offset") Integer offset);

}
