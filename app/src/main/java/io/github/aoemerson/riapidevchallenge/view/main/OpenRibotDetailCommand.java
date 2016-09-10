package io.github.aoemerson.riapidevchallenge.view.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.ActionCommand;
import io.github.aoemerson.riapidevchallenge.view.detail.RibotProfileActivity;


public class OpenRibotDetailCommand implements ActionCommand {


    private final Ribot ribot;
    private int ribotIndex;

    public OpenRibotDetailCommand(Ribot ribot) {
        this.ribot = ribot;
    }

    public OpenRibotDetailCommand(Ribot ribot, int ribotIndex) {
        this.ribot = ribot;
        this.ribotIndex = ribotIndex;
    }

    @Override
    public void execute(Activity activity) {

        activity.startActivity(getIntent(activity));
    }

    @Override
    public Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, RibotProfileActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(RibotProfileActivity.EXTRA_RIBOT, ribot);
        intent.putExtras(extras);
        return intent;
    }

    @Override
    public int getDataPosition() {
        return ribotIndex;
    }


}
