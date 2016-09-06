package io.github.aoemerson.riapidevchallenge.api;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;


public interface RibotsClient {

    interface Callback {
        void onRibotsLoaded(List<Ribot> ribots);
        void onRibotsLoadError(Throwable e);

    }

    void getRibots(Callback callback);
}
