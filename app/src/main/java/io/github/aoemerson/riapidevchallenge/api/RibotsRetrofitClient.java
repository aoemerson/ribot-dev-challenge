package io.github.aoemerson.riapidevchallenge.api;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import retrofit2.Call;
import retrofit2.Response;


public class RibotsRetrofitClient implements RibotsClient {

    static class RetrofitCallback implements retrofit2.Callback<List<Ribot>> {

        Callback ribotsCallback;

        RetrofitCallback(Callback ribotsCallback) {
            this.ribotsCallback = ribotsCallback;
        }

        @Override
        public void onResponse(Call<List<Ribot>> call, Response<List<Ribot>> response) {
            if (response.isSuccessful()) {
                ribotsCallback.onRibotsLoaded(response.body());
            } else {
                // TODO: read JSON error response & throw specific exception.
//                    ribotsCallback.onRibotsLoadError(throw new IOException("Placeholder for ribot API exception"));
            }

        }

        @Override
        public void onFailure(Call<List<Ribot>> call, Throwable t) {
            ribotsCallback.onRibotsLoadError(t);
        }
    }

    private static RibotsRetrofitClient instance;
    RibotsService ribotsService;

    private RibotsRetrofitClient() {
        ribotsService = RibotsService.Builder.build();
    }

    public static RibotsRetrofitClient getInstance() {
        if (instance == null) {
            instance = new RibotsRetrofitClient();
        }
        return instance;
    }

    @Override
    public void getRibots(Callback callback) {
        ribotsService.getRibots().enqueue(new RetrofitCallback(callback));
    }


}
