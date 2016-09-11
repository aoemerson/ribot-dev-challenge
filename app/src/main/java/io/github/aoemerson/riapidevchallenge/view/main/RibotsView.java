package io.github.aoemerson.riapidevchallenge.view.main;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.ActionCommand;
import io.github.aoemerson.riapidevchallenge.view.BaseView;


public interface RibotsView extends BaseView {

    void showRibots(List<Ribot> ribots);

    void showRibotDetail(ActionCommand action);

}
