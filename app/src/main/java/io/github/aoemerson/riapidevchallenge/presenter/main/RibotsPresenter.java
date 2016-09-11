package io.github.aoemerson.riapidevchallenge.presenter.main;

import io.github.aoemerson.riapidevchallenge.presenter.BasePresenter;
import io.github.aoemerson.riapidevchallenge.view.main.RibotsView;


public interface RibotsPresenter extends BasePresenter<RibotsView> {

    void ribotClicked(int ribotPosition);

    void requestRibots();

    void restore();

}
