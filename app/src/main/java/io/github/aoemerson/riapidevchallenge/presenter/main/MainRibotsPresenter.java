package io.github.aoemerson.riapidevchallenge.presenter.main;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import io.github.aoemerson.riapidevchallenge.api.RibotsClient;
import io.github.aoemerson.riapidevchallenge.api.RibotsRetrofitClient;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.main.OpenRibotDetailCommand;
import io.github.aoemerson.riapidevchallenge.view.main.RibotsView;


public class MainRibotsPresenter implements RibotsPresenter, RibotsClient.Callback, Parcelable {

    public static final Parcelable.Creator<MainRibotsPresenter> CREATOR = new Parcelable.Creator<MainRibotsPresenter>() {
        @Override
        public MainRibotsPresenter createFromParcel(Parcel source) {
            return new MainRibotsPresenter(source);
        }

        @Override
        public MainRibotsPresenter[] newArray(int size) {
            return new MainRibotsPresenter[size];
        }
    };

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
        this.ribotsClient = RibotsRetrofitClient.getInstance();
    }

    public MainRibotsPresenter() {
        this.ribotsClient = RibotsRetrofitClient.getInstance();
    }

    protected MainRibotsPresenter(Parcel in) {
        this.ribots = in.createTypedArrayList(Ribot.CREATOR);
    }

    @Override
    public void ribotClicked(int ribotPosition) {
        OpenRibotDetailCommand detailCommand = new OpenRibotDetailCommand(ribots
                .get(ribotPosition), ribotPosition);
        ribotsView.showRibotDetail(detailCommand);
    }

    @Override
    public void requestRibots() {

        if (ribots == null || ribots.size() == 0) {
            ribotsView.showLoading(true);
            ribotsClient.getRibots(this);
        } else {
            ribotsView.showRibots(ribots);
        }
    }

    @Override
    public void restore() {
        this.ribotsClient = RibotsRetrofitClient.getInstance();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.ribots);
    }
}
