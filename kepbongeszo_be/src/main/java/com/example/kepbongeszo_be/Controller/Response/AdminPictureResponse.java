package com.example.kepbongeszo_be.Controller.Response;

import com.example.kepbongeszo_be.Model.Role;

import java.util.Set;

public class AdminPictureResponse {
    private Long id;
    private String description;
    private String type;
    private String name;
    private String uploader;
    private String displayName;
    private Set<Role> availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<Role> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<Role> availability) {
        this.availability = availability;
    }
}
