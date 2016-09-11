package io.github.aoemerson.riapidevchallenge.presenter.detail;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.github.aoemerson.riapidevchallenge.model.Name;
import io.github.aoemerson.riapidevchallenge.model.Profile;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.detail.RibotProfileView;

public class MainRibotProfilePresenter implements RibotProfilePresenter {

    private RibotProfileView ribotProfileView;
    private Ribot ribot;

    @Override
    public void requestData() {
        ribot = ribotProfileView.getRibotArgument();
        if (ribot != null) {
            Profile profile = ribot.getProfile();
            if (profile != null) {
                setAvatar(profile);
                setName(profile);
                setDob(profile);
                setEmail(profile);
                setBio(profile);
                ribotProfileView.setAccentColor(profile.getHexColor());
                ribotProfileView.detailFinalised();
            }
        } else {
//       TODO: handle this error
        }

    }

    @Override
    public void onEmailButtonClicked() {
        ribotProfileView.sendEmail(ribot.getProfile().getEmail());
    }

    private void setAvatar(Profile profile) {
        String avatar = profile.getAvatar();
        if (avatar != null && avatar.length() > 0)
            ribotProfileView.setAvatar(avatar);
        else {

        }
//            ribotProfileView.
    }

    private void setBio(Profile profile) {
        String bio = profile.getBio();
        if (validField(bio)) {
            ribotProfileView.setBio(bio);
        }
    }

    private void setEmail(@NonNull Profile profile) {
        String email = profile.getEmail();
        if (validField(email)) {
            ribotProfileView.setEmail(email);
        }

    }

    private void setDob(@NonNull Profile profile) {
        Date dob = profile.getDateOfBirth();
        if (dob != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
            String dobString = sdf.format(dob);
            ribotProfileView.setDateOfBirth(dobString);
        }
    }

    private void setName(@NonNull Profile profile) {
        Name name = profile.getName();
        if (name != null && validField(name.getFirst())) {
            ribotProfileView.setName(String
                    .format("%s %s", name.getFirst(), name.getLast()));
        }
    }

    private boolean validField(String field) {
        return field != null && field.length() > 0;
    }

    @Override
    public void attachView(RibotProfileView view) {
        ribotProfileView = view;
    }

    @Override
    public void detachView() {
        ribotProfileView = null;
    }
}
