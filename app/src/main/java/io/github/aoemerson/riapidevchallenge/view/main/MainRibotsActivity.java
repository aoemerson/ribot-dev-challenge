package io.github.aoemerson.riapidevchallenge.view.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;

import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.presenter.main.MainRibotsPresenter;
import io.github.aoemerson.riapidevchallenge.presenter.main.RibotsPresenter;
import io.github.aoemerson.riapidevchallenge.view.ActionCommand;

public class MainRibotsActivity extends AppCompatActivity implements RibotsView, RibotsAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview_ribots_grid) RecyclerView ribotsGridView;
    @BindInt(R.integer.ribot_grid_columns) int ribotGridColumns;

    private RibotsAdapter ribotsAdapter;
    private RibotsPresenter presenter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ribots);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade = new Fade();
//            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setExitTransition(fade);

            getWindow().setEnterTransition(fade);
        }

        ribotsAdapter = new RibotsAdapter(this);
        ribotsGridView.setAdapter(
                ribotsAdapter);
        gridLayoutManager = new GridLayoutManager(this, ribotGridColumns);
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
    public void showRibotDetail(ActionCommand command) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss", Locale.US);
//        File dir = Environment.getExternalStorageDirectory();
//
//        Debug.startMethodTracing(String.format("%s/trace", dir.toString()));
//        command.execute(this);
        Intent intent = command.getIntent(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            View ribotItemView = gridLayoutManager.findViewByPosition(command.getDataPosition());
            View avatar = ribotItemView.findViewById(R.id.imageview_avatar);
            ActivityOptions activityOptions = ActivityOptions
                    .makeSceneTransitionAnimation(this, avatar, "avatar_transition");
            startActivity(intent, activityOptions.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showLoading(boolean loading) {

    }

    @Override
    public void showError(int errorMsgResId) {
    }

    @Override
    public void onRibotItemClicked(int itemPosition) {
        presenter.ribotClicked(itemPosition);
    }
}
