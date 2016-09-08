package io.github.aoemerson.riapidevchallenge.presenter;

import java.io.IOException;
import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import io.github.aoemerson.riapidevchallenge.api.RibotsClient;
import io.github.aoemerson.riapidevchallenge.api.RibotsRetrofitClient;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.RibotsView;


public class MainRibotsPresenter implements RibotsPresenter, RibotsClient.Callback {

    RibotsView ribotsView;
    RibotsClient ribotsClient;


    MainRibotsPresenter(RibotsView ribotsView, RibotsClient ribotsClient) {
        attachView(ribotsView);
        this.ribotsClient = ribotsClient;
    }

    public MainRibotsPresenter(RibotsView ribotsView) {
        attachView(ribotsView);
        this.ribotsClient = RibotsRetrofitClient.getInstance();
    }

    public MainRibotsPresenter() {
        this.ribotsClient = RibotsRetrofitClient.getInstance();
    }

    @Override
    public void ribotClicked(Ribot ribot) {
        ribotsView.showRibotDetail(ribot);
    }

    @Override
    public void requestRibots() {
        ribotsView.showLoading(true);
        ribotsClient.getRibots(this);
    }

    @Override
    public void attachView(RibotsView view) {
        ribotsView = view;
    }

    @Override
    public void detachView() {
        ribotsView = null;
    }

    @Override
    public void onRibotsLoaded(List<Ribot> ribots) {
        ribotsView.showLoading(false);
        if (ribots != null && ribots.size() > 0) {
            ribotsView.showRibots(ribots);
        } else {
            ribotsView.showError(R.string.error_msg_no_ribots_found);
        }
    }

    @Override
    public void onRibotsLoadError(Throwable e) {
        ribotsView.showLoading(false);

        if (e instanceof IOException) {
            ribotsView.showError(R.string.error_msg_ribot_connection_failed);
        } else {
            ribotsView.showError(R.string.error_msg_generic);
        }

    }
}
