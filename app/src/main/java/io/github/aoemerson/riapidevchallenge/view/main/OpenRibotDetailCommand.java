package io.github.aoemerson.riapidevchallenge.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.view.ActionCommand;

/**
 * Created by Andrew on 09/09/2016.
 */
public class OpenRibotDetailCommand implements ActionCommand {

    private final ArrayList<Ribot> ribots;
    private final int index;

    public OpenRibotDetailCommand(ArrayList<Ribot> ribots, int index) {
        this.ribots = ribots;
        this.index = index;
    }

    @Override
    public void execute(Context context) {
        Intent intent = new Intent(context, ActivityCompat.class);
        Bundle extras = new Bundle();
        extras.putParcelableArrayList("", ribots);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

}
