package io.github.aoemerson.riapidevchallenge.presenter;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.RibotsView;


public interface RibotsPresenter {

    void ribotClicked(Ribot ribot);
    void requestRibots();

    void attachView(RibotsView view);
    void detachView();
}
