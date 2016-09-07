package io.github.aoemerson.riapidevchallenge.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.github.aoemerson.riapidevchallenge.model.Name;
import io.github.aoemerson.riapidevchallenge.model.Profile;
import io.github.aoemerson.riapidevchallenge.model.Ribot;

/**
 * Created by Andrew on 07/09/2016.
 */
public class RibotTestFactory {

    public static List<Ribot> createTestRibots(int howMany) {
        ArrayList<Ribot> ribots = new ArrayList<>(howMany);
        for (int i = 0; i < howMany; i++) {
            ribots.add(createRibot(i));
        }
        return ribots;
    }

    public static Ribot createRibot(int i) {
        return new Ribot(createRibotProfile(i));
    }

    private static Profile createRibotProfile(int i) {
        return Profile.builder()
                      .avatar(String.format("http://test.com/avatar%d.jpg", i))
                      .bio("Bio " + i)
                      .dateOfBirth(new Date())
                      .email(String.format("user%d@test.com", i))
                      .hexColor("#FFFFFF")
                      .id(UUID.randomUUID().toString())
                      .isActive(true)
                      .name(createRibotName(i))
                      .build();

    }

    @NonNull
    private static Name createRibotName(int i) {
        return new Name("first" + i, "last" + i);
    }
}
