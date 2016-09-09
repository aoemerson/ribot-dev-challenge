package io.github.aoemerson.riapidevchallenge.presenter;

import io.github.aoemerson.riapidevchallenge.view.BaseView;

/**
 * Created by Andrew on 09/09/2016.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
