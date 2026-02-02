package org.Grupp3Api.Api.Entity;

import java.time.LocalDate;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "t_APP")
public class App {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Application needs to have a name.")
    @Column(length = 200, name = "app_name")
    private String appName;
    
    @NotNull
    private double appVersion;

    @Size(min = 0, max = 200)
    private String appDescription;
    private String appImage;

    @NotEmpty(message = "There must be a publisher!")
    private String appPublisher;
    private LocalDate creationDate;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public double getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }
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
    public String getAppPublisher() {
        return appPublisher;
    }
    public void setAppPublisher(String appPublisher) {
        this.appPublisher = appPublisher;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    
}
