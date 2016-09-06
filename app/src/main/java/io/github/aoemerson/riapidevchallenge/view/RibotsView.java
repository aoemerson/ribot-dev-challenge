package io.github.aoemerson.riapidevchallenge.view;

import android.support.annotation.Nullable;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;


public interface RibotsView {

    void showRibots(List<Ribot> ribots);
    void showRibotDetail(Ribot ribot);
    void showLoading(boolean loading);
    void showError(int errorMsgResId);
}
