package com.example.kepbongeszo_be.Controller.Request;

public class ChangeVisibilityRequest {
    private boolean userVisibility;
    private Long pictureId;

    public boolean isUserVisibility() {
        return userVisibility;
    }

    public void setUserVisibility(boolean userVisibility) {
        this.userVisibility = userVisibility;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "ChangeVisibilityRequest{" +
                "userVisibility=" + userVisibility +
                ", pictureId=" + pictureId +
                '}';
    }
}
