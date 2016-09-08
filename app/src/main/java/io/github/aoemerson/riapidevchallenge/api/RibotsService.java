package io.github.aoemerson.riapidevchallenge.api;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

public interface RibotsService {

    class Builder {

        public static RibotsService build() {
            return new Retrofit.Builder()
                    .client(new OkHttpClient.Builder()
                            .build())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(RibotsService.class);
        }
    }

    String BASE_URL = "https://api.ribot.io/";

    @GET("ribots")
    Call<List<Ribot>> getRibots();


}
