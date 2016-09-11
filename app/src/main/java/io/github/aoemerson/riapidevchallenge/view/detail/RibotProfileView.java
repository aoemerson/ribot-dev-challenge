package io.github.aoemerson.riapidevchallenge.view.detail;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.BaseView;

public interface RibotProfileView extends BaseView {

    Ribot getRibotArgument();

    String getRibotArgumentKey();

    void setName(CharSequence name);

    void setAvatar(String location);

    void setAvatarColor(String color);

    void setEmail(CharSequence email);

    void setBio(CharSequence bio);

    void setDateOfBirth(CharSequence dob);

    void setAccentColor(String hexColor);

    void detailFinalised();

    void sendEmail(String email);
}
