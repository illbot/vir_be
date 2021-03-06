package com.example.kepbongeszo_be.Controller.Request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class UploadRequest {
    private Long uploader;
    private Set<String> visibility_list;
    private String description;
    private String type;
    private String name;
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UploadRequest() {
    }

    public Long getUploader() {
        return uploader;
    }

    public void setUploader(Long uploader) {
        this.uploader = uploader;
    }

    public Set<String> getVisibility_list() {
        return visibility_list;
    }

    public void setVisibility_list(Set<String> visibility_list) {
        this.visibility_list = visibility_list;
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
}
