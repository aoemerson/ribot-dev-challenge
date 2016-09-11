package io.github.aoemerson.riapidevchallenge.view.detail;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import aoemerson.github.io.riapidevchallenge.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.presenter.detail.MainRibotProfilePresenter;
import io.github.aoemerson.riapidevchallenge.presenter.detail.RibotProfilePresenter;
import io.github.aoemerson.riapidevchallenge.view.transition.TransitionAdapter;

public class RibotProfileActivity extends AppCompatActivity implements RibotProfileView {

    public static String EXTRA_RIBOT = "EXTRA_RIBOTS";
    @BindView(R.id.view_avatar_header) ImageView avatarHeaderView;
    @BindView(R.id.detailItemContainer) LinearLayout ribotDetailLayout;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;

    RibotPropertyView lastAddedPropertyView;
    private RibotProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paralax_ribot_detail);
        ButterKnife.bind(this);

        collapsingToolbar.setTitleEnabled(savedInstanceState != null);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(new TransitionAdapter() {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void onTransitionEnd(Transition transition) {
                    collapsingToolbar.postDelayed(new Runnable() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            collapsingToolbar.setTitleEnabled(true);
                        }
                    }, 100);
                }
            });

            Transition fade = new Fade();
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setExitTransition(fade);
            getWindow().setEnterTransition(fade);
        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new MainRibotProfilePresenter();
        presenter.attachView(this);
        presenter.requestData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        collapsingToolbar.setTitleEnabled(false);
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        presenter.onEmailButtonClicked();
    }

    @Override
    public Ribot getRibotArgument() {
        return getIntent().getParcelableExtra(getRibotArgumentKey());
    }

    @Override
    public String getRibotArgumentKey() {
        return EXTRA_RIBOT;
    }

    @Override
    public void setName(CharSequence name) {
        collapsingToolbar.setTitle(name);
    }


    @Override
    public void setAvatar(String location) {
        if (location != null && location.length() > 0) {
            Picasso.with(this)
                   .load(location)
                   .placeholder(R.drawable.ic_person_100px)
                   .error(R.drawable.ic_person_100px)
                   .into(avatarHeaderView);
        }
    }

    @Override
    public void setAvatarColor(String color) {
        avatarHeaderView.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void setEmail(CharSequence email) {
        RibotPropertyView emailView = new RibotPropertyView(this);
        emailView.setText(email);
        emailView.setIconResource(R.drawable.ic_email_24px);
        ribotDetailLayout.addView(emailView);
        lastAddedPropertyView = emailView;
    }

    @Override
    public void setBio(CharSequence bio) {
        RibotPropertyView bioView = new RibotPropertyView(this);
        bioView.setText(bio);
        bioView.setIconResource(R.drawable.ic_bio_24px);
        ribotDetailLayout.addView(bioView);
        lastAddedPropertyView = bioView;

    }

    @Override
    public void setDateOfBirth(CharSequence dob) {
        RibotPropertyView dobView = new RibotPropertyView(this);
        dobView.setText(dob);
        dobView.setIconResource(R.drawable.ic_birthday_24px);
        ribotDetailLayout.addView(dobView);
        lastAddedPropertyView = dobView;
    }

    @Override
    public void setAccentColor(String hexColor) {
        try {
            int color = Color.parseColor(hexColor);
//            avatarHeaderView.setBackgroundColor(color);
            collapsingToolbar.setBackgroundColor(color);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(color);
            }
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            DrawableCompat.setTint(fab.getDrawable(), color);
            collapsingToolbar.setContentScrimColor(color);
        } catch (IllegalArgumentException ignored) {
            // Ignored - keep default color
            // TODO: Consider logging exception to Analytics (via throwing exception to presenter?)
        }
    }

    @Override
    public void detailFinalised() {
        lastAddedPropertyView.setSeparatorVisibility(false);
    }

    @Override
    public void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(String.format("mailto:%s", email)));
        startActivity(emailIntent);
    }

    @Override
    public void showLoading(boolean loading) {

    }

    @Override
    public void showError(int errorMsgResId) {

    }
}
