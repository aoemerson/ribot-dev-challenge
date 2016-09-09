package io.github.aoemerson.riapidevchallenge.presenter.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import io.github.aoemerson.riapidevchallenge.api.RibotsClient;
import io.github.aoemerson.riapidevchallenge.api.RibotsOfflineClient;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.main.OpenRibotDetailCommand;
import io.github.aoemerson.riapidevchallenge.view.main.RibotsView;


public class MainRibotsPresenter implements RibotsPresenter, RibotsClient.Callback {

    RibotsView ribotsView;
    RibotsClient ribotsClient;
    private List<Ribot> ribots;


    MainRibotsPresenter(RibotsView ribotsView, RibotsClient ribotsClient) {
        attachView(ribotsView);
        this.ribotsClient = ribotsClient;
    }

    @Override
    public void attachView(RibotsView view) {
        ribotsView = view;
    }

    @Override
    public void detachView() {
        ribotsView = null;
    }

    public MainRibotsPresenter(RibotsView ribotsView) {
        attachView(ribotsView);
        //   TODO: reinstate     this.ribotsClient = RibotsRetrofitClient.getInstance();
        this.ribotsClient = RibotsOfflineClient.getInstance();
    }

    public MainRibotsPresenter() {
//   TODO: reinstate     this.ribotsClient = RibotsRetrofitClient.getInstance();
        this.ribotsClient = RibotsOfflineClient.getInstance();
    }

    @Override
    public void ribotClicked(int ribotPosition) {
        OpenRibotDetailCommand detailCommand = new OpenRibotDetailCommand(
                ((ArrayList<Ribot>) ribots), ribotPosition);
        ribotsView.showRibotDetail(detailCommand);
    }

    @Override
    public void requestRibots() {
        ribotsView.showLoading(true);
        ribotsClient.getRibots(this);
    }

    @Override
    public void onRibotsLoaded(List<Ribot> ribots) {
        ribotsView.showLoading(false);
        if (ribots != null && ribots.size() > 0) {
            this.ribots = ribots;
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
