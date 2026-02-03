package org.Grupp3Api.Api.Entity;

import jakarta.validation.constraints.NotBlank;

public class AppDTO {
    
    @NotBlank(message = "App version can't be empty")
    private String appVersion;
    private String appDescription;
    private String appImage;


    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppImage() {
        return appImage;
    }

    public void setAppImage(String appImage) {
        this.appImage = appImage;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
