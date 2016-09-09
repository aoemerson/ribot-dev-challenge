package io.github.aoemerson.riapidevchallenge.model;

import java.util.Date;

public class Profile {

    public static class Builder {

        Profile profile = new Profile();

        public Builder id(String id) {
            profile.id = id;
            return this;
        }

        public Builder email(String email) {
            profile.email = email;
            return this;
        }

        public Builder avatar(String avatar) {
            profile.avatar = avatar;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            profile.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder hexColor(String hexColor) {
            profile.hexColor = hexColor;
            return this;
        }

        public Builder bio(String bio) {
            profile.bio = bio;
            return this;
        }

        public Builder name(Name name) {
            profile.name = name;
            return this;
        }

        public Builder isActive(boolean isActive) {
            profile.isActive = isActive;
            return this;
        }

        public Profile build() {
            return profile;
        }

    }
    String id;
    String email;
    String avatar;
    Date dateOfBirth;
    String hexColor;
    String bio;
    boolean isActive;
    Name name;
    public Profile() {
    }

    public static Builder builder() {
        return new Builder();
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (hexColor != null ? hexColor.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (isActive != profile.isActive) return false;
        if (id != null ? !id.equals(profile.id) : profile.id != null) return false;
        if (email != null ? !email.equals(profile.email) : profile.email != null) return false;
        if (avatar != null ? !avatar.equals(profile.avatar) : profile.avatar != null) return false;
        if (dateOfBirth != null ? !dateOfBirth
                .equals(profile.dateOfBirth) : profile.dateOfBirth != null) return false;
        if (hexColor != null ? !hexColor.equals(profile.hexColor) : profile.hexColor != null)
            return false;
        if (bio != null ? !bio.equals(profile.bio) : profile.bio != null) return false;
        return name != null ? name.equals(profile.name) : profile.name == null;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
