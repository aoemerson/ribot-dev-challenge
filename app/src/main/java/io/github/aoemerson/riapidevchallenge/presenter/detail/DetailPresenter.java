package io.github.aoemerson.riapidevchallenge.presenter.detail;

import io.github.aoemerson.riapidevchallenge.presenter.BasePresenter;
import io.github.aoemerson.riapidevchallenge.view.detail.RibotDetailView;

/**
 * Created by Andrew on 09/09/2016.
 */
public interface DetailPresenter extends BasePresenter<RibotDetailView> {

    void requestDetail();

    void activeRibotChanged(int index);

    void shareButtonClicked();

}
