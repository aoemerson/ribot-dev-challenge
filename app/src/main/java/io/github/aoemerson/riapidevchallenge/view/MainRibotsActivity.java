package io.github.aoemerson.riapidevchallenge.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.presenter.MainRibotsPresenter;
import io.github.aoemerson.riapidevchallenge.presenter.RibotsPresenter;

public class MainRibotsActivity extends AppCompatActivity implements RibotsView {

    @BindView(R.id.recyclerview_ribots_grid) RecyclerView ribotsGridView;
    @BindInt(R.integer.ribot_grid_columns) int ribotGridColumns;

    private RibotsAdapter ribotsAdapter;
    private RibotsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ribots);
        ButterKnife.bind(this);
        ribotsAdapter = new RibotsAdapter();
        ribotsGridView.setAdapter(
                ribotsAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, ribotGridColumns);
        ribotsGridView.setHasFixedSize(true);
        ribotsGridView.setLayoutManager(gridLayoutManager);
        presenter = new MainRibotsPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.requestRibots();
    }

    @Override
    public void showRibots(List<Ribot> ribots) {
        ribotsAdapter.setRibots(ribots);
    }

    @Override
    public void showRibotDetail(Ribot ribot) {

    }

    @Override
    public void showLoading(boolean loading) {

    }

    @Override
    public void showError(int errorMsgResId) {

    }
}
