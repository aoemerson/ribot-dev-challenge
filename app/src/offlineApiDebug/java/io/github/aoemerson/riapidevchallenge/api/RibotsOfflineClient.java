package io.github.aoemerson.riapidevchallenge.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import retrofit2.Call;
import retrofit2.Response;


public class RibotsOfflineClient implements RibotsClient {



    private static RibotsOfflineClient instance;

    private RibotsOfflineClient() {

    }

    public static RibotsOfflineClient getInstance() {
        if (instance == null) {
            instance = new RibotsOfflineClient();
        }
        return instance;
    }

    @Override
    public void getRibots(Callback callback) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Ribot> ribots = objectMapper.readValue(getClass()
                            .getResourceAsStream("/response.json"),
                    new TypeReference<List<Ribot>>() {});
            callback.onRibotsLoaded(ribots);
        } catch (IOException e) {
            callback.onRibotsLoadError(e);
        }
    }


}
