package io.github.aoemerson.riapidevchallenge.presenter.detail;

import io.github.aoemerson.riapidevchallenge.presenter.BasePresenter;
import io.github.aoemerson.riapidevchallenge.view.detail.RibotProfileView;

public interface RibotProfilePresenter extends BasePresenter<RibotProfileView> {

    void requestData();

}
