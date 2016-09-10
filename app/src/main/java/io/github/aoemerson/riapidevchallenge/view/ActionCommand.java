package io.github.aoemerson.riapidevchallenge.view;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Andrew on 09/09/2016.
 */
public interface ActionCommand {

    void execute(Activity activity);

    Intent getIntent(Activity originActivity);

    int getDataPosition();
}
