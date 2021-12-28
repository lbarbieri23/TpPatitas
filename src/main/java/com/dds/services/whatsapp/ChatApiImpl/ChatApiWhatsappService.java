package com.dds.services.whatsapp.ChatApiImpl;

import com.dds.services.whatsapp.IWhatsAppService;
import com.dds.services.whatsapp.WhatsappException;
import com.dds.services.whatsapp.WhatsappMessage;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.Path;

import java.io.IOException;

public class ChatApiWhatsappService implements IWhatsAppService {

    private String instance;
    private String token;
    private IChatApiService service;

    public ChatApiWhatsappService(String instance, String token) {
        this.instance = instance;
        this.token = token;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chat-api.com/" + instance + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IChatApiService.class);
    }

    @Override
    public Boolean enviarMensaje(WhatsappMessage message) throws WhatsappException {
        if (message.getImage() == null) {
            return enviarMensajeTexto(message);
        } else {
            return enviarMensajeConFoto(message);
        }
    }

    private Boolean enviarMensajeConFoto(WhatsappMessage message) throws WhatsappException {
        try {
            String image = "data:image/png;base64," + message.getImage();
            Call<ChatApiResponse> callable = service.sendFile(this.token, message.getTo(), image, "imagen.png", message.getText());
            Response<ChatApiResponse> response = callable.execute();
            return response.isSuccessful() && response.body().getSent();
        } catch (IOException ex) {
            throw new WhatsappException("No se pudo enviar el mensaje. Error :" + ex.getMessage());
        }
    }

    private Boolean enviarMensajeTexto(WhatsappMessage message) throws WhatsappException  {
        try {
            Call<ChatApiResponse> callable = service.sendText(this.token, message.getTo(), message.getText());
            Response<ChatApiResponse> response = callable.execute();
            return response.isSuccessful() && response.body().getSent();
        } catch (IOException ex) {
            throw new WhatsappException("No se pudo enviar el mensaje. Error :" + ex.getMessage());
        }
    }


}
