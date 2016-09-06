package io.github.aoemerson.riapidevchallenge.model;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;




public class ProfileTests {

    private static final Name NAME = new Name("first", "last");
    private static final String AVATAR = "http://url.com/pic.jpg";
    private static final String BIO = "This is a bio";
    private static final String EMAIL = "this@that.com";
    private static final String UUID = java.util.UUID.randomUUID().toString();
    private static final String HEX_COLOR = "#FFFFFF";
    private static final boolean IS_ACTIVE = true;
    private static final Date DATE = new Date();


    @Test
    public void builderCreatesProfile() {
        Profile expectedProfile = new Profile();
        expectedProfile.isActive = IS_ACTIVE;
        expectedProfile.name = NAME;
        expectedProfile.avatar = AVATAR;
        expectedProfile.bio = BIO;
        expectedProfile.email = EMAIL;
        expectedProfile.id = UUID;
        expectedProfile.hexColor = HEX_COLOR;
        expectedProfile.dateOfBirth = DATE;

        Profile actualProfile = Profile.builder()
                                       .isActive(IS_ACTIVE)
                                       .name(NAME)
                                       .avatar(AVATAR)
                                       .bio(BIO)
                                       .email(EMAIL)
                                       .id(UUID)
                                       .dateOfBirth(DATE)
                                       .hexColor(HEX_COLOR)
                                       .build();

        assertThat(expectedProfile, is(equalTo(actualProfile)));
    }
}
