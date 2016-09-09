package io.github.aoemerson.riapidevchallenge.view.detail;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.BaseView;

/**
 * Created by Andrew on 09/09/2016.
 */
public interface RibotDetailView extends BaseView {

    String EXTRA_RIBOTS_LIST = "RIBOTS_LIST";
    String EXTRA_TARGET_RIBOT = "RIBOT_TARGET";

    String getTargetRibotKey();

    String getRibotListBundleKey();

    List<Ribot> getBundledRibots();

    int getBundledTargetRibot();

    void setRibots(List<Ribot> ribots);

    void setActiveRibot(int index);


}
